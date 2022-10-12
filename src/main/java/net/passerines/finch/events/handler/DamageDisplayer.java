package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.persistence.PersistentDataType;

public class DamageDisplayer implements Listener {
    public DamageDisplayer() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(ElementalDamageEvent event){
        Entity victim = event.getVictim();
        int damage = (int) event.getFinalDamage();
        Location loc = victim.getLocation().add(Util.rand(-1.0, 1.0), Util.rand(1.0, 2.0), Util.rand(-1.0, 1.0));
        ArmorStand damageDisplay = loc.getWorld().spawn(loc, ArmorStand.class, (armorStand) -> {
            armorStand.getPersistentDataContainer().set(Util.getNamespacedKey("remove"), PersistentDataType.BYTE, (byte) 1);
            armorStand.getPersistentDataContainer().set(Util.getNamespacedKey("invulnerable"), PersistentDataType.BYTE, (byte) 1);
            armorStand.getPersistentDataContainer().set(Util.getNamespacedKey("ignore"), PersistentDataType.BYTE, (byte) 1);
            armorStand.setInvisible(true);
            armorStand.setInvulnerable(true);
            armorStand.setMarker(true);
            armorStand.customName(Chat.formatC(event.getElement().getColor() + "☆" + damage + "☆"));
            armorStand.setCustomNameVisible(true);
        });
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), damageDisplay::remove, 50);
    }

    @EventHandler
    public void onEntityLoad(EntitiesLoadEvent event) {
        for(Entity entity : event.getEntities()) {
            if(entity.getPersistentDataContainer().has(Util.getNamespacedKey("remove")) && entity.getTicksLived()>0) {
                entity.remove();
            }
        }
    }
}
