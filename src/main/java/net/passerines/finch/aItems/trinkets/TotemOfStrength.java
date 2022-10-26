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

public class TotemOfStrength extends FinchTrinkets implements FinchCraftableItem {
    public TotemOfStrength() {
        super("TotemOfStrength");
        this.damage = 5;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTotem of Strength"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cDamage: &f+" + this.damage)));
        itemMeta.lore(lore);
        itemMeta.setCustomModelData(199);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack acaciaLog = new ItemStack(Material.ACACIA_LOG);
        ItemStack ritualEssence = ItemManager.ITEM_HASH_MAP.get("RitualEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , acaciaLog, ritualEssence);
        finchRecipe.addRecipe();
    }
}
