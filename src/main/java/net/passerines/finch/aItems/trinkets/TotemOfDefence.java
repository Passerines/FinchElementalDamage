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

public class TotemOfDefence extends FinchTrinkets implements FinchCraftableItem {
    public TotemOfDefence() {
        super("TotemOfDefence");
        this.defense = 50;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&aTotem of Defence"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&aDefence: &f+" + this.defense)));
        itemMeta.lore(lore);
        itemMeta.setCustomModelData(401);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack oakLog = new ItemStack(Material.OAK_LOG);
        ItemStack ritualEssence = ItemManager.ITEM_HASH_MAP.get("RitualEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , oakLog, ritualEssence);
        finchRecipe.addRecipe();
    }
}
