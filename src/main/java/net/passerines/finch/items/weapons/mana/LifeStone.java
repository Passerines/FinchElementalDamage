package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class LifeStone extends FinchWeapon implements Listener {

    public LifeStone() {
        super("LifeStone");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(vPlayer.getMana() >= 500 && event.getAction().isRightClick() && id.equals( Util.getId(item))){
            vPlayer.setMana(vPlayer.getMana() - 500);
            vPlayer.setHealth(vPlayer.getHealth() + 250);
            String bar = Chat.format("&c-500 &bMana");
            Chat.sendActionBar(player, bar);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLD_ORE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Life Stone"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Converts Your Mana to Health at Half Efficiency");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
