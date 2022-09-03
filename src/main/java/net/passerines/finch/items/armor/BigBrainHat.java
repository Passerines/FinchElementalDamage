package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BigBrainHat extends FinchArmor {

    public BigBrainHat() {
        super("BigBrainHat");
        this.defense = 25;
        this.health = 5;
        this.mana = 1000;

    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bBig Brain Hat"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "HOLY CRAP IT MAKES YOU SMARTER");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
