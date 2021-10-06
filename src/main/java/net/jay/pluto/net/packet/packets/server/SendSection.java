package net.jay.pluto.net.packet.packets.server;

import net.jay.pluto.container.Chest;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.entity.tileentity.TileEntity;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.world.sign.Sign;
import net.jay.pluto.world.tile.Block;
import net.jay.pluto.world.tile.Tile;
import net.jay.pluto.world.tile.WireType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.Deflater;

public class SendSection implements SPacket {
    private static final Packets enumRepresentation = Packets.SendSection;
    // Undefined size, can probably be very large so yeah
    private static final int maxPacketDataSize = 8192;

    public boolean[] importance;
    public boolean compressed;
    public int startX;
    public int startY;
    public short width;
    public short height;
    public Tile[][] tiles;
    public Chest[] chests;
    public Sign[] signs;
    public TileEntity[] tileEntities;

    public SendSection(int startX, int startY, short width, short height, Tile[][] tiles, Chest[] chests, Sign[] signs, TileEntity[] tileEntities) {
        this.importance = Access.world.getBoringInfo().getTileImportants();
        this.compressed = true;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.chests = chests;
        this.signs = signs;
        this.tileEntities = tileEntities;
    }

    public SendSection(boolean[] importance, boolean compressed, int startX, int startY, short width, short height, Tile[][] tiles, Chest[] chests, Sign[] signs, TileEntity[] tileEntities) {
        this.importance = importance;
        this.compressed = compressed;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.chests = chests;
        this.signs = signs;
        this.tileEntities = tileEntities;
    }

