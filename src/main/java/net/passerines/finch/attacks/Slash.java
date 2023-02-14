package net.passerines.finch.attacks;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.events.handler.FinchKnockBackEvent;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class Slash {
    private Player player;
    private Location location;
    private Particle particle;
    private Particle particleEnd;
    private double range;
    private double angle;
    private double rotation;
    private ItemStack itemStack;
    private double damage;
    private Object dust;
    private ArrayList<Entity> hitEntities = new ArrayList<>();

    private int degreesPerIteration = 3;
    private int degreesPerTick = 9;
    public Slash(Player player, Location location, ItemStack itemStack, Particle particle, Particle particleEnd, double range, double damage, double angle, double rotation) {
        this(player, location, itemStack, particle, particleEnd, range, damage, angle, rotation, null);
    }
    public Slash(Player player, Location location, ItemStack itemStack, Particle particle, Particle particleEnd, double range, double damage, double angle, double rotation, Object dust) {
        this.player = player;
        this.location = location;
        this.particle = particle;
        this.particleEnd = particleEnd;
        this.range = range;
        this.angle = angle;
        this.rotation = rotation;
        this.itemStack = itemStack;
        this.damage = damage;
        this.dust = dust;
    }
    public static Vector rotateVectorCC(Vector vec, Vector axis, double theta){
        double x, y, z;
        double u, v, w;
        x=vec.getX();y=vec.getY();z=vec.getZ();
        u=axis.getX();v=axis.getY();w=axis.getZ();
        double v1 = u * x + v * y + w * z;
        double xPrime = u* v1 *(1d - Math.cos(theta))
                + x*Math.cos(theta)
                + (-w*y + v*z)*Math.sin(theta);
        double yPrime = v* v1 *(1d - Math.cos(theta))
                + y*Math.cos(theta)
                + (w*x - u*z)*Math.sin(theta);
        double zPrime = w* v1 *(1d - Math.cos(theta))
                + z*Math.cos(theta)
                + (-v*x + u*y)*Math.sin(theta);
        return new Vector(xPrime, yPrime, zPrime);
    }
    private void drawLine(Location location) {
        for(double i = range/2; i <= range; i += 0.3){
            Location loc = location.clone();
            Vector direction = loc.getDirection().multiply(i);
            loc.add(direction);
            if(i + 0.9 >= range){
                loc.getWorld().spawnParticle(particleEnd, loc, 1, 0, 0, 0, 0);
            }
            else if(dust != null){
                loc.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0, dust);
            }
            Collection<Entity> entitylist = loc.getNearbyEntities(1, 1, 1);
            Object[] entities = entitylist.toArray();
            for(Object entity : entities) {
                if (entity instanceof Damageable) {
                    if (!(entity.equals(player))) {
                        String weaponId = Util.getId(itemStack);
                        FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(weaponId);
                        if(finchItem instanceof FinchWeapon finchWeapon && !hitEntities.contains(entity)) {
                            FinchKnockBackEvent finchKnockBackEvent = new FinchKnockBackEvent((Entity) entity, player, 1);
                            ElementalDamageEvent elementalDamageEvent = new ElementalDamageEvent(player, (Entity) entity, EntityDamageEvent.DamageCause.ENTITY_ATTACK , finchWeapon.getElement(), damage, itemStack);
                            elementalDamageEvent.apply();
                            finchKnockBackEvent.apply();
                            hitEntities.add((Entity) entity);
                        }
                    }
                }
            }
        }
    }
    public void drawSlash(){

        new BukkitRunnable(){
            int iteration = 0;
            @Override
            public void run() {
                int iterationEnd = iteration*degreesPerIteration+degreesPerTick;
                for(int i=iteration*degreesPerIteration; i < iterationEnd; i+=degreesPerIteration) {
                    iteration++;
                    if(i>=angle){
                        this.cancel();
                    }
                    Vector direction = new Vector(1, 0, 0);
                    Vector y = new Vector(0, 1, 0);
                    Vector z = new Vector(0, 0, 1);
                    Vector x = new Vector(1, 0, 0);
                    Location loc = location.clone();
                    direction = rotateVectorCC(direction, y, Math.toRadians(i - angle / 2));
                    direction = rotateVectorCC(direction, x, Math.toRadians(rotation));
                    direction = rotateVectorCC(direction, z, Math.toRadians(-loc.getPitch()));
                    direction = rotateVectorCC(direction, y, Math.toRadians(-loc.getYaw() - 90));
                    loc.setDirection(direction);
                    drawLine(loc);
                }
            }
        }.runTaskTimer(FinchElementalDamage.inst(), 0, 1);
    }

    public Slash setDegreesPerIteration(int degreesPerIteration) {
        this.degreesPerIteration = degreesPerIteration;
        return this;
    }

    public Slash setDegreesPerTick(int degreesPerTick) {
        this.degreesPerTick = degreesPerTick;
        return this;
    }
}
