package net.passerines.finch.events.handler;


import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.util.Vector;

public class DashHandler implements Listener {
    private final Cooldown<Player> cd = new Cooldown<>(18);
    public DashHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onDash(PlayerDropItemEvent itemEvent){
        Player player = itemEvent.getPlayer();
        if(cd.isOffCooldown(player) && player.getOpenInventory().getType() != InventoryType.CRAFTING){
            Vector vector = player.getVelocity();
            player.setVelocity(vector.multiply(2).setY(0));
            cd.add(player);
        }
        itemEvent.setCancelled(true);
    }
}
