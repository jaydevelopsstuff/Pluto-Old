package net.jay.pluto.net.handlers;

import net.jay.pluto.PlutoServer;
import net.jay.pluto.container.Chest;
import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.Terraria;
import net.jay.pluto.data.enums.CharacterSkinVariant;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.entity.player.PlayerFactory;
import net.jay.pluto.entity.tileentity.TileEntity;
import net.jay.pluto.item.Item;
import net.jay.pluto.net.Client;
import net.jay.pluto.net.packet.packets.both.*;
import net.jay.pluto.net.packet.packets.client.*;
import net.jay.pluto.net.packet.packets.server.*;
import net.jay.pluto.world.World;
import net.jay.pluto.world.sign.Sign;
import net.jay.pluto.world.tile.Tile;

import java.io.IOException;

public class ServerLoginNetHandler implements IServerLoginNetHandler, Access {
    private final Client client;
    private final PlayerFactory playerFactory;

    public ServerLoginNetHandler(Client client) {
        this.client = client;
        this.playerFactory = new PlayerFactory();
    }

    @Override
    public void processConnectRequest(ConnectRequest request) {
        if(!request.version.equals("Terraria" + Terraria.currentRelease)) {
            client.disconnect();
            return;
        }
        client.sendPacket(new ContinueConnecting((byte)client.getClientID()));
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
            client.disconnect("Bad packet");
        }
        playerFactory.setName(packet.name);
        if(characterInfo == null) return;
        playerFactory.setCharacterInfo(characterInfo);
    }

    @Override
    public void processClientUUID(ClientUUID packet) {
        playerFactory.setUuid(packet.uuid);
    }

    @Override
    public void processPlayerHP(PlayerHP packet) {
        if(packet.hp < 0 || packet.maxHp < 0 || packet.hp > 600 || packet.maxHp > 600) {
            client.disconnect("Invalid packet");
            return;
        }
        playerFactory.setHp(packet.hp);
        playerFactory.setMaxHp(packet.maxHp);
    }

    @Override
    public void processManaEffect(ManaEffect packet) {
        if(packet.manaAmount < 0 || packet.manaAmount > 400) {
            client.disconnect("Invalid packet");
            return;
        }
        playerFactory.setMana(packet.manaAmount);
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
        if(slot < 58) playerFactory.getInventory().setItem(packet.slot, item);
        // TODO Figure the rest of this out
    }

    @Override
    public void processRequestWorldData(RequestWorldData packet) {
        client.sendPacket(new WorldInfo(server.getWorld()));
    }

    @Override
    public void processRequestEssentialTiles(RequestEssentialTiles packet) {
        client.setLoggedIn(true);
        client.getConnectionManager().setNetHandler(new ServerPlayNetHandler(client));

        World world = PlutoServer.getInstance().getWorld();

        for(int i = 0; i < world.getMaxTilesX() / 100; i++) {
            Tile[][] tiles = world.getTiles(i, 0, 100, world.getMaxTilesY());
            client.sendPacket(new TileSection(true, i * 100, 0, (short)100, (short)world.getMaxTilesY(), tiles, new Chest[0], new Sign[0][0], new TileEntity[0]));
        }

        client.sendPacket(new TileSectionFrame((short)0, (short)0, (short)world.getMaxSectionX(), (short)world.getMaxSectionY()));

        client.sendPacket(new CompleteConnectionAndSpawn());
    }


    @Override
    public void handleDisconnect() {
        server.freePlayerID(client.getClientID());
    }
}
