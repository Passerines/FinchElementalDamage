package net.passerines.finch.aItems.weapons.melee.katanas.t2;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DiamondKatana extends FinchWeapon implements Listener {
    public final HashMap<Player, Integer> comboMap = new HashMap<>();
    Cooldown<Player> cd = new Cooldown<>(8);
    Cooldown<Player> cdMode = new Cooldown<>(15);

    public DiamondKatana() {
        super("DiamondKatana", 2);
        this.attack = 25;
        this.element = ElementalDamageEvent.Element.NEUTRAL;
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
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(80, 190, 212), 1.0F);
            if(comboMap.getOrDefault(player, 1) == 1){
                Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.CRIT, 4, attack,85,135, dust);
                slash.drawSlash();
            }
            else if(comboMap.getOrDefault(player, 2) == 2) {
                Slash slash = new Slash(player, player.getEyeLocation(), getItem(), Particle.REDSTONE, Particle.CRIT, 4, attack, 85, 45, dust);
                slash.drawSlash();
            }
            cd.add(player);
            if(comboMap.getOrDefault(player, 1) == 1){
                comboMap.put(player, 2);
            }
            else if(comboMap.getOrDefault(player, 2) == 2) {
                comboMap.put(player, 1);
            }
            cdMode.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(6);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        return writeId(item);
    }
}
