package net.passerines.finch.events;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class DamageHandler implements Listener {
    public DamageHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void takeDamage(EntityDamageByEntityEvent hit){
        Entity victim = hit.getEntity();
        Entity attacker = hit.getDamager();
        if(victim instanceof Player){
            PlayerData vPlayerData = PlayerMap.PLAYERS.get(((Player) hit.getEntity()));
            int finalDamage = (int) hit.getDamage();
            vPlayerData.setHealth(vPlayerData.getHealth()  - (finalDamage - finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500))));
            int damageTaken = finalDamage - finalDamage * (vPlayerData.getDefense()/ (vPlayerData.getDefense() + 500));
            attacker.sendMessage("Damage Dealt: " + damageTaken);
            victim.sendMessage("Damage Taken: " + damageTaken);
        }
    }
}
