package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.http.WebSocket;
import java.util.HashMap;

public class ArrowHandler implements Listener {

    public ArrowHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onContact(ProjectileHitEvent event) {
        //Util.log("Projectile Hit");
        if (event.getEntity() instanceof Arrow arrow) {
            //Util.log("Arrow Hit");
            if (!event.isCancelled() && arrow.getPersistentDataContainer().has(Util.getNamespacedKey("FinchArrow"))) {
                event.setCancelled(true);
                //Util.log("FinchArrow Hit");
                int taskId = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER);
                Util.log(taskId + "");
                Bukkit.getScheduler().cancelTask(taskId);
                if (event.getHitEntity() != null) {
                    Entity entity = event.getHitEntity();
                    Location loc = arrow.getLocation();
                    int damage = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER);
                    String aElement = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING);
                    ElementalDamageEvent elementalDamageEvent;
                    switch (aElement) {
                        default -> {
                            loc.getWorld().spawnParticle(Particle.CRIT, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.NEUTRAL, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "fire" -> {
                            entity.setFireTicks(20);
                            loc.getWorld().spawnParticle(Particle.DRIP_LAVA, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.FIRE, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "water" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0, false, false, true));
                            loc.getWorld().spawnParticle(Particle.WATER_SPLASH, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.WATER, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "earth" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.LEGACY_BLOCK_DUST, loc, 20);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.EARTH, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "wind" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 1, false, false, true));
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SNOWBALL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.WIND, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "electro" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3, 10, false, false, true));
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3, -1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.ELECTRO, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "light" -> {
                            loc.getWorld().spawnParticle(Particle.FLASH, loc, 10);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.LIGHT, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "undead" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SOUL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.UNDEAD, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                        case "dark" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4, 0, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SOUL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Player) arrow.getShooter(), entity, EntityDamageEvent.DamageCause.PROJECTILE, ElementalDamageEvent.Element.DARK, damage, FinchArrow.getShootingWeapon(arrow));
                        }
                    }
                    elementalDamageEvent.apply();
                    FinchArrow.remove(arrow);
                    event.getEntity().remove();
                }
            }
        }
    }

}
