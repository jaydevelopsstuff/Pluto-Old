package net.jay.pluto.net.handlers;

import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.Terraria;
import net.jay.pluto.data.enums.CharacterSkinVariant;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.item.Item;
import net.jay.pluto.net.Client;
import net.jay.pluto.net.packet.packets.both.*;
import net.jay.pluto.net.packet.packets.client.ClientUUID;
import net.jay.pluto.net.packet.packets.client.ConnectRequest;
import net.jay.pluto.net.packet.packets.client.PasswordSend;
import net.jay.pluto.net.packet.packets.client.RequestWorldData;
import net.jay.pluto.net.packet.packets.server.ContinueConnecting;
import net.jay.pluto.util.PlayerBuilder;

import java.io.IOException;
import java.util.Arrays;

public class ServerLoginNetHandler implements IServerLoginNetHandler, Access {
    private final Client client;
    private final PlayerBuilder playerBuilder;

    public ServerLoginNetHandler(Client client) {
        this.client = client;
        this.playerBuilder = new PlayerBuilder(client);
    }

    @Override
    public void processConnectRequest(ConnectRequest request) {
        if(!request.version.equals("Terraria" + Terraria.currentRelease)) {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            client.sendPacket(new ContinueConnecting((byte)client.getClientID()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processPasswordSend(PasswordSend packet) {

    }

    @Override
    public void processPlayerInfo(PlayerInfo packet) {
        CharacterInfo characterInfo = null;
        // TODO Check all the information sent and confirm it's valid
        try {
           characterInfo = new CharacterInfo(CharacterSkinVariant.fromID(packet.playerID), packet.hairType, packet.hairColor, packet.skinColor, packet.eyeColor, packet.shirtColor, packet.underShirtColor);
        } catch(IllegalArgumentException ignored) {
            // Client sent bad ID
            try {
                client.disconnect("Bad packet");
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        playerBuilder.setName(packet.name);
        if(characterInfo == null) return;
        playerBuilder.setCharacterInfo(characterInfo);
    }

    @Override
    public void processClientUUID(ClientUUID packet) {
        if(packet.UUID.length() == 0) return;
        playerBuilder.setUuid(packet.UUID);
    }

    @Override
    public void processPlayerHP(PlayerHP packet) {
        if(packet.HP < 0 || packet.maxHP < 0 || packet.HP > 600 || packet.maxHP > 600) {
            try {
                client.disconnect("Invalid packet");
            } catch(IOException e) {
                e.printStackTrace();
            }
            return;
        }
        playerBuilder.setHP(packet.HP);
        playerBuilder.setMaxHP(packet.maxHP);
    }

    @Override
    public void processManaEffect(ManaEffect packet) {
        if(packet.manaAmount < 0 || packet.manaAmount > 400) {
            try {
                client.disconnect("Invalid packet");
            } catch(IOException e) {
                e.printStackTrace();
            }
            return;
        }
        playerBuilder.setMana(packet.manaAmount);
    }

    @Override
    public void processPlayerBuff(PlayerBuff packet) {

    }

    @Override
    public void processPlayerSlot(PlayerSlot packet) {
        short slot = packet.slot;
        Item item;
        try {
            item = new Item(packet.itemNetID, packet.prefix, packet.stack);
        } catch(IllegalArgumentException exception) {
            // Until we get all terraria items in the Items enum this is gonna show up
            // Later on this will cause the player to be kicked for sending suspicious packets or smthn
            mainLogger.debug(client.getIP() + " sent an invalid item in PlayerSlot packet");
            return;
        }
        // Inventory
        if(slot < 58) playerBuilder.getInventory().setItem(packet.slot, item);
        // TODO Figure the rest of this out
    }

    @Override
    public void processRequestWorldData(RequestWorldData packet) {

    }

    @Override
    public void handleDisconnect() {
        server.freePlayerID(client.getClientID());
    }
}
