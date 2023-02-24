package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Ectoplasm extends FinchItem implements FinchCraftableItem {
    public Ectoplasm() {
        super("Ectoplasm");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bEctoplasm"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7They linger"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BBB", "BAB", "BBB" , totem, new ItemStack(Material.DIAMOND));
        finchRecipe.addRecipe();
    }
}

