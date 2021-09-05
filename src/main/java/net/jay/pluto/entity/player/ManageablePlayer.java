package net.jay.pluto.entity.player;

import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.net.Client;

import java.io.IOException;

public class ManageablePlayer extends BasicPlayer implements Access {
    private final Client connectedClient;

    public ManageablePlayer(Client client, BasicPlayer player) {
        super(player.getID(), player.getUuid(), player.getName(), player.getCharacterInfo(), player.getInventory(), player.getArmor(), player.getAccessories());
        this.connectedClient = client;
    }

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

    public Client getConnectedClient() {
        return connectedClient;
    }

    public static ManageablePlayer toManageable(Client client, BasicPlayer player) {
        return new ManageablePlayer(client, player);
    }
}
