package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;

public class FinchThrownInsect {
    private final FinchInsect finchInsect;
    private final FinchGlaives finchGlaives;
    private final Player player;
    public FinchThrownInsect(Player player, ItemStack insect, ItemStack glaive){
        this.player = player;
        if(Util.getFinchItem(insect) instanceof FinchInsect finchInsect){
            this.finchInsect = finchInsect;
        }
        else {
            finchInsect = null;
            Util.log("A error regarding FinchInsects have occured");
        }
        if(Util.getFinchItem(glaive) instanceof FinchGlaives finchGlaives){
            this.finchGlaives = finchGlaives;
        }
        else {
            finchGlaives = null;
            Util.log("A error regarding FinchGlaives have occured");
        }
    }
    public void throwInsect(){
        Util.log("Launched Insect");
        Plugin plugin = FinchElementalDamage.inst();
        ArrayList<Entity> hitEntities = new ArrayList<>();
        Location loc = player.getLocation();
        ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class, false, (ArmorStand armorStand1) -> {
            armorStand1.setGravity(false);
            armorStand1.setInvisible(true);
            armorStand1.setInvulnerable(true);
            armorStand1.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand1.getEquipment().setItemInOffHand(player.getEquipment().getItemInMainHand());
        });
        int sSRT = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Location asLoc = armorStand.getLocation();
            if(hitEntities.size() == 0) {
                armorStand.teleport(asLoc);
                armorStand.setVelocity(loc.getDirection().normalize().multiply(2));
            }
            Collection<Entity> entitylist = asLoc.getNearbyEntities(1, 1, 1);
            Object[] entities = entitylist.toArray();
            for(Object entity : entities) {
                if (entity instanceof Damageable) {
                    if (!(entity.equals(player) || entity.equals(armorStand))) {
                        if(hitEntities.size() == 0) {
                            new ElementalDamageEvent(player, (Entity) entity, EntityDamageEvent.DamageCause.CUSTOM, finchInsect.getElement(), finchGlaives.getAttack()/10, player.getInventory().getItemInMainHand()).apply();
                            hitEntities.add((Entity) entity);
                            ((Entity) entity).playEffect(EntityEffect.HURT);
                        }
                    }
                }
            }
        }, 0, 2);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            armorStand.remove();
            Bukkit.getScheduler().cancelTask(sSRT);
        }, 25);
    }
}
