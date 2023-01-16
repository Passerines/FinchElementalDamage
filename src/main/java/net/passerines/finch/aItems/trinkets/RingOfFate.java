package net.passerines.finch.aItems.trinkets;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.itemmanaging.recipeitems.Fate;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RingOfFate extends FinchTrinkets implements FinchCraftableItem {
    public RingOfFate() {
        super("RingOfFate");
        this.healthRegen = 6;
        this.critChance = 6;
        this.dark = 6;
        displayName = Chat.formatC("&bRingOfFate");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&7It Seems Like The World Revolves Around This Strange Object.");
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack fate = new ItemStack(Material.DAMAGED_ANVIL);
        ItemStack ritualEssence = ItemManager.ITEM_HASH_MAP.get("RitualEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BAB", "BBB", "BAB" , fate, ritualEssence);
        finchRecipe.addRecipe();
    }
}
