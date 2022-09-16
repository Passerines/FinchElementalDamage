package net.passerines.finch.attacks;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;

public class ThrowBlade {
    private String id;
    private Player player;
    private ElementalDamageEvent.Element element;
    private Particle particle;
    private int fireVelocity;
    private int damage;

    public ThrowBlade(String id, Player player, Particle particle, ElementalDamageEvent.Element element, int fireVelocity, int damage){
        this.id = id;
        this.player = player;
        this.particle = particle;
        this.element = element;
        this.fireVelocity = fireVelocity;
        this.damage = damage;
    }
    public void throwItem() {
        Plugin plugin = FinchElementalDamage.inst();
        ArrayList<Entity> hitEntities = new ArrayList<>();
        Location loc = player.getLocation();
        if (id.equals(Util.getId(player.getInventory().getItemInMainHand()))) {
            ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class, false, (ArmorStand armorStand1) -> {
                armorStand1.setInvisible(true);
                armorStand1.setInvulnerable(true);
                armorStand1.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                armorStand1.getEquipment().setItemInOffHand(player.getEquipment().getItemInMainHand());
            });
            int sSRT = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                Location asLoc = armorStand.getLocation();
                asLoc.setYaw(armorStand.getLocation().getYaw() + 50);
                armorStand.teleport(asLoc);
                armorStand.setVelocity(loc.getDirection().normalize().multiply(fireVelocity));
                Collection<Entity> entitylist = asLoc.getNearbyEntities(1, 1, 1);
                Object[] entities = entitylist.toArray();
                asLoc.getWorld().spawnParticle(particle, asLoc, 3, 0, 0, 0, 0);
                for(Object entity : entities) {
                    if (entity instanceof Damageable) {
                        if (!(entity.equals(player) || entity.equals(armorStand)) && !hitEntities.contains(entity)) {
                            new ElementalDamageEvent(player, (Entity) entity, element, damage).apply();
                            hitEntities.add((Entity) entity);
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
}
