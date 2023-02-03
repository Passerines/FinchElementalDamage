package net.passerines.finch.aItems.weapons.mana.t2;

import net.bytebuddy.build.Plugin;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.UUID;

public class LightningSpell extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(5);
    public LightningSpell() {
        super("LightningSpell", 2);
        this.mana = 400;
        this.manaRegen = 100;
        this.element = ElementalDamageEvent.Element.ELECTRO;

        // Set the displayname and lore
        displayName = Chat.formatC("&6Smite Spellbook");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &6Smite");
        lore.add("&7Spawn a bolt of lightning where you are looking");
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
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            playerData.setMana(playerData.getMana()-150);
            HashSet<Material> transparent = new HashSet<>();
            transparent.add(Material.AIR);
            Block block = player.getTargetBlock(transparent, 50);
            Location loc = block.getLocation();
            LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
            lightningStrike.setCausingPlayer(player);
            lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("LightningSpell"), PersistentDataType.STRING, id);
            String bar = Chat.format("&c-150 &bMana");
            Chat.sendActionBar(player, bar);
            cd.add(player);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLightning(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof LightningStrike lightningStrike){
            if(lightningStrike.getPersistentDataContainer().has(Util.getNamespacedKey("LightningSpell"))){
                event.setCancelled(true);
                new ElementalDamageEvent(lightningStrike.getCausingEntity(), event.getEntity(), EntityDamageEvent.DamageCause.LIGHTNING, ElementalDamageEvent.Element.ELECTRO, 10.0 + (PlayerMap.PLAYERS.get((Player) lightningStrike.getCausingEntity()).getManaMax() / 100f + 0.0)*6, ((Player) lightningStrike.getCausingEntity()).getInventory().getItemInMainHand()).apply();
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(2003);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        //
        return writeId(item);
    }
}
