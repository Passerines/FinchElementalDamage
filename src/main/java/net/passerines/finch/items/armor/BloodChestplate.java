package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BloodChestplate extends FinchArmor {

    public BloodChestplate() {
        super("BloodChestplate");
        this.defense = 150;
        this.health = -10;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Chestplate"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
