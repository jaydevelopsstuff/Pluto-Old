package net.jay.pluto.entity.player;

import net.jay.pluto.Access;
import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.net.Client;

import java.io.IOException;

public class ManageablePlayer extends BasicPlayer implements Access {
    private Client connectedClient;

    public ManageablePlayer(int ID, PlayerInventory inventory, PlayerArmor armor, PlayerAccessories accessories) {
        super(ID, inventory, armor, accessories);
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
}
