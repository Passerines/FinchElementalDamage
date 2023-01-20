package net.passerines.finch.aItems.utilities;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchEquipment;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.recipebook.RecipeBookDefault;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LifeCrystal extends FinchEquipment implements Listener, FinchCraftableItem {
    Cooldown cd = new Cooldown<>(10);
    public LifeCrystal() {
        super("LifeCrystal", 2);
        displayName = Chat.formatC("&8Life Crystal");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Mmmm... tasty");
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(vPlayer.getMana() >= 50 && event.getAction().isRightClick() && id.equals(Util.getId(item)) && cd.isOffCooldown(player)){
            vPlayer.setMana(vPlayer.getMana() - 50);
            vPlayer.setHealth(vPlayer.getHealthMax() + (vPlayer.getHealthMax()*0.1));
            String bar = Chat.format("&c-50 &bMana");
            Chat.sendActionBar(player, bar);
            cd.add(player);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "AAA", "AAA" , diamond);
        finchRecipe.addRecipe();
    }
}
