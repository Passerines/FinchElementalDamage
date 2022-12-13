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

public class LuckEssence extends FinchItem implements FinchCraftableItem {
    public LuckEssence() {
        super("LuckEssence");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Luck Essence"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Makes you feel more fortunate"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack scale = ItemManager.ITEM_HASH_MAP.get("GoldEssence").getItem();
        ItemStack totem =ItemManager.ITEM_HASH_MAP.get("MahiMahiScale").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BBB", "BAB", "CCC" , totem, scale, new ItemStack(Material.BLAZE_POWDER));
        finchRecipe.addRecipe();
    }
}

