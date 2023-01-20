package net.passerines.finch.events.handler;


import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class DashHandler implements Listener {
    private final Cooldown<Player> cd = new Cooldown<>(18);
    public DashHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onDash(PlayerToggleFlightEvent event){
        /* Player player = itemEvent.getPlayer();
        if(cd.isOffCooldown(player) && !(player.getOpenInventory() instanceof PlayerInventory)){
            Vector vector = player.getLocation().getDirection().normalize();
            player.setVelocity(vector.multiply(2).setY(0));
            cd.add(player);
        }
        itemEvent.setCancelled(true); */
        if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE){
            event.setCancelled(true);
            Player player = event.getPlayer();
            if(cd.isOffCooldown(player)){
                Vector vector = player.getLocation().getDirection().normalize();
                player.setVelocity(vector.multiply(1.5).setY(0));
                player.setAllowFlight(false);
                cd.add(player);
            }
        }
    }
    @EventHandler
    public void onJump(PlayerJumpEvent event){
        Player player = event.getPlayer();
        player.setAllowFlight(true);
    }
}
