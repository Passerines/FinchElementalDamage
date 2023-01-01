package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class FinchGlaives extends FinchEquipment implements Listener {

    protected ElementalDamageEvent.Element element;

    public FinchGlaives(String id) {
        this(id, 1);
    }
    public FinchGlaives(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void ifInsect(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(id.equals(Util.getId(player.getInventory().getItemInMainHand()))){
            if(event.getAction().isRightClick() && Util.getFinchItem(player.getInventory().getItemInOffHand()) instanceof FinchInsect){
                Plugin plugin = FinchElementalDamage.inst();
                ArrayList<Entity> hitEntities = new ArrayList<>();
                Location loc = player.getLocation();
                ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class, false, (ArmorStand armorStand1) -> {
                    armorStand1.setInvisible(true);
                    armorStand1.setInvulnerable(true);
                    armorStand1.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    armorStand1.getEquipment().setItemInOffHand(player.getEquipment().getItemInMainHand());
                });
                int sSRT = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                    Location asLoc = armorStand.getLocation();
                    if(hitEntities.size() == 0) {
                        armorStand.teleport(asLoc);
                        armorStand.setVelocity(loc.getDirection().normalize().multiply(2));
                    }
                    Collection<Entity> entitylist = asLoc.getNearbyEntities(1, 1, 1);
                    Object[] entities = entitylist.toArray();
                    for(Object entity : entities) {
                        if (entity instanceof Damageable) {
                            if (!(entity.equals(player) || entity.equals(armorStand)) && !hitEntities.contains(entity)) {
                                if(hitEntities.size() == 0) {
                                    new ElementalDamageEvent(player, (Entity) entity, EntityDamageEvent.DamageCause.CUSTOM, element, this.attack, player.getInventory().getItemInMainHand()).apply();
                                    hitEntities.add((Entity) entity);
                                    ((Entity) entity).playEffect(EntityEffect.HURT);
                                }
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
    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}