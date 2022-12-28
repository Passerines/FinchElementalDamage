package net.passerines.finch.aItems.weapons.melee.spears.t3;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ElectrifiedSpear extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(5);
    Cooldown cd0 = new Cooldown<>(200);
    public ElectrifiedSpear() {
        super("ElectrifiedSpear");
        this.attack = 35;
        this.mana = 150;
        this.element = ElementalDamageEvent.Element.ELECTRO;

        // Set the displayname and lore
        displayName = Chat.formatC("&5Electrified Spear");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &5Stun");
        lore.add("&7Scatter electricity around yourself dealing 25 true damage and stunning opponent");
        this.lore = Chat.formatC(lore);
        //

        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void cancelHit(EntityDamageByEntityEvent hit){
        if(hit.getDamager() instanceof Player player){
            if(id.equals(Util.getId(player.getInventory().getItemInMainHand()))){
                hit.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.CRIT, Particle.DRAGON_BREATH, 5, attack,2,0, null);
            slash.drawSlash();
            cd.add(player);
        }
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Location loc = player.getLocation();
                loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 90,1,2,1);
                Collection<Entity> entitylist = loc.getNearbyEntities(1,2,1);
                Object[] entities = entitylist.toArray();
                for (Object entity : entities) {
                    if (entity instanceof Damageable) {
                        if (!(entity.equals(player)) && !(entity.equals(ArmorStand.class))) {
                            ElementalDamageEvent elementalDamageEvent = new ElementalDamageEvent(player, (Entity) entity,
                                    EntityDamageEvent.DamageCause.LIGHTNING, ElementalDamageEvent.Element.WATER,
                                    15, player.getInventory().getItemInMainHand());
                            elementalDamageEvent.apply();
                        }
                    }
                }
            }, 6);
            cd0.add(player);
        }
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(1);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        //
        return writeId(item);
    }
}
