package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.CustomPlayerDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
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

public class DamageHandler implements Listener {

    public DamageHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        //Converts entity damage by entity to elemental damage
        ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.FIRE, (int) event.getDamage());
        elementalDamage.apply();
        event.setCancelled(true);
    }
    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity attacker = event.getAttacker();
        Entity victim = event.getVictim();
        if(victim instanceof Player){
            PlayerData vPlayerData = PlayerMap.PLAYERS.get(((Player) event.getVictim()));
            int finalDamage = (int) event.getDamage();
            vPlayerData.setHealth(vPlayerData.getHealth()  - (finalDamage - finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500))));
            int damageTaken = (int) ((finalDamage - finalDamage * (vPlayerData.getDefense()/ (vPlayerData.getDefense() + 500))) * event.getElement().getElementalMultiplier());
            attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());
            if(vPlayerData.getHealth() <= 0){
                CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((Player) victim);
                deathEvent.apply();
            }
        }
    }

    @EventHandler
    public void onCustomPlayerDeath(CustomPlayerDeathEvent event){
        Entity victim = event.getDeadVictim();
        victim.sendMessage(Chat.format("&4U died!"));
        victim.teleport(victim.getWorld().getSpawnLocation());
    }

    /*@EventHandler
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
    }*/
}