    @Override
    public PacketBuffer writePacketData() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);
        buffer.writeBoolean(compressed);
        buffer.writeInt(startX);
        buffer.writeInt(startY);
        buffer.writeShort(width);
        buffer.writeShort(height);
        buffer.writeBytes(compress(writeTilesUncompressed()));
        buffer.writeShort((short)chests.length);
        // Chest data
        buffer.writeShort((short)signs.length);
        // Signs data
        buffer.writeShort((short)tileEntities.length);
        // Tile Entities data
        return buffer;
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        buffer.writeBoolean(compressed);
        buffer.writeInt(startX);
        buffer.writeInt(startY);
        buffer.writeShort(width);
        buffer.writeShort(height);
        buffer.writeBytes(compress(writeTilesUncompressed()));
        buffer.writeShort((short)chests.length);
        // Chest data
        buffer.writeShort((short)signs.length);
        // Signs data
        buffer.writeShort((short)tileEntities.length);
        // Tile Entities data
        return buffer;
    }

    private byte[] compress(byte[] uncompressed) {
        if(!compressed) return uncompressed;

        Deflater deflater = new Deflater();
        deflater.setInput(uncompressed);
        deflater.finish();

        byte[] bigBuffer = new byte[maxPacketDataSize / 2];

        int actualSize = deflater.deflate(bigBuffer);

        byte[] actualBuffer = new byte[actualSize];

        System.arraycopy(bigBuffer, 0, actualBuffer, 0, actualSize);

        return actualBuffer;
    }

    private byte[] writeTilesUncompressed() {
        PacketBuffer buffer = new PacketBuffer(maxPacketDataSize);

        int bytesWritten = 0;
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Tile tile = tiles[x][y];

                List<Byte> tileData = serializeTileData(tile);

                short rleCount = 0;
                int nextY = y;
                int remainingY = height - y - 1;
                while(remainingY > 0 && tile.sameAs(tiles[x][nextY])) {
                    rleCount++;
                    remainingY--;
                    nextY++;
                }

                if(rleCount > 0) {
                    y = y + rleCount;

                    tileData.add((byte)(rleCount));

                    if(rleCount <= 255) tileData.set(0, (byte)(tileData.get(0) | 64));
                    else {
                        tileData.set(0, (byte)(tileData.get(0) | 128));

                        tileData.add((byte)(rleCount >> 8));
                    }
                }

                byte[] realTileData = new byte[tileData.size()];
                int index = 0;
                for(Byte b : tileData) {
                    if(b == null) continue;
                    realTileData[index] = b;
                    index++;
                }

                buffer.writeBytes(realTileData);
                bytesWritten += realTileData.length;
            }
        }

        byte[] actualBytes = new byte[bytesWritten];
        System.arraycopy(buffer.getBuffer(), 0, actualBytes, 0, bytesWritten);

        return actualBytes;
    }

    private List<Byte> serializeTileData(Tile tile) {
        byte header1 = 0;
        byte header2 = 0;
        byte header3 = 0;
        LinkedList<Byte> data = new LinkedList<>();

        // tile data
        if(tile.getBlock() != null)
        {
            // Active first bit
            header1 = (byte)(header1 | 2);

            // Save ID as byte or short
            if(tile.getBlock().getID() <= 255) data.add((byte)tile.getBlock().getID());
            else {
                // Write high byte
                data.add((byte)(tile.getBlock().getID() >> 8));

                // Set header1's 5th bit for short tile ID
                header1 = (byte)(header1 | 32);
            }

            if(importance[tile.getBlock().getID()])
            {
                // Pack UV coords
                data.add((byte) ((byte)(tile.getBlock().getU())));
                data.add((byte)((tile.getBlock().getU()) >> 8));
                data.add((byte) ((byte)(tile.getBlock().getV())));
                data.add((byte)((tile.getBlock().getV()) >> 8));
            }

            if(tile.getBlock().getColor() != 0)
            {
                // Set header3's 3 for block color active
                header3 = (byte)(header3 | 8);
                data.add(tile.getBlock().getColor());
            }
        }

        // wall data
        if (tile.getWall() != null)
        {
            // set header1 bit[2] for wall active
            header1 = (byte)(header1 | 4);
            data.add((byte)tile.getWall().getID());

            // save tile wall color
            if (tile.getWall().getColor() != 0)
            {
                // Set header3's bit 4 for wall color active
                header3 = (byte)(header3 | 16);
                data.add(tile.getWall().getColor());
            }
        }

        // liquid data
        /*if (tile.LiquidAmount != 0 && tile.LiquidType != LiquidType.None)
        {
            // set bits[3,4] using left shift
            header1 = (byte)(header1 | (byte)((byte)tile.LiquidType << 3));
            tileData[dataIndex++] = tile.LiquidAmount;
        }*/

        // wire data
        if (tile.getWire() == WireType.Red)
        {
            // red wire = header2 bit[1]
            header2 = (byte)(header2 | 2);
        }
        if (tile.getWire() == WireType.Blue)
        {
            // blue wire = header2 bit[2]
            header2 = (byte)(header2 | 4);
        }
        if (tile.getWire() == WireType.Green)
        {
            // green wire = header2 bit[3]
            header2 = (byte)(header2 | 8);
        }

        // brick style
        byte brickStyle = tile.getBlock() != null ? (byte)((byte)tile.getBlock().getModification().ID << 4) : (byte)Block.Modification.Solid.ID;
        // set bits[4,5,6] of header2
        header2 = (byte)(header2 | brickStyle);

        // actuator data
        if (tile.hasActuator())
        {
            // set bit[1] of header3
            header3 = (byte)(header3 | 2);
        }
        if (tile.isActuated())
        {
            // set bit[2] of header3
            header3 = (byte)(header3 | 4);
        }
        if (tile.getWire() == WireType.Yellow)
        {
            header3 = (byte)(header3 | 32);
        }
        if(tile.getWall() != null) {
            if (tile.getWall().getID() > 255)
            {
                data.add((byte)(tile.getWall().getID() >> 8));
                header3 = (byte)(header3 | 64);
            }
        }


        if (header3 != 0)
        {
            // set header3 active flag bit[0] of header2
            header2 = (byte)(header2 | 1);
            data.addFirst(header3);
        }
        if (header2 != 0)
        {
            // set header2 active flag bit[0] of header1
            header1 = (byte)(header1 | 1);
            data.addFirst(header2);
        }

        data.addFirst(header1);

        return data;
    }

    @Override
    public int getMaxPacketDataSize() {
        return maxPacketDataSize;
    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
