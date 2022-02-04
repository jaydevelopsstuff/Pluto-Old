package net.jay.pluto.net.handlers;

import lombok.RequiredArgsConstructor;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.net.Client;

@RequiredArgsConstructor
public class ServerPlayNetHandler implements IServerPlayNetHandler, Access {
    private final Client client;

    @Override
    public void handleDisconnect() {
        server.freePlayerID(client.getClientID());
    }
}
