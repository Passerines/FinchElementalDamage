package net.passerines.finch.aItems.weapons.melee.spears.t1;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
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
import java.util.UUID;

public class Spear extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(5);
    public Spear() {
        super("Spear");
        this.attack = 15;
        this.element = ElementalDamageEvent.Element.NEUTRAL;

        // Set the displayname and lore
        displayName = Chat.formatC("&fSpear");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &fDefensive Stance");
        lore.add("&7While sneaking your attacks have +1 range and hit a second time");
        lore.add("&7for 30% the damage");
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

    @Override
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.CRIT, Particle.CRIT, 5, attack,2,0, null);
            slash.drawSlash();
            cd.add(player);
        }
        if(click.getAction().isLeftClick() && player.isSneaking()){
            Slash slash = new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.CRIT, Particle.CRIT, 6, attack/3.0,2,0, null);
            slash.drawSlash();
            cd.add(player);
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
