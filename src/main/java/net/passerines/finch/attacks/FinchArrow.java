package net.passerines.finch.attacks;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

public class FinchArrow implements Listener {

    private String id;
    private Player player;
    private float fireVelocity;
    private float spread;
    private int damage;

    public FinchArrow(Player player, float fireVelocity, float spread, int damage){
        this.player = player;
        this.fireVelocity = fireVelocity;
        this.damage = damage;
        this.spread = spread;
    }
    public Arrow shootFireArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(195, 58, 0), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "fire");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootWaterArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(0, 105, 217), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "water");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootEarthArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(67, 59, 0), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "earth");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootWindArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(227, 229, 255), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "wind");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootElectroArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(227, 157, 254), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "electro");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootLightArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 249, 214), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "electro");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootNeutralArrow(){
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "neutral");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.CRIT, arrow.getLocation(), 1,0 ,0 ,0 ,0);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootUndeadArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(148, 50, 99), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "undead");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
    public Arrow shootDarkArrow(){
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(1, 1, 1), 1.0F);
        Location loc = player.getEyeLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
        arrow.setShooter(player);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING, "dark");
        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
            arrow.getLocation().getWorld().spawnParticle(Particle.REDSTONE, arrow.getLocation(), 1,0 ,0 ,0 ,0, dust);
        }, 1,1);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER, task);
        arrow.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER, damage);
        return arrow;
    }
}
