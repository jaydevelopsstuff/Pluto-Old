package net.jay.pluto.net;

import net.jay.pluto.io.TerrariaReader;
import net.jay.pluto.io.TerrariaWriter;
import net.jay.pluto.localization.NetworkText;
import net.jay.pluto.net.packet.Packet;
import net.jay.pluto.net.packet.SPacket;
import net.jay.pluto.net.packet.packets.server.DisconnectClient;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientSocket extends Socket {
    private TerrariaReader reader;
    private TerrariaWriter writer;

    /** A list that contains packets that are queued for sending, these are stored conveniently as <code>PacketBuffer</code>s so that they only contain their byte data */
    private final List<PacketBuffer> queuedPackets = new CopyOnWriteArrayList<>();

    public void init() throws IOException {
        reader = new TerrariaReader(getInputStream());
        writer = new TerrariaWriter(getOutputStream());
    }

    /**
     * Checks if a packet has been sent and if one has, it parses the data (bytes) to an easily readable packet
     * @return The packet that has been read, this is null if no packet was sent
     * @throws IOException
     */
    public Packet readPacket() throws IOException {
        int available = reader.available();
        if(available == 0) return null;

        short completeMessageLength = reader.readShort();
        short messageLength = (short)(completeMessageLength - 2);
        PacketBuffer buffer = new PacketBuffer(reader.readNBytes(messageLength));

        int messageID = buffer.readByte();

        Packets packetType = Packets.fromID(messageID);
        if(packetType == null) return null;
        System.out.println(packetType.name());

       return Packets.getPacketAndSetData(packetType, buffer);
    }

    /**
     * Adds a packet to the packet queue, this does not send the packet however, to flush (send) the packet queue to the client you need to use <code>flushPacketQueue</code>
     * @param packet The packet to be queued
     */
    public void queuePacket(SPacket packet) {
        PacketBuffer tempBuffer = packet.writePacketData();

        int rawBytesCount = tempBuffer.getWriterIndex();
        // 2 is for the short and the 1 is for the message type byte
        int bytesCount = rawBytesCount + 2 + 1;

        PacketBuffer buffer = new PacketBuffer(bytesCount);
        buffer.writeShort((short)bytesCount);
        buffer.writeByte((byte)packet.getEnum().ID);


        for(int i = 0; i < rawBytesCount; i++) {
            buffer.writeByte(tempBuffer.getByte(i));
        }

        queuedPackets.add(buffer);
    }

    /**
     * Writes a packet to the stream and then flushes (sends) it, this is fine in most situations but in others you might want to use <code>queuePacket</code>
     * @param packet The packet to be sent
     * @throws IOException If the stream throws an exception when writing/flushing
     */
    public void sendPacket(SPacket packet) throws IOException {
        PacketBuffer tempBuffer = packet.writePacketData();

        int rawBytesCount = tempBuffer.getWriterIndex();
        // 2 is for the short and the 1 is for the message type byte
        int bytesCount = rawBytesCount + 2 + 1;

        PacketBuffer buffer = new PacketBuffer(bytesCount);
        buffer.writeShort((short)bytesCount);
        buffer.writeByte((byte)packet.getEnum().ID);


        for(int i = 0; i < rawBytesCount; i++) {
            buffer.writeByte(tempBuffer.getByte(i));
        }

        writer.writeBuffer(buffer);
        writer.flush();
    }

    /** Flushes (sends) all the <code>PacketBuffer</code>s from <code>queuedPackets</code> to the client  */
    public void flushPacketQueue() throws IOException {
        for(PacketBuffer packetBuffer : queuedPackets) writer.writeBuffer(packetBuffer);
        queuedPackets.clear();
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
