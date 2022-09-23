package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StronkLeggings extends FinchArmor {

    public StronkLeggings() {
        super("StronkLeggings");
        this.defense = 1500;
        this.health = 1000;
        this.damage = 300;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cStronk Leggings"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
