package net.jay.pluto.entity;

import net.jay.pluto.Access;
import net.jay.pluto.net.Client;

import java.io.IOException;

public class ManageablePlayer extends Player implements Access {
    private Client connectedClient;

    public void boot(String reason) throws IOException {
        disconnect(reason);
    }

    public void disconnect(String reason) throws IOException {
        connectedClient.disconnect(reason);
    }

    public void boot() throws IOException {
        disconnect();
    }

    public void disconnect() throws IOException {
        connectedClient.disconnect();
        server.getPlayerManager().removePlayer(this);
    }
}
