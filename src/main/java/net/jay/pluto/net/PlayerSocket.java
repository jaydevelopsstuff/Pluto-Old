package net.jay.pluto.net;

import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.util.TerrariaReader;
import net.jay.pluto.util.TerrariaWriter;

import java.io.IOException;
import java.net.Socket;

public class PlayerSocket extends Socket {
    private TerrariaReader reader;
    private TerrariaWriter writer;

    public void init() throws IOException {
        reader = new TerrariaReader(getInputStream());
        writer = new TerrariaWriter(getOutputStream());
    }

    public void readData() throws IOException {
        if(reader.available() == 0) return;

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

    public TerrariaReader getReader() {
        return reader;
    }

    public TerrariaWriter getWriter() {
        return writer;
    }
}
