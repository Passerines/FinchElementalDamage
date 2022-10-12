package net.passerines.finch.aItems.weapons.melee.katanas.t4;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Ircosis extends FinchWeapon implements Listener {
    Cooldown<Player> cd = new Cooldown<>(20);

    public Ircosis() {
        super("Ircosis", 4);
        this.damage = 55;
        this.element = ElementalDamageEvent.Element.WATER;
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
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(94, 59, 255), 1.0F);
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 5, damage,85,30, dust);
            slash.drawSlash();
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 5, damage,85,60, dust);
                slash0.drawSlash();
            }, 4);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 5, damage,85,120, dust);
                slash0.drawSlash();
            }, 4);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 5, damage,85,150, dust);
                slash0.drawSlash();
            }, 4);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&3Ircosis"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&4Damage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(6);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
