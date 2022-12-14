package net.passerines.finch.aItems.weapons.melee.katanas.t5;

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
import java.util.UUID;

public class Shriex extends FinchWeapon implements Listener {
    Cooldown<Player> cd = new Cooldown<>(50);

    public Shriex() {
        super("Shriex", 5);
        this.attack = 55;
        this.element = ElementalDamageEvent.Element.WIND;
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
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.SOUL, Particle.SOUL_FIRE_FLAME, 3, attack,100,30, null);
            slash.drawSlash();
            Slash slash1 = new Slash(player, player.getEyeLocation(), getItem() , Particle.SOUL, Particle.SOUL_FIRE_FLAME, 3, attack,100,-30, null);
            slash1.drawSlash();
            cd.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bShr&3iex"));
        ArrayList<Component> lore = new ArrayList<>();
        //lore.add(Component.text(Chat.format("&4Damage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&6Ability: &bSonic Delay")));
        lore.add(Component.text(Chat.format("&7Two Slashes are created at once dealing twice as much damage")));
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(6);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
