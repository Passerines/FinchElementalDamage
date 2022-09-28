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
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;

public class DamageDisplayer implements Listener {
    public DamageDisplayer() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onDamage(ElementalDamageEvent event){
        Entity victim = event.getVictim();
        double finalDamage = event.getDamage();
        double damage = 0;
        if(!(victim instanceof ArmorStand)) {
            if(victim instanceof Player){
                PlayerData vPlayerData = PlayerMap.PLAYERS.get(victim);
                damage = (int) ((finalDamage - (finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500.0))) * event.getElement().getElementalMultiplier()));
            }
            else if(victim instanceof LivingEntity){
                EntityData vEntityData = EntityMap.ENTITIES.get(victim);
                damage = (int) (finalDamage - finalDamage * (vEntityData.getDefense() / (vEntityData.getDefense() + 500.0)));
            }
            Location loc = victim.getLocation().add(Util.rand(-1.0, 1.0), Util.rand(-1.0, 1.0), Util.rand(-1.0, 1.0));
            ArmorStand damageDisplay = loc.getWorld().spawn(loc, ArmorStand.class);
            damageDisplay.getPersistentDataContainer().set(Util.getNamespacedKey("remove"), PersistentDataType.BYTE, (byte) 1);
            damageDisplay.setInvisible(true);
            damageDisplay.setMarker(true);
            damageDisplay.customName(Chat.formatC(event.getElement().getColor() + "☆" + damage + "☆"));
            damageDisplay.setCustomNameVisible(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), damageDisplay::remove, 50);
        }
    }
}
