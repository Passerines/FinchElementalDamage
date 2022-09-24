package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReinforcedLeather extends FinchItem implements FinchCraftableItem {
    public ReinforcedLeather() {
        super("ReinforcedLeather");
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fReinforced Leather"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("Leather that has been reinforced with iron")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemStack leather = new ItemStack(Material.LEATHER);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AB", "  ", "   " , iron, leather);
        finchRecipe.addRecipe();
    }
}
