package net.passerines.finch.aItems.weapons.ranged.t5;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchBow;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

public class VortexSplash extends FinchBow implements Listener {
    public VortexSplash() {
        super("VortexSplash");
        this.bowDamage = 150;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    private final Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData playerData = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isLeftClick() && cd.isOffCooldown(player)) {
            if(id.equals(Util.getId(item))) {
                FinchArrow finchArrow = new FinchArrow(player, 2, 0, this.bowDamage);
                finchArrow.shootWaterArrow().getPersistentDataContainer().set(Util.getNamespacedKey("VortexSplash"), PersistentDataType.STRING, "VortexSplash");
                cd.add(player);
            }
        }
    }
    @EventHandler
    public void onContact(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow arrow){
            if(arrow.getPersistentDataContainer().has(Util.getNamespacedKey("VortexSplash"))){
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                    arrow.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, arrow.getLocation(), 90,2,2,2);
                    Collection<Entity> entitylist = arrow.getLocation().getNearbyEntities(5,5,5);
                    Object[] entities = entitylist.toArray();
                    for (Object entity : entities) {
                        if (entity instanceof Damageable) {
                            if (!(entity.equals(arrow.getShooter())) && !(entity.equals(ArmorStand.class))) {
                                ElementalDamageEvent elementalDamageEvent = new ElementalDamageEvent(arrow, (Entity) entity, ElementalDamageEvent.Element.WATER, this.bowDamage/2);
                                elementalDamageEvent.apply();
                            }
                        }
                    }
                }, 6);

            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&1Vortex Splash"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
