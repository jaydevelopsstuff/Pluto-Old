package net.jay.pluto.entity.player;

import lombok.Getter;
import lombok.Setter;
import net.jay.pluto.container.PlayerAccessories;
import net.jay.pluto.container.PlayerArmor;
import net.jay.pluto.container.PlayerInventory;
import net.jay.pluto.data.holders.CharacterInfo;
import net.jay.pluto.item.Item;
import net.jay.pluto.net.Client;

import java.util.UUID;

@Getter
@Setter
public class PlayerFactory {
    private int ID;
    private UUID uuid;
    private String name;
    private CharacterInfo characterInfo;
    private int hp;
    private int maxHp;
    private int mana;
    private int maxMana;
    private PlayerInventory inventory = new PlayerInventory();
    private PlayerArmor armor = new PlayerArmor();
    private PlayerAccessories accessories = new PlayerAccessories();
    private Item trash = Item.Air;

    public BasicPlayer build() {
        return new BasicPlayer(ID, uuid, name, characterInfo, hp, maxHp, mana, maxMana, inventory, armor, accessories);
    }

    public static ManageablePlayer newManageable(Client connectedClient, BasicPlayer basicPlayer) {
        return new ManageablePlayer(connectedClient, basicPlayer);
    }
}
