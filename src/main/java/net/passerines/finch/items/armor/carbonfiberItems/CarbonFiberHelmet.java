package net.passerines.finch.items.armor.carbonfiberItems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+10")));
        lore.add(Component.text(Chat.format("&aDefense: &f+100")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
