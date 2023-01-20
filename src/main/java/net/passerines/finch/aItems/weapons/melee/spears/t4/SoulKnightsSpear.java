package net.passerines.finch.aItems.weapons.melee.spears.t4;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.DrawLine;
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

public class SoulKnightsSpear extends FinchWeapon implements Listener {
    Cooldown<Player> cd = new Cooldown<>(4);
    public SoulKnightsSpear() {
        super("SoulKnightsSpear");
        this.attack = 30;
        this.element = ElementalDamageEvent.Element.DARK;
        displayName = Chat.formatC("&3ESoul Knight's Spear");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
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
        PlayerData vPlayer = PlayerMap.PLAYERS.get(click.getPlayer());
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.SOUL, Particle.SONIC_BOOM, 8, attack,2,0, null).drawSlash();
            cd.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&3The Soul Knight's Spear"));
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(1001);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
