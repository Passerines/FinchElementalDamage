package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.CustomEntityDeathEvent;
import net.passerines.finch.events.CustomPlayerDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import static net.passerines.finch.players.PlayerMap.PLAYERS;
import static net.passerines.finch.entity.EntityMap.ENTITIES;

public class DamageHandler implements Listener {

    public DamageHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());

    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //Converts entity damage by entity to elemental damage
        ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.UNDEAD, (int) event.getDamage());
        elementalDamage.apply();
        event.setDamage(0);
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent event) {
        switch (event.getCause()) {
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
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity attacker = event.getAttacker();
        Entity victim = event.getVictim();
        if (victim instanceof Player) {
            PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
            int finalDamage = event.getDamage();
            int damageTaken = (int) ((finalDamage - (finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500.0))) * event.getElement().getElementalMultiplier()));

            if (attacker instanceof Player) {
                damageTaken = damageTaken + PLAYERS.get(attacker).getDamage();
            }
            else if (attacker instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    damageTaken = damageTaken + PlayerMap.PLAYERS.get(player).getDamage();
                }
            }
            else if (attacker instanceof LivingEntity entity) {
                damageTaken = damageTaken + EntityMap.ENTITIES.get(entity).getDamage();
            }

            vPlayerData.setHealth(vPlayerData.getHealth() - damageTaken);

            victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());

            if (attacker instanceof Player) {
                attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            }

            if (vPlayerData.getHealth() <= 0) {
                CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((Player) victim);
                deathEvent.apply();
            }
        }
        else if (victim instanceof LivingEntity) {
            EntityData vEntityData = EntityMap.ENTITIES.get(victim);
            int mobDamage = event.getDamage();
            int mobDamageTaken = (int) (mobDamage - mobDamage * (vEntityData.getDefense() / (vEntityData.getDefense() + 500.0)));

            if(attacker instanceof Player) {
                attacker.sendMessage("Damage Dealt: " + mobDamageTaken + " Element: " + event.getElement());
            }
            else if(attacker instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    mobDamageTaken = mobDamageTaken + PlayerMap.PLAYERS.get(player).getDamage();
                }
            }

            vEntityData.setHealth(vEntityData.getHealth() - mobDamageTaken);

            if(vEntityData.getHealth() >= 0){
                CustomEntityDeathEvent deathEvent = new CustomEntityDeathEvent(victim);
                deathEvent.apply();
            }
        }
    }

    @EventHandler
    public void onCustomEntityDeath(CustomEntityDeathEvent event){
        Entity victim = event.getDeadVictim();
        victim.remove();
    }

    @EventHandler
    public void onCustomPlayerDeath(CustomPlayerDeathEvent event){
        Player victim = event.getDeadVictim();
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
        victim.setInvulnerable(true);
        victim.sendMessage(Chat.format("&4You Died!"));
        Chat.sendTitle(victim, "&4You Died!");
        victim.teleport(victim.getWorld().getSpawnLocation());
        vPlayerData.setHealth(vPlayerData.getHealthMax());
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()-> victim.setInvulnerable(false), 60);
    }
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
