package net.passerines.finch.aItems.trinkets;

import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TotemOfStrength extends FinchTrinkets {
    public TotemOfStrength() {
        super("TotemOfStrength");
        this.health = 10;
        this.damage = 3;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTotem of Strength"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
