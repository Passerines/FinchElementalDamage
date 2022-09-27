package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
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
        Location loc = victim.getLocation().add(Util.rand(-1.0 , 1.0), Util.rand(-1.0 , 1.0), Util.rand(-1.0 , 1.0));
        int damage = (int) event.getDamage();
        ArmorStand damageDisplay = loc.getWorld().spawn(loc, ArmorStand.class);
        damageDisplay.getPersistentDataContainer().set(Util.getNamespacedKey("remove"), PersistentDataType.BYTE, (byte) 1);
        damageDisplay.setInvisible(true);
        damageDisplay.setMarker(true);
        damageDisplay.customName(Chat.formatC(event.getElement().getColor() + "☆" + damage + "☆"));
        damageDisplay.setCustomNameVisible(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), damageDisplay::remove, 50);
    }
}
