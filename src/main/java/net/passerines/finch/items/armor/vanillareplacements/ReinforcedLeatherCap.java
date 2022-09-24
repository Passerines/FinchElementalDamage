package net.passerines.finch.items.armor.vanillareplacements;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReinforcedLeatherCap extends FinchArmor implements FinchCraftableItem {
    public ReinforcedLeatherCap() {
        super("ReinforcedLeatherCap");
        this.defense = 45;
        this.health = 10;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fReinforced Leather Cap"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+10")));
        lore.add(Component.text(Chat.format("&aDefense: &f+45")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack leather = ItemManager.ITEM_HASH_MAP.get("ReinforcedLeather").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , leather);
        finchRecipe.addRecipe();
    }
}
