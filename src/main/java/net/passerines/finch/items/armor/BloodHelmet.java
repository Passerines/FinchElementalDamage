package net.passerines.finch.items.armor;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BloodHelmet extends FinchArmor {

    public BloodHelmet() {
        super("BloodHelmet");
        this.defense = 100;
        this.health = -10;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Helmet"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
