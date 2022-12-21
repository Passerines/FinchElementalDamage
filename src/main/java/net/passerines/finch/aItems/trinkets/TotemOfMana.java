package net.passerines.finch.aItems.trinkets;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TotemOfMana extends FinchTrinkets implements FinchCraftableItem {
    public TotemOfMana() {
        super("TotemOfMana");
        this.mana = 100;
        displayName = Chat.formatC("&aTotem of Mana");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(402);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack spruceLog = new ItemStack(Material.SPRUCE_LOG);
        ItemStack ritualEssence = ItemManager.ITEM_HASH_MAP.get("RitualEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , spruceLog, ritualEssence);
        finchRecipe.addRecipe();
    }
}
