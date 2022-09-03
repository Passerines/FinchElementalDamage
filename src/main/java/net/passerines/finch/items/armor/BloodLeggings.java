package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BloodLeggings extends FinchArmor {

    public BloodLeggings() {
        super("BloodLeggings");
        this.defense = 150;
        this.health = -10;
        this.damage = 5;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Leggings"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
