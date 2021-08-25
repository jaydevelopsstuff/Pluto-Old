package net.jay.pluto.managers;

import net.jay.pluto.entity.ManageablePlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private final int maxPlayers;
    private final List<ManageablePlayer> players = new ArrayList<>();

    public PlayerManager(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void updatePlayerIDs(int removedPlayerID) {
        for(ManageablePlayer player : players) {
            // TODO
        }
    }

    public int getOpenPlayerSlots() {
        return maxPlayers - players.size();
    }
}
