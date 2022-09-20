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
        this.damage = 2100000000;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cStronk Boots"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
