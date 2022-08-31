package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BloodHelmet extends FinchItem {
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        return item;
    }
}
