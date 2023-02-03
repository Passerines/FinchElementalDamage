package net.passerines.finch.events;

import com.comphenix.protocol.injector.BukkitUnwrapper;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.players.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class NaturalHealthRegen implements Listener {
    private static final HashMap<Player, Integer> TIMER = new HashMap<>();
    public NaturalHealthRegen(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            for(Player player : Bukkit.getOnlinePlayers()) {
                PlayerData playerData = PLAYERS.get(player);
                if(player.getFoodLevel() == 20 && playerData.getHealth() < playerData.getHealthMax()) {
                    if(player.getSaturation() > 0) {
                        if(TIMER.getOrDefault(player, -2000) <= Bukkit.getCurrentTick() - 60){
                            int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.00015 * playerData.getHealthRegen()));
                            playerData.setHealth(newHealth);
                        }
                        int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.000225 * playerData.getManaRegen()));
                        playerData.setMana(newMana);
                        player.setSaturation(player.getSaturation() - 1);
                    }
                    else if(player.getSaturation() == 0){
                        if(TIMER.getOrDefault(player, -2000) <= Bukkit.getCurrentTick() - 60){
                            int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.00015 * playerData.getHealthRegen()));
                            playerData.setHealth(newHealth);
                        }
                        int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.000225 * playerData.getManaRegen()));
                        playerData.setMana(newMana);
                        player.setFoodLevel(player.getFoodLevel() - 1);
                    }
                }
                else {
                    if(TIMER.getOrDefault(player, -2000) <= Bukkit.getCurrentTick() - 60) {
                        int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.0001 * playerData.getHealthRegen()));
                        playerData.setHealth(newHealth);
                    }
                    int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.00015 * playerData.getManaRegen()));
                    playerData.setMana(newMana);
                }
            }
        }, 0, 20);
    }
    @EventHandler
    public void hitTimer(ElementalDamageEvent event){
        if(event.getVictim() instanceof Player player) {
            TIMER.put(player, Bukkit.getCurrentTick());
        }
    }
}
