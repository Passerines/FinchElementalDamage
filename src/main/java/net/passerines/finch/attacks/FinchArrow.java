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
    public void shootEarthArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootWindArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootElectroArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootLightArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootNeutralArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootUndeadArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
    public void shootDarkArrow(){
        Location loc = player.getLocation();
        Arrow arrow = loc.getWorld().spawnArrow(loc, player.getEyeLocation().getDirection(), fireVelocity, 0);
    }
}
