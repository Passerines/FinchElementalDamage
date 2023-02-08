package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FinchThrownInsect {
    public static HashMap<Player, FinchThrownInsect> thrownInsectMap = new HashMap<>();
    private Entity hitEntity = null;
    private ArmorStand armorStand = null;
    private final FinchInsect finchInsect;
    private final Player player;
    private boolean retracting;
    private int taskThrow;
    public FinchThrownInsect(Player player, ItemStack insect){
        this.player = player;
        if(Util.getFinchItem(insect) instanceof FinchInsect finchInsect){
            this.finchInsect = finchInsect;
        }
        else {
            finchInsect = null;
            Util.log("A error regarding FinchInsects have occured");
        }
    }
    public void throwInsect(){
        Plugin plugin = FinchElementalDamage.inst();
        Location loc = player.getLocation();
        armorStand = loc.getWorld().spawn(loc, ArmorStand.class, false, (ArmorStand armorStand1) -> {
            armorStand1.setInvisible(true);
            armorStand1.setInvulnerable(true);
            armorStand1.setSmall(true);
            armorStand1.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand1.getEquipment().setHelmet(player.getEquipment().getItemInOffHand());
        });
        player.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
        taskThrow = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Location asLoc = armorStand.getLocation();
            if(hitEntity == null) {
                armorStand.teleport(asLoc);
                armorStand.setVelocity(loc.getDirection().normalize().multiply(2));
                Collection<Entity> entitylist = asLoc.getNearbyEntities(4, 4, 4);
                Object[] entities = entitylist.toArray();
                for(Object entity : entities) {
                    if (entity instanceof Damageable) {
                        if (!entity.equals(player) && !(entity instanceof ArmorStand)) {
                            if (hitEntity == null) {
                                hitEntity = (Entity) entity;
                                player.sendMessage(Chat.formatC("You hit for: " + finchInsect.getBugDamage()));
                                new ElementalDamageEvent(player, (Entity) entity, EntityDamageEvent.DamageCause.CUSTOM, finchInsect.getElement(), finchInsect.getBugDamage(), armorStand.getItem(EquipmentSlot.HEAD)).apply();
                                hitEntity.playEffect(EntityEffect.HURT);
                            }
                        }
                    }
                }
            }
            else{
                armorStand.setGravity(false);
            }
        }, 0, 1);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::retractInsect, 60);
    }
    public void retractInsect() {
        if (!retracting) {
            retracting = true;
            Bukkit.getScheduler().cancelTask(taskThrow);
            taskThrow = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), () -> {
                Location asLoc = armorStand.getLocation();
                Location loc = player.getLocation();
                PlayerData playerData = PlayerMap.PLAYERS.get(player);
                if (asLoc.distanceSquared(loc) < 5) {
                    ItemStack itemStack = armorStand.getEquipment().getHelmet();
                    if (player.getEquipment().getItemInOffHand().getType().isAir()) {
                        player.getEquipment().setItemInOffHand(itemStack);
                        playerData.applyInsectBuff(finchInsect, hitEntity);
                    }
                    else if (player.getInventory().firstEmpty() > -1) {
                        player.getInventory().addItem(itemStack);
                        playerData.applyInsectBuff(finchInsect, hitEntity);
                    }
                    else {
                        loc.getWorld().dropItem(loc, itemStack);
                    }
                    Bukkit.getScheduler().cancelTask(taskThrow);
                    armorStand.remove();
                } else {
                    Vector vector = (loc.subtract(asLoc).toVector().normalize()).add(asLoc.toVector());
                    armorStand.teleport(vector.toLocation(loc.getWorld()));
                }
            }, 0, 1);
        }
    }
}
