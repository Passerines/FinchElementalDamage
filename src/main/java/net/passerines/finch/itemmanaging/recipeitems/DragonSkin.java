package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DragonSkin extends FinchItem {
    public DragonSkin() {
        super("DragonSkin");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.COAL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fDragon Skin"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7A very strong and sturdy material"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}

