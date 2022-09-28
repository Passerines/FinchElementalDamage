package net.passerines.finch.entity;

import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.CustomEntityDeathEvent;
import net.passerines.finch.integrations.MythicMobsBridge;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.EntitiesLoadEvent;

import java.util.Collection;
import java.util.HashMap;

public class EntityMap implements Listener {
    public static final HashMap<Entity, EntityData> ENTITIES = new HashMap<>();


    public EntityMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onSpawn(MythicMobSpawnEvent event){
        ActiveMob eventEntity = event.getMob();
        Util.log("Entity added to map");
        ENTITIES.put(event.getEntity(), new EntityData(eventEntity, event.getMobType()));
        Util.log("Registered Mob: " + event.getMobType());
    }

    @EventHandler
    public void onVanillaSpawn(EntitySpawnEvent event){
        if(!ENTITIES.containsKey(event.getEntity()) && event.getEntity() instanceof LivingEntity livingEntity){
            Util.log("Entity added to map");
            double maxHealth = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*3;
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
            livingEntity.setHealth(maxHealth);
            ENTITIES.put(event.getEntity(), new EntityData(event.getEntity()));
            Util.log("Registered Mob: " + event.getEntityType());
        }
    }
    @EventHandler
    public void onLoadEvent(EntitiesLoadEvent event){
        Collection<Entity> entitylist = event.getEntities();
        Object[] entities = entitylist.toArray();
        for(Object entity : entities) {
            if(entity instanceof LivingEntity livingEntity && !ENTITIES.containsKey(entity)){
                Util.log("Entity added to map");
                ENTITIES.put(livingEntity, new EntityData(livingEntity));
                Util.log("Registered Mob: " + (livingEntity).getType());
            }
        }
    }

    @EventHandler
    public void onDeath(CustomEntityDeathEvent event){
        ENTITIES.remove(event.getDeadVictim());
        Util.log("Registered Mob Died: " + event.getDeadVictim());
    }
}

