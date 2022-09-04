package net.passerines.finch.entity;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class EntityMap implements Listener {
    public static final HashMap<Entity, EntityData> ENTITIES = new HashMap<>();


    public EntityMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onSpawn(MythicMobSpawnEvent event){
        ENTITIES.put(event.getEntity(), new EntityData(event.getEntity()));
        Util.log("Registered Mob: " + event.getMobType());
    }

    @EventHandler
    public void onDeath(MythicMobDeathEvent event){
        ENTITIES.remove(event.getEntity());
        Util.log("Registered Mob Died: " + event.getMobType());
    }
}

