package net.passerines.finch.events;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class NaturalHealthRegen {
    private Player player;

    public NaturalHealthRegen(){

        if(){
            Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
                PLAYERS.setHealth(PLAYERS.get(player).getHealth() + PLAYERS.get(player).getHealth()*0.01);
            }, 0, 100);
        }
    }
}
