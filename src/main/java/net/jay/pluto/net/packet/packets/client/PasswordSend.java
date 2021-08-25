package net.jay.pluto.net.packet.packets.client;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerLoginNetHandler;
import net.jay.pluto.net.packet.CPacket;

public class PasswordSend implements CPacket<IServerLoginNetHandler> {
    private static final Packets enumRepresentation = Packets.PasswordSend;

    private String password;

    public PasswordSend(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        password = buffer.readString();
    }

    @Override
    public void processPacket(IServerLoginNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
