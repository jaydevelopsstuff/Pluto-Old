package net.jay.pluto.entity.player;

import lombok.Getter;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.net.Client;

import java.io.IOException;

public class ManageablePlayer extends BasicPlayer implements Access {
    @Getter
    private final Client connectedClient;

    public ManageablePlayer(Client client, BasicPlayer player) {
        super(player.getID(), player.getUuid(), player.getName(), player.getCharacterInfo(), player.getHp(), player.getMaxHp(), player.getMana(), player.getMaxMana(), player.getInventory(), player.getArmor(), player.getAccessories());
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
}
