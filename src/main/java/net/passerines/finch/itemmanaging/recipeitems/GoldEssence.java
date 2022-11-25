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

public class GoldEssence extends FinchItem implements FinchCraftableItem {
    public GoldEssence() {
        super("GoldEssence");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GLOWSTONE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Gold Essence"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Glowing a strange yellow tint"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack nugget = new ItemStack(Material.GOLD_NUGGET);
        ItemStack ingot = new ItemStack(Material.GOLD_INGOT);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , nugget, ingot);
        finchRecipe.addRecipe();
    }
}

