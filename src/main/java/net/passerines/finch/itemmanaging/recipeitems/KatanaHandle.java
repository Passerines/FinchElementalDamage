package net.passerines.finch.itemmanaging.recipeitems;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KatanaHandle extends FinchItem implements FinchCraftableItem, Listener {
    public KatanaHandle() {
        super("KatanaHandle");
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LIGHTNING_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Katana Handle"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Chat.formatC("&7A curved long handle"));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @EventHandler
    public void onClick(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (id.equals(Util.getId(event.getItemInHand()))) {
            event.setCancelled(true);
        }
    }
    @Override
    public void registerRecipe() {
        ItemStack scale = new ItemStack(Material.WHITE_WOOL);
        ItemStack wool = new ItemStack(Material.BLACK_WOOL);
        ItemStack totem = new ItemStack(Material.COPPER_INGOT);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, " A ", " B ", "AAA" , totem, scale);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe1 = new FinchRecipe(getItem(), "katanahandle1", " A ", " B ", "AAA" , totem, wool);
        finchRecipe1.addRecipe();
    }
}

