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
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class DamageHandler implements Listener {
    public DamageHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private static final ArrayList<EntityDamageEvent.DamageCause> modelEngineImmunityTicks = new ArrayList<>();
    static {
        modelEngineImmunityTicks.add(EntityDamageEvent.DamageCause.FIRE);
        modelEngineImmunityTicks.add(EntityDamageEvent.DamageCause.FIRE_TICK);
        modelEngineImmunityTicks.add(EntityDamageEvent.DamageCause.LAVA);
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onDamageEvent(EntityDamageEvent event) {
        if (modelEngineImmunityTicks.contains(event.getCause())) {
            if (ModelEngineAPI.isModeledEntity(event.getEntity().getUniqueId())) {
                Entity entity = Bukkit.getEntity(ModelEngineAPI.getModeledEntity(event.getEntity().getUniqueId()).getBase().getUniqueId());
                PersistentDataContainer data = entity.getPersistentDataContainer();
                NamespacedKey key = Util.getNamespacedKey("lastFireTick");
                if (data.has(key, PersistentDataType.INTEGER)) {
                    int lastFireTick = data.get(key, PersistentDataType.INTEGER);
                    if (Bukkit.getCurrentTick() - lastFireTick < 10) {
                        event.setCancelled(true);
                        return;
                    }
                }
                data.set(key, PersistentDataType.INTEGER, Bukkit.getCurrentTick());
            }
        }
        if (!(event.getEntity() instanceof Item)) {
            switch (event.getCause()) {
                case VOID, DRAGON_BREATH, WITHER -> {
                    new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.DARK, (int) event.getDamage() * 5).apply();
                    event.setDamage(0);
                }
                case FALL, FALLING_BLOCK, POISON, STARVATION, SUFFOCATION, THORNS, CRAMMING, FLY_INTO_WALL -> {
                    new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.EARTH, (int) event.getDamage() * 5).apply();
                    event.setDamage(0);
                }
                case FIRE, LAVA, HOT_FLOOR, FIRE_TICK -> {
                    new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.FIRE, (int) event.getDamage() * 5).apply();
                    event.setDamage(0);
                }
                case DROWNING, FREEZE -> {
                    new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.WATER, (int) event.getDamage() * 5).apply();
                    event.setDamage(0);
                }
                case LIGHTNING -> {
                    new ElementalDamageEvent(null, event.getEntity(), ElementalDamageEvent.Element.ELECTRO, (int) event.getDamage() * 5).apply();
                    event.setDamage(0);
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //Converts entity damage by entity to elemental damage
        if(!event.isCancelled()) {
            ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.UNDEAD, (int) event.getDamage());
            elementalDamage.apply();
            event.setDamage(0);
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity victim = event.getVictim();
        if (victim instanceof Player) {
            PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
            vPlayerData.setHealth(vPlayerData.getHealth() - event.getFinalDamage());
            victim.playEffect(EntityEffect.HURT);

            //victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());
        /*
            if (attacker instanceof Player) {
                attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            }
        */
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
        else if (victim instanceof LivingEntity && EntityMap.has(victim)) {
            EntityData vEntityData = EntityMap.get(victim);
            vEntityData.setHealth(vEntityData.getHealth() - event.getFinalDamage());
            double health = Math.max(0, vEntityData.getHealth());
            ((LivingEntity) victim).setHealth(health);
            if (ModelEngineAPI.isModeledEntity(victim.getUniqueId())) {
                ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(victim.getUniqueId());
                modeledEntity.hurt();
            } else {
                victim.playEffect(EntityEffect.HURT);
            }
            if (vEntityData.getHealth() <= 0) {
                CustomEntityDeathEvent deathEvent = new CustomEntityDeathEvent(victim);
                deathEvent.apply();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
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
    @EventHandler(priority = EventPriority.HIGH)
    public void onCustomEntityDeath(CustomEntityDeathEvent event){
        LivingEntity victim = (LivingEntity) event.getDeadVictim();
        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        Util.log("Registered Mob Died: " + event.getDeadVictim());
        EntityMap.remove(victim);
        victim.damage(99999);
        victim.remove();

    }
}