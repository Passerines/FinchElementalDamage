package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MagicBloodIron extends FinchItem implements FinchCraftableItem {
    public MagicBloodIron() {
        super("MagicBloodIron");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cMagical Blood-Iron"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Iron infused with blood and extra magic"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "BAB", "BAB", "   " , iron, new ItemStack(Material.REDSTONE));
        finchRecipe.addRecipe();
    }
}

