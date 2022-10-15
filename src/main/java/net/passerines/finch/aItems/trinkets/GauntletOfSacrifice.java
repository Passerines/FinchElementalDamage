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
        this.damage = 50;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Gauntlet Of Sacrifice"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cDamage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format("&4Health: &f+" + this.health )));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack condensedDiamond = ItemManager.ITEM_HASH_MAP.get("CondensedDiamond").getItem();
        ItemStack condensedEnergy = ItemManager.ITEM_HASH_MAP.get("CondensedEnergy").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , condensedDiamond, condensedEnergy);
        finchRecipe.addRecipe();
    }
}
