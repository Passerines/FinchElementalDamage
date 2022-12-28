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

public class GlacialGem extends FinchItem implements FinchCraftableItem {
    public GlacialGem() {
        super("GlacialGem");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&9Glacial Gem"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Gem crafted of fine ice"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack ice = new ItemStack(Material.ICE);
        ItemStack blueice = new ItemStack(Material.BLUE_ICE);
        ItemStack packedice = new ItemStack(Material.PACKED_ICE);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BBB", "BAB", "CCC" , ice, blueice, packedice);
        finchRecipe.addRecipe();
    }
}

