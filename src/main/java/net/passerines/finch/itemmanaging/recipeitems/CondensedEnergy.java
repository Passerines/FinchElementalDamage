package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class CondensedEnergy extends FinchItem implements FinchCraftableItem {
    public CondensedEnergy() {
        super("CondensedEnergy");
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();
        SkullMeta skullItemMeta = (SkullMeta) item.getItemMeta();
        skullItemMeta.setOwningPlayer(Bukkit.getOfflinePlayer("mochhi"));
        itemMeta.displayName(Chat.formatC("&bCondensed Energy"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7Enough Energy To Make A Nuclear Bomb!"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        item.setItemMeta(skullItemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "ABA", "AAA" , new ItemStack(Material.GLOW_BERRIES), new ItemStack(Material.SOUL_SAND));
        finchRecipe.addRecipe();
    }
}

