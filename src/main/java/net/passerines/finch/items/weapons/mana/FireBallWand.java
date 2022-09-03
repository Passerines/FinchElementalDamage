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


public class FireBallWand extends FinchWeapon implements Listener {

    public FireBallWand() {
        super("FireBallWand");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && vPlayer.getMana() >= 50 && id.equals( Util.getId(item))){
            Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
            Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
            fireball.setYield(8);
            fireball.setShooter(player);
            vPlayer.setMana(vPlayer.getMana() - 50);
            String bar = Chat.format("&c-50 &bMana " + vPlayer.getMana());
            Chat.sendActionBar(player, bar);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cFireBallWand"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
