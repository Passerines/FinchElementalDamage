package net.passerines.finch.aItems.trinkets;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.itemmanaging.recipeitems.CondensedDiamond;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GauntletOfSacrifice extends FinchTrinkets implements FinchCraftableItem {
    public GauntletOfSacrifice() {
        super("GauntletOfSacrifice");
        this.health = -150;
        this.strength = 30;
        displayName = Chat.formatC("&4Gauntlet of Sacrifice");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack condensedDiamond = ItemManager.ITEM_HASH_MAP.get("CondensedDiamond").getItem();
        ItemStack condensedEnergy = ItemManager.ITEM_HASH_MAP.get("CondensedEnergy").getItem();
        ItemStack apple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ACA", "ABA" , condensedDiamond, condensedEnergy,apple);
        finchRecipe.addRecipe();
    }
}
