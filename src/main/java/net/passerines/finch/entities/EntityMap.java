package net.passerines.finch.entities;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class EntityMap implements Listener {
    public static final HashMap<Entity, EntityData> ENTITIES = new HashMap<>();


    public EntityMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onSpawn(MythicMobSpawnEvent event){
        Util.log("Added Mob To Map");
        ENTITIES.put(event.getEntity(), new EntityData(event.getEntity()));
    }

    @EventHandler
    public void onDespawn(MythicMobDeathEvent event){
        ENTITIES.remove(event.getEntity());
    }

}

