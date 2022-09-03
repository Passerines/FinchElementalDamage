package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CarbonFiberChestplate extends FinchArmor {
    public CarbonFiberChestplate() {
        super("CarbonFiberChestplate");
        this.defense = 160;
        this.health = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fCarbon Fiber Chestplate"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
