package net.passerines.finch.attacks;

import net.passerines.finch.FinchElementalDamage;
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
    private ItemStack weapon;
    private float fireVelocity;
    private float spread;
    private int damage;

    public FinchArrow(Player player, ItemStack weapon, float fireVelocity, float spread, int damage){
        this.player = player;
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

    public Arrow shootFireArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(195, 58, 0), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "fire");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootWaterArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(0, 105, 217), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "water");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootEarthArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(67, 59, 0), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "earth");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootWindArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(227, 229, 255), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "wind");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootElectroArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(227, 157, 254), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "electro");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootLightArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 249, 214), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "electro");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootNeutralArrow(){
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "neutral");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.CRIT, arrow.getLocation(), 1,0 ,0 ,0 ,0);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootUndeadArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(148, 50, 99), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "undead");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        ARROW_MAP.put(arrow, this);
        return arrow;
    }
    public Arrow shootDarkArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(1, 1, 1), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, spread);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "dark");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
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
