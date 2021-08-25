package net.jay.pluto.net.packet;

import net.jay.pluto.net.handlers.NetHandler;

public interface BothPacket<T extends NetHandler> extends CPacket<T>, SPacket {
}
