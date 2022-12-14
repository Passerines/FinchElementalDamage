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
                if(player.getFoodLevel() == 20) {
                    if(player.getSaturation() > 0) {
                        int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.00015 * playerData.getHealthRegen()));
                        int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.000225 * playerData.getManaRegen()));
                        playerData.setMana(newMana);
                        playerData.setHealth(newHealth);
                        player.setSaturation(player.getSaturation() - 1);
                    }
                    else if(player.getSaturation() == 0){
                        int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.00015 * playerData.getHealthRegen()));
                        int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.000225 * playerData.getManaRegen()));
                        playerData.setMana(newMana);
                        playerData.setHealth(newHealth);
                        player.setFoodLevel(player.getFoodLevel() - 1);
                    }
                }
                else {
                    int newHealth = (int) Math.min(playerData.getHealthMax(), playerData.getHealth() + playerData.getHealthMax() * (0.0001 * playerData.getHealthRegen()));
                    int newMana = (int) Math.min(playerData.getManaMax(), playerData.getMana() + playerData.getManaMax() * (0.00015 * playerData.getManaRegen()));
                    playerData.setMana(newMana);
                    playerData.setHealth(newHealth);
                    player.setSaturation(player.getSaturation() - 1);
                }
            }
        }, 0, 20);
    }
}
