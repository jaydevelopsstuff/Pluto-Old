package net.jay.pluto.managers;

import net.jay.pluto.entity.player.ManageablePlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements Manager {
    private final int maxPlayers;
    private final List<ManageablePlayer> players = new ArrayList<>();

    public PlayerManager(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void updatePlayerIDs(int removedPlayerID) {
        for(ManageablePlayer player : players) {
            //if(player.getID() > removedPlayerID) player.setID(player.getID() - 1);
        }
    }

    public ManageablePlayer getPlayerByID(int ID) {
        for(ManageablePlayer player : players) {
            if(player.getID() == ID) return player;
        }
        return null;
    }

    public void removePlayer(ManageablePlayer player) {
        players.remove(player);
    }

    public int getOpenPlayerSlots() {
        return maxPlayers - players.size();
    }

    @Override
    public void shutdown() {

    }
}
