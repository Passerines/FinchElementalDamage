package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.CustomPlayerDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class DamageHandler implements Listener {

    public DamageHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        //Converts entity damage by entity to elemental damage
        if(event.getEntity() instanceof Player) {
            ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.FIRE, (int) event.getDamage());
            elementalDamage.apply();
            event.setDamage(0);
        }
    }
    @EventHandler
    public void onDamageEvent(EntityDamageEvent event){
        switch (event.getCause()){
            case VOID, DRAGON_BREATH, WITHER -> {
                new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.DARK, (int) event.getDamage()).apply();
                event.setDamage(0);
            }
            case FALL, FALLING_BLOCK, POISON, STARVATION, SUFFOCATION, THORNS, CRAMMING, FLY_INTO_WALL -> {
                new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.EARTH, (int) event.getDamage()).apply();
                event.setDamage(0);
            }
            case FIRE, LAVA, HOT_FLOOR, FIRE_TICK -> {
                new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.FIRE, (int) event.getDamage()).apply();
                event.setDamage(0);
            }
            case DROWNING, FREEZE -> {
                new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.WATER, (int) event.getDamage()).apply();
                event.setDamage(0);
            }
            case LIGHTNING -> {
                new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.ELECTRO, (int) event.getDamage()).apply();
                event.setDamage(0);}
        }
    }
    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity attacker = event.getAttacker();
        Entity victim = event.getVictim();
        if(victim instanceof Player targetPlayer){
            PlayerData vPlayerData = PlayerMap.PLAYERS.get((targetPlayer));
            int finalDamage = event.getDamage();
            vPlayerData.setHealth(vPlayerData.getHealth()  - (finalDamage - finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500))));
            int damageTaken = (int) ((finalDamage - finalDamage * (vPlayerData.getDefense()/ (vPlayerData.getDefense() + 500))) * event.getElement().getElementalMultiplier());
            attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());
            if(vPlayerData.getHealth() <= 0){
                CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((Player) victim);
                deathEvent.apply();
            }
        }
        /*else if(victim instanceof LivingEntity targetEntity){
            int mobFinalDamage = event.getDamage();
            int mobDamageTaken = (int) ((mobFinalDamage - mobFinalDamage * event.getElement().getElementalMultiplier()));
            targetEntity.damage(mobDamageTaken, attacker);
        }*/
    }

    @EventHandler
    public void onCustomPlayerDeath(CustomPlayerDeathEvent event){
        Player victim = event.getDeadVictim();
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
        victim.sendMessage(Chat.format("&4You Died!"));
        Chat.sendTitle(victim, "&4You Died!");
        victim.teleport(victim.getWorld().getSpawnLocation());
        vPlayerData.setHealth(vPlayerData.getHealthMax());
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
