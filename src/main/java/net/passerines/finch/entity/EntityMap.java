package net.passerines.finch.entity;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ModeledEntity;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.CustomEntityDeathEvent;
import net.passerines.finch.integrations.MythicMobsBridge;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;

public class EntityMap implements Listener {
    private static final HashMap<Entity, EntityData> ENTITIES = new HashMap<>();


    public EntityMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    public static EntityData get(Entity entity) {
        if(ModelEngineAPI.isModeledEntity(entity.getUniqueId())) {
            return ENTITIES.get(Bukkit.getEntity(ModelEngineAPI.getModeledEntity(entity.getUniqueId()).getBase().getUniqueId()));
        } else {
            return ENTITIES.get(entity);
        }
    }
    public static void remove(Entity entity) {
        ENTITIES.remove(entity);
    }
    public static boolean has(Entity entity) {
        return ENTITIES.containsKey(entity);
    }

    @EventHandler
    public void onSpawn(MythicMobSpawnEvent event){
        ActiveMob eventEntity = event.getMob();
        //Util.log("Entity added to map");
        ENTITIES.put(event.getEntity(), new EntityData(eventEntity, event.getMobType()));
        //Util.log("Registered Mob: " + event.getMobType());
    }

    @EventHandler
    public void onVanillaSpawn(EntitySpawnEvent event){
        if(event.getEntity() instanceof LivingEntity livingEntity) {
            //Util.log("Entity added to map");
            if (event.getEntity().getPersistentDataContainer().has(Util.getNamespacedKey("ignore"), PersistentDataType.BYTE))
                return;
            if(ModelEngineAPI.isModeledEntity(livingEntity.getUniqueId())) {
                if(!ModelEngineAPI.getModeledEntity(livingEntity.getUniqueId()).getBase().getUniqueId().equals(livingEntity.getUniqueId())) {
                    Util.log(Chat.format("&cNOT ADDING MODEL ENGINE MOB"));
                    return;
                }
            }
            ENTITIES.put(event.getEntity(), new EntityData(livingEntity));
            //Util.log("Registered Mob: " + event.getEntityType());
        }
    }
    @EventHandler
    public void onLoadEvent(EntitiesLoadEvent event){
        Collection<Entity> entitylist = event.getEntities();
        Object[] entities = entitylist.toArray();
        for(Object entity : entities) {
            if(entity instanceof LivingEntity livingEntity && !ENTITIES.containsKey(entity)){
                //Util.log("Entity added to map");
                ENTITIES.put(livingEntity, new EntityData(livingEntity));
                //Util.log("Registered Mob: " + (livingEntity).getType());
            }
        }
    }

    @EventHandler
    public void onDeath(CustomEntityDeathEvent event){
        ENTITIES.remove(event.getDeadVictim());
        //Util.log("Registered Mob Died: " + event.getDeadVictim());
    }
}

