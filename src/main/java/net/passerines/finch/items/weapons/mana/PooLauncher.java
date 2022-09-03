package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class PooLauncher extends FinchWeapon implements Listener {

    public PooLauncher() {
        super("PooLauncher");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && id.equals( Util.getId(item))){
            Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
            Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
            fireball.setYield(690);
            fireball.setShooter(player);
            String bar = Chat.format("You asked for this.");
            Chat.sendActionBar(player, bar);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.SNOWBALL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTHE POO LAUNCHER"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
