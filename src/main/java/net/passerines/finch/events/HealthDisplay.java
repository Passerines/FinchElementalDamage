package net.passerines.finch.events;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class HealthDisplay implements Listener {
    public HealthDisplay(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            for(Player player : Bukkit.getOnlinePlayers()) {
                displayHealth(player);
            }
        }, 0, 30);
    }

    @EventHandler
    public void sendBarOnDamage(ElementalDamageEvent event){
        Entity victim = event.getVictim();
        if(victim instanceof Player){
            displayHealth((Player) victim);
        }
    }


    public static void displayHealth(Player player){
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((player));
        int maxHealth = vPlayerData.getHealthMax();
        int currentHealth = vPlayerData.getHealth();
        int maxMana = vPlayerData.getManaMax();
        int currentMana = vPlayerData.getMana();
        String bar = Chat.format("&cHealth: " + currentHealth + "/" + maxHealth + "               &bMana: " + currentMana + "/" + maxMana);
        Chat.sendActionBar(player, bar);
    }
}
