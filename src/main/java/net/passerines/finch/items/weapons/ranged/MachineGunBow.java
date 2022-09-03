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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MachineGunBow extends FinchWeapon implements Listener {

    public MachineGunBow() {
        super("MachineGunBow");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && id.equals(Util.getId(item))) {
            Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(0.7)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
            Arrow arrow = (Arrow) loc.getWorld().spawnEntity(loc, EntityType.ARROW);
            arrow.setVelocity(loc.getDirection().normalize().multiply(7));
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cMachineGunBow"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
