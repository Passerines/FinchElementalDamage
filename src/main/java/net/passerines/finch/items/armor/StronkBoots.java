package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StronkBoots extends FinchArmor {

    public StronkBoots() {
        super("StronkBoots");
        this.defense = 6900;
        this.health = 420000;
        this.attack = 2100;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cStronk Boots"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
