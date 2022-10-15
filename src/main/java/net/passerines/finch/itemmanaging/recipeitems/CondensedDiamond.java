package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CondensedDiamond extends FinchItem implements FinchCraftableItem {
    public CondensedDiamond() {
        super("CondensedDiamond");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bCondensed Diamond"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7A Glowing Diamond"));
        itemMeta.lore(lore);
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK);
        ItemStack condensedEnergy = ItemManager.ITEM_HASH_MAP.get("CondensedEnergy").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "CAC", "BCB", "CAC" , diamondBlock, new ItemStack(Material.GLOW_BERRIES), condensedEnergy);
        finchRecipe.addRecipe();
    }
}

