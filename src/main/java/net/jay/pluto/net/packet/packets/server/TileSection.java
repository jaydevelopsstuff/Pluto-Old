package net.jay.pluto.net.packet.packets.server;

import lombok.AllArgsConstructor;
import net.jay.pluto.container.Chest;
import net.jay.pluto.entity.tileentity.TileEntity;
import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.VariableSizePacketBuffer;
import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.world.sign.Sign;
import net.jay.pluto.world.tile.Block;
import net.jay.pluto.world.tile.Tile;
import net.jay.pluto.world.tile.Wall;

import java.util.zip.Deflater;

@AllArgsConstructor
public class TileSection implements SPacket {
    public boolean compressed;
    public int startX;
    public int startY;
    public short width;
    public short height;
    public Tile[][] tiles;
    public Chest[] chests;
    public Sign[][] signs;
    public TileEntity[] tileEntities;

    @Override
    public PacketBuffer writePacketData() {
        VariableSizePacketBuffer buffer = new VariableSizePacketBuffer();
        buffer.writeBoolean(compressed);
        VariableSizePacketBuffer toCompressBuffer = new VariableSizePacketBuffer();

        // COMPRESSED
        toCompressBuffer.writeInt(startX);
        toCompressBuffer.writeInt(startY);
        toCompressBuffer.writeShort(width);
        toCompressBuffer.writeShort(height);
        toCompressBuffer = writeTiles(toCompressBuffer);
        toCompressBuffer.writeShort((short)0);
        // TODO: Chests
        toCompressBuffer.writeShort((short)0);
        // TODO: Signs
        toCompressBuffer.writeShort((short)0);
        // TODO: Tile Entities
        // COMPRESSED

        // Write compressed buffer
        buffer.writeBytes(writeCompressed(toCompressBuffer));
        return buffer.toNormal();
    }

    @Override
    public PacketBuffer writePacketData(PacketBuffer buffer) {
        return buffer;
    }

    public VariableSizePacketBuffer writeTiles(VariableSizePacketBuffer buffer) {
        for(short y = 0; y < height; y++) {
            for(short x = 0; x < width; x++) {
                Tile tile = tiles[x][y];
                Block block = tile.getBlock();
                Wall wall = tile.getWall();

                byte header1 = 0;
                byte header2 = 0;
                byte header3 = 0;

                boolean additionalBlockByte = false;
                boolean additionalWallByte = false;

                /*if(tile.hasBlock()) {
                    header1 |= blockBit;
                    if(block.getID() > 255) {
                        header1 |= additionalBlockByteBit;
                        additionalBlockByte = true;
                    }
                }

                if(tile.hasWall()) {
                    header1 |= wallBit;
                    if(wall.getID() > 255) {
                        header1 |= additionalWallByteBit;
                        additionalWallByte = true;
                    }
                }*/

                // TODO: Liquid and Wires

                // if(tile.hasBlock()) header2 |= (tile.getBlock().getModification().ID << shapeShift) & shapeBits;

                //if(tile.hasActuator()) header3 |= actuatorBit;
                //if(tile.isActuated()) header3 |= actuatedBit;

                if(header2 != 0) header1 |= header2Bit;
                if(header3 != 0) {
                    header1 |= header2Bit;
                    header2 |= header3Bit;
                }

                buffer.writeByte(header1);
                if(header2 != 0) buffer.writeByte(header2);
                if(header3 != 0) buffer.writeByte(header3);

                /*if(tile.hasBlock()) {
                    if(additionalBlockByte) buffer.writeShort(block.getID());
                    else buffer.writeByte((byte)block.getID());

                    buffer.writeShort(block.getU());
                    buffer.writeShort(block.getV());
                }

                if(tile.hasWall()) buffer.writeByte((byte)(wall.getID()));

                // LIQUID

                if(tile.hasWall()) buffer.writeByte((byte)(wall.getID() >> 8));*/
            }
        }
        return buffer;
    }

    public byte[] writeCompressed(VariableSizePacketBuffer input) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.DEFAULT_COMPRESSION);

        deflater.setInput(input.getBuffer());
        deflater.finish();
        byte[] rawOutput = new byte[8192];
        int size = deflater.deflate(rawOutput);
        byte[] output = new byte[size];
        System.arraycopy(rawOutput, 0, output, 0, size);
        return output;
    }

    @Override
    public Packets getEnum() {
        return Packets.SendSection;
    }

    // Header 1
    private static final byte header2Bit = 0b00000001;
    private static final byte blockBit = 0b00000010;
    private static final byte wallBit = 0b00000100;
    private static final byte additionalBlockByteBit = 0b00100000;
    private static final byte liquidBits = 0b00011000;

    // Header 2
    private static final byte header3Bit = 0b00000001;
    private static final byte redWireBit = 0b00000010;
    private static final byte blueWireBit = 0b00000100;
    private static final byte greenWireBit = 0b00001000;
    private static final byte shapeBits = 0b01110000;

    // Header 3
    private static final byte actuatorBit = 0b00000010;
    private static final byte actuatedBit = 0b00000100;
    private static final byte yellowWireBit = 0b00100000;
    private static final byte additionalWallByteBit = 0b01000000;

    // Shifts
    private static final byte liquidShift = 3;
    private static  final byte shapeShift = 4;
}
