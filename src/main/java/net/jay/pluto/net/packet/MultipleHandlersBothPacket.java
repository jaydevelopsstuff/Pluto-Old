package net.jay.pluto.net.packet;

import net.jay.pluto.net.handlers.NetHandler;

public interface MultipleHandlersBothPacket<T extends NetHandler, V extends NetHandler> extends MultipleHandlersCPacket<T, V>, SPacket {
}
