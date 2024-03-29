package net.passerines.finch.entity;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.nms.entity.fake.SubHitboxEntity;
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
import java.util.Iterator;
import java.util.UUID;

public class EntityMap implements Listener {
    private static final HashMap<Entity, EntityData> ENTITIES = new HashMap<>();

    public EntityMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    public static EntityData get(Entity entity) {
        if(ModelEngineAPI.isModeledEntity(entity.getUniqueId())) {
            return ENTITIES.get(Bukkit.getEntity(ModelEngineAPI.getModeledEntity(entity.getUniqueId()).getBase().getUniqueId()));
        } else if(ModelEngineAPI.getModelTicker().getSubHitboxBone(entity.getUniqueId())!=null) {
            return ENTITIES.get(Bukkit.getEntity(
                    ModelEngineAPI.getModelTicker().getSubHitboxBone(entity.getUniqueId()).getActiveModel().getModeledEntity().getBase().getUniqueId()
            ));
        } else {
            return ENTITIES.get(entity);
        }
    }
    public static Entity getBaseEntity(Entity entity) {
        EntityData entityData = get(entity);
        return entityData!=null?entityData.getEntity():null;
    }
    public static void remove(Entity entity) {
        ENTITIES.remove(entity);
    }
    public static boolean has(Entity entity) {
        return get(entity)!=null;
    }

    @EventHandler
    public void onSpawn(MythicMobSpawnEvent event){
        ActiveMob eventEntity = event.getMob();
        //Util.log("MythicMob spawn");
        ENTITIES.put(event.getEntity(), new EntityData(eventEntity, event.getMobType()));
        //Util.log("Loading Mythic entity " + eventEntity.getUniqueId() + " vanilla " + BukkitAdapter.adapt(eventEntity.getEntity()).getUniqueId());
        if(ModelEngineAPI.isModeledEntity(BukkitAdapter.adapt(eventEntity.getEntity()).getUniqueId())) {
            //Util.log("Loading modeled mythic entity " + BukkitAdapter.adapt(eventEntity.getEntity()).getUniqueId());
        }
        //Util.log("Registered Mob: " + event.getMobType());
    }

    @EventHandler
    public void onVanillaSpawn(EntitySpawnEvent event){
        if(event.getEntity() instanceof LivingEntity livingEntity && MythicMobsBridge.getActiveMob(livingEntity)==null) {
            //Util.log("Entity spawn");
            if (event.getEntity().getPersistentDataContainer().has(Util.getNamespacedKey("ignore"), PersistentDataType.BYTE))
                return;
            //Util.log("Loading entity " + livingEntity.getUniqueId());
            if(ModelEngineAPI.isModeledEntity(livingEntity.getUniqueId())) {
                //Util.log("Loading modeled entity " + livingEntity.getUniqueId());
                if(!ModelEngineAPI.getModeledEntity(livingEntity.getUniqueId()).getBase().getUniqueId().equals(livingEntity.getUniqueId())) {
                    //Util.log(Chat.format("&cNOT ADDING MODEL ENGINE MOB"));
                    return;
                }
            }
            if(livingEntity.getPersistentDataContainer().has(Util.getNamespacedKey("ignore"))) return;
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
                //Util.log("Entity load");
                if(livingEntity.getPersistentDataContainer().has(Util.getNamespacedKey("ignore"))) return;
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

