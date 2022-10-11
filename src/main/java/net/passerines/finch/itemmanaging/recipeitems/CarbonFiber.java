package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CarbonFiber extends FinchItem implements FinchCraftableItem {
    public CarbonFiber() {
        super("CarbonFiber");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.COAL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cfCarbon-Fiber"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7It's Very tough"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemStack coal = new ItemStack(Material.COAL);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "ABA", "ABA", "   " , coal, diamond);
        finchRecipe.addRecipe();
    }
}

