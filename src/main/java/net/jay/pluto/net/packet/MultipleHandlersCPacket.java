package net.jay.pluto.net.packet;

import net.jay.pluto.net.handlers.NetHandler;

/**
 * For packets that can be sent during login or after
 * @param <T> Login Handler
 * @param <V> Play Handler
 */
public interface MultipleHandlersCPacket<T extends NetHandler, V extends NetHandler> extends Packet {
}
