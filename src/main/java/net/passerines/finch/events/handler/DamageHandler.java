package net.passerines.finch.events.handler;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ModeledEntity;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.CustomEntityDeathEvent;
import net.passerines.finch.events.CustomPlayerDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static net.passerines.finch.entity.EntityMap.ENTITIES;
import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class DamageHandler implements Listener {
    public DamageHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());

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
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //Converts entity damage by entity to elemental damage
        ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.UNDEAD, (int) event.getDamage());
        elementalDamage.apply();
        event.setDamage(0);
    }
    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity attacker = event.getAttacker();
        Entity victim = event.getVictim();
        if (victim instanceof Player) {
            PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
            double finalDamage = event.getDamage();
            double damageTaken = (int) ((finalDamage - (finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500.0))) * event.getElement().getElementalMultiplier()));

            if (attacker instanceof Player) {
                if(Util.getId(((Player) attacker).getPlayer().getInventory().getItemInMainHand()) != null){
                    damageTaken = (damageTaken + (PLAYERS.get(attacker).getDamage()*((Player) attacker).getPlayer().getAttackCooldown()));
                }
                else{damageTaken = 5;}
            }
            else if (attacker instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    damageTaken = damageTaken + (PlayerMap.PLAYERS.get(player).getDamage()*(player).getPlayer().getAttackCooldown());
                }
            }
            else if (attacker instanceof LivingEntity entity) {
                damageTaken = damageTaken + EntityMap.ENTITIES.get(entity).getDamage();
            }
            vPlayerData.setHealth(vPlayerData.getHealth() - damageTaken);
            victim.playEffect(EntityEffect.HURT);

            victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());

            if (attacker instanceof Player) {
                attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            }

            if (vPlayerData.getHealth() <= 0) {
                CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((Player) victim);
                deathEvent.apply();
            }
        }
        //                  ^ Players
        //
        //
        //
        //                  v Entities
        else if (victim instanceof LivingEntity) {
            EntityData vEntityData = EntityMap.ENTITIES.get(victim);
            double mobDamage = event.getDamage();
            double mobDamageTaken = (mobDamage - mobDamage * (vEntityData.getDefense() / (vEntityData.getDefense() + 500.0)));

            if (attacker instanceof Player) {
                if (Util.getId(((Player) attacker).getPlayer().getInventory().getItemInMainHand()) != null) {
                    mobDamageTaken = (mobDamageTaken + (PLAYERS.get(attacker).getDamage() * ((Player) attacker).getPlayer().getAttackCooldown()));
                } else {
                    mobDamageTaken = 5;
                }
                attacker.sendMessage("Damage Dealt: " + mobDamageTaken + " Element: " + event.getElement());
            } else if (attacker instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    mobDamageTaken = mobDamageTaken + (PlayerMap.PLAYERS.get(player).getDamage() * (player).getPlayer().getAttackCooldown());
                }
            }
            vEntityData.setHealth(vEntityData.getHealth() - mobDamageTaken);
            double health = Math.max(0, vEntityData.getHealth() / 3);
            ((LivingEntity) victim).setHealth(health);
            victim.playEffect(EntityEffect.HURT);
            if (ModelEngineAPI.isModeledEntity(victim.getUniqueId())) {
                ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(victim.getUniqueId());
                modeledEntity.hurt();
            }
            if (vEntityData.getHealth() <= 0) {
                CustomEntityDeathEvent deathEvent = new CustomEntityDeathEvent(victim);
                deathEvent.apply();
            }
        }
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
        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()-> victim.setInvulnerable(false), 60);
    }
    @EventHandler
    public void onCustomEntityDeath(CustomEntityDeathEvent event){
        LivingEntity victim = (LivingEntity) event.getDeadVictim();
        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        Util.log("Registered Mob Died: " + event.getDeadVictim());
        ENTITIES.remove(victim);
        victim.damage(99999);
        victim.remove();

    }
}