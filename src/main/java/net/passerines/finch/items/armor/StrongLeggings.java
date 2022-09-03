package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StrongLeggings extends FinchArmor {

    public StrongLeggings() {
        super("StrongLeggings");
        this.defense = 150;
        this.health = 500;
        this.damage = 50;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cStrong Leggings"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
