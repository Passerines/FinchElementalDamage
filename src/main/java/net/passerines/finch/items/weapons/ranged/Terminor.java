package net.passerines.finch.items.weapons.ranged;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Terminor extends FinchWeapon implements Listener {

    public Terminor() {
        super("Terminor");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && id.equals(Util.getId(item))) {
            Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(0.7)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
            LightningStrike arrow = (LightningStrike) loc.getWorld().spawnEntity(loc, EntityType.LIGHTNING);
            arrow.setVelocity(loc.getDirection().normalize().multiply(7));
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTerminor"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}