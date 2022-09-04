package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SniperHelmet extends FinchArmor {
    public SniperHelmet() {
        super("SniperHelmet");
        this.defense = 5;
        this.health = 115;
        this.damage = 30;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&eSniper Helmet"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}

