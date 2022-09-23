package net.passerines.finch.attacks;

import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Util;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class DrawLine {
    private Player player;
    private Location location;
    private Particle particle;
    private Particle particleEnd;
    private double range;
    private ItemStack itemStack;
    private double damage;

    public DrawLine(Player player, Location location, ItemStack itemStack, Particle particle, Particle particleEnd, double range, double damage) {
        this.player = player;
        this.location = location;
        this.particle = particle;
        this.particleEnd = particleEnd;
        this.range = range;
        this.itemStack = itemStack;
        this.damage = damage;
    }

    public ArrayList<Entity> draw() {
        ArrayList<Entity> hitEntities = new ArrayList<>();
        for (int i = 1; i <= range; i += 1) {
            Location loc = location.clone();
            Vector direction = loc.getDirection().multiply(i);
            loc.add(direction);
            if (loc.getBlock().isCollidable()) {
                break;
            }
            if (i + 1 >= range) {
                loc.getWorld().spawnParticle(particleEnd, loc, 1, 0, 0, 0, 0);
            } else {
                loc.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0);
            }
            Collection<Entity> entitylist = loc.getNearbyEntities(3, 3, 3);
            Object[] entities = entitylist.toArray();
            for (Object entity : entities) {
                if (entity instanceof Damageable) {
                    if (!(entity.equals(player))) {
                        String weaponId = Util.getId(itemStack);
                        FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(weaponId);
                        if (finchItem instanceof FinchWeapon finchWeapon && !hitEntities.contains(entity)) {
                            ElementalDamageEvent elementalDamageEvent = new ElementalDamageEvent(player, (Entity) entity, finchWeapon.getElement(), damage);
                            elementalDamageEvent.apply();
                            hitEntities.add((Entity) entity);
                        }
                    }
                }
            }
        }
        return hitEntities;
    }
}
