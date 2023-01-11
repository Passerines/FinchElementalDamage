package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractDetector implements Listener {
    public InteractDetector(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if(Util.getId(item) != null){
            Util.getFinchItem(item).onClick(event);
        }
    }
    @EventHandler
    public void cancelHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            ItemStack item = player.getInventory().getItemInMainHand();
            if(Util.getId(item) != null){
                Util.getFinchItem(item).onClick(new PlayerInteractEvent(player, Action.LEFT_CLICK_AIR, item, null, BlockFace.SELF));
            }
        }
    }
}
