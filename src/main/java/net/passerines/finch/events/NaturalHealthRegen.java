package net.passerines.finch.events;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class NaturalHealthRegen {
    public NaturalHealthRegen(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            for(Player player : Bukkit.getOnlinePlayers()) {
                PlayerData playerData = PLAYERS.get(player);
                int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * 0.01);
                playerData.setHealth(newHealth);
            }
        }, 0, 100);
    }
}
