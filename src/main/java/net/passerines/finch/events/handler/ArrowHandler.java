package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.http.WebSocket;

public class ArrowHandler implements Listener {
    public ArrowHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onContact(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (!event.isCancelled() && arrow.getPersistentDataContainer().has(Util.getNamespacedKey("FinchArrow"))) {
                if (event.getHitEntity() != null) {
                    Entity entity = event.getHitEntity();
                    Location loc = arrow.getLocation();
                    int damage = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("damage"), PersistentDataType.INTEGER);
                    int taskId = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("taskid"), PersistentDataType.INTEGER);
                    String aElement = arrow.getPersistentDataContainer().get(Util.getNamespacedKey("FinchArrow"), PersistentDataType.STRING);
                    Bukkit.getScheduler().cancelTask(taskId);
                    ElementalDamageEvent elementalDamageEvent;
                    switch (aElement) {
                        default -> {
                            loc.getWorld().spawnParticle(Particle.CRIT, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.NEUTRAL, damage);
                        }
                        case "fire" -> {
                            entity.setFireTicks(20);
                            loc.getWorld().spawnParticle(Particle.DRIP_LAVA, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.FIRE, damage);
                        }
                        case "water" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0, false, false, true));
                            loc.getWorld().spawnParticle(Particle.WATER_SPLASH, loc, 6);
                            Bukkit.getScheduler().cancelTask(taskId);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.WATER, damage);
                        }
                        case "earth" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.WATER_SPLASH, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.EARTH, damage);
                        }
                        case "wind" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 1, false, false, true));
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SNOWBALL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.WIND, damage);
                        }
                        case "electro" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3, 10, false, false, true));
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3, -1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.ELECTRO, damage);
                        }
                        case "light" -> {
                            loc.getWorld().spawnParticle(Particle.FLASH, loc, 10);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.LIGHT, damage);
                        }
                        case "undead" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 1, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SOUL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.UNDEAD, damage);
                        }
                        case "dark" -> {
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4, 0, false, false, true));
                            loc.getWorld().spawnParticle(Particle.SOUL, loc, 6);
                            elementalDamageEvent = new ElementalDamageEvent((Entity) arrow.getShooter(), entity, ElementalDamageEvent.Element.DARK, damage);
                        }
                    }
                    elementalDamageEvent.apply();
                }
            }
        }
    }
}
