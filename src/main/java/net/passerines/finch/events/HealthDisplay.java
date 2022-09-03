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

public class HealthDisplay implements Listener {
    public HealthDisplay(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            for(Player player : Bukkit.getOnlinePlayers()) {
                updateActionBar(player);
            }
        }, 0, 30);
    }

    @EventHandler
    public void sendBarOnDamage(ElementalDamageEvent event){
        Entity victim = event.getVictim();
        if(victim instanceof Player){
            updateActionBar((Player) victim);
        }
    }


    public static void updateActionBar(Player player){
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((player));
        if(vPlayerData!=null) {
            int defense = vPlayerData.getDefense();
            double maxHealth = vPlayerData.getHealthMax();
            double currentHealth = vPlayerData.getHealth();
            int maxMana = vPlayerData.getManaMax();
            int currentMana = vPlayerData.getMana();
            String bar = Chat.format("&cHealth: " + currentHealth + "/" + maxHealth + "    &aDefense: " + defense + "    &bMana: " + currentMana + "/" + maxMana);
            Chat.sendActionBar(player, bar);
        }
    }
}
