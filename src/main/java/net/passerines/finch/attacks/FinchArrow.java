package net.passerines.finch.attacks;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class FinchArrow implements Listener {

    private static final HashMap<Arrow, FinchArrow> ARROW_MAP = new HashMap<>();

    private String id;
    private Player player;
    private Location origin;
    private ItemStack weapon;
    private float fireVelocity;
    private float spread;
    private int damage;

    public FinchArrow(Player player, ItemStack weapon, float fireVelocity, float spread, int damage){
        this(player, player.getEyeLocation(), weapon, fireVelocity, spread, damage);
    }

    public FinchArrow(Player player, Location origin, ItemStack weapon, float fireVelocity, float spread, int damage){
        this.player = player;
        this.origin = origin;
        this.weapon = weapon;
        this.fireVelocity = fireVelocity;
        this.damage = damage;
        this.spread = spread;
    }


    //Get the FinchArrow that an arrow was shot from
    public static FinchArrow getFinchArrow(Arrow arrow) {
        return ARROW_MAP.get(arrow);
    }
    //Get the weapon used to shoot the arrow
    public static ItemStack getShootingWeapon(Arrow arrow) {
        return ARROW_MAP.get(arrow).getWeapon();
    }
    public static void remove(Arrow arrow) {
        ARROW_MAP.remove(arrow);
    }
    //May be needed to clear arrows that despawn or became invalid through other reasons
    //Perhaps a task that clears invalid arrows at fixed intervals
    public static void clearInvalidArrows() {
        for(Arrow arrow : ARROW_MAP.keySet()) {
            if(!arrow.isValid()) {
                ARROW_MAP.remove(arrow);
            }
        }
    }
    public Arrow shootCustomArrow(String element, Particle particle, Particle.DustOptions dust){
        Location loc = origin;
        Arrow arrow = loc.getWorld().spawnArrow(loc, loc.getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, element);
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(particle, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootCustomArrow(String element, Particle particle){
        Location loc = origin;
        Arrow arrow = loc.getWorld().spawnArrow(loc, loc.getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, element);
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(particle, arrow.getLocation(), 1,0 ,0 ,0 ,0);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public ItemStack getWeapon() {
        return weapon;
    }
}
