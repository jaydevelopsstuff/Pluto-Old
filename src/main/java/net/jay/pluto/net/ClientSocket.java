package net.jay.pluto.net;

import net.jay.pluto.io.TerrariaReader;
import net.jay.pluto.io.TerrariaWriter;
import net.jay.pluto.localization.NetworkText;
import net.jay.pluto.net.packet.Packet;
import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.net.packet.packets.server.DisconnectClient;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket extends Socket {
    private TerrariaReader reader;
    private TerrariaWriter writer;

    public void init() throws IOException {
        reader = new TerrariaReader(getInputStream());
        writer = new TerrariaWriter(getOutputStream());
    }

    public Packet readPacket() throws IOException {
        int available = reader.available();
        if(available == 0) return null;

        PacketBuffer buffer = new PacketBuffer(reader.readNBytes(available));

        short messageLength = buffer.readShort();
        int messageID = buffer.readByte();

        Packets packetType = Packets.fromID(messageID);
        if(packetType == null) return null;

       return Packets.getPacketAndSetData(packetType, buffer);
    }

    public void sendPacket(SPacket packet) throws IOException {
        PacketBuffer tempBuffer = packet.writePacketData();

        int bytesCount = 0;
        for(byte b : tempBuffer.getBuffer()) {
            if(b == -128) continue;
            bytesCount++;
        }

        // 2 is for the short and the 1 is for the message type byte
        bytesCount += 2 + 1;

        PacketBuffer buffer = new PacketBuffer(bytesCount);
        buffer.writeShort((short)bytesCount);
        buffer.writeByte((byte)packet.getEnum().ID);


        for(byte b : tempBuffer.getBuffer()) {
            if(b == -128) continue;
            buffer.writeByte(b);
        }
        writer.writeBuffer(buffer);
        writer.flush();
    }

    public void disconnectGracefully(String reason) throws IOException {
        sendPacket(new DisconnectClient(new NetworkText(reason, NetworkText.Mode.LITERAL)));
        close();
    }

    public void disconnectGracefully() throws IOException {
        sendPacket(new DisconnectClient(new NetworkText("Disconnected", NetworkText.Mode.LITERAL)));
        close();
    }

    public TerrariaReader getReader() {
        return reader;
    }

    public TerrariaWriter getWriter() {
        return writer;
    }
}
