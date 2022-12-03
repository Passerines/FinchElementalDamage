package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MahiMahiScale extends FinchItem {
    public MahiMahiScale() {
        super("MahiMahiScale");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fMahi Mahi Scales"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Worth just as much as an emerald because how tough"));
        lore.add(Chat.formatC("&7it is, can also be made into armor"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}

