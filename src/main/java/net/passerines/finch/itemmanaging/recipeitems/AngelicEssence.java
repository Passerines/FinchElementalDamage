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

public class AngelicEssence extends FinchItem implements FinchCraftableItem {
    public AngelicEssence() {
        super("AngelicEssence");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Angelic Essence"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7The things divine being are made of"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack scale = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BBB", "BAB", "CCC" , totem, scale, new ItemStack(Material.BLAZE_POWDER));
        finchRecipe.addRecipe();
    }
}

