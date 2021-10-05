package net.jay.pluto.net.packet.packets.client;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.net.Packets;
import net.jay.pluto.net.handlers.IServerPlayNetHandler;
import net.jay.pluto.net.packet.CPacket;

public class LoadNetModule implements CPacket<IServerPlayNetHandler> {
    private static final Packets enumRepresentation = Packets.LoadNetModule;

    public int moduleID;
    public PacketBuffer payload;

    public LoadNetModule(PacketBuffer buffer) {
        this.readPacketData(buffer);
    }

    @Override
    public void readPacketData(PacketBuffer buffer) {
        moduleID = Short.toUnsignedInt(buffer.readShort());
        payload = buffer.toUnreadOnly();
    }

    @Override
    public void processPacket(IServerPlayNetHandler handler) {

    }

    @Override
    public Packets getEnum() {
        return enumRepresentation;
    }
}
