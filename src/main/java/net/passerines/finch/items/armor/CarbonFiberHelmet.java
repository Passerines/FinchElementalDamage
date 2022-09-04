package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CarbonFiberHelmet extends FinchArmor {
    public CarbonFiberHelmet() {
        super("CarbonFiberHelmet");
        this.defense = 100;
        this.health = 10;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fCarbon Fiber Helmet"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
