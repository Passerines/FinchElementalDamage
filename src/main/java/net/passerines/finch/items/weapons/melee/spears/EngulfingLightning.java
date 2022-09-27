package net.passerines.finch.items.weapons.melee.spears;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
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
import java.util.UUID;

public class EngulfingLightning extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(3);
    Cooldown cd1 = new Cooldown<>(20);
    public EngulfingLightning() {
        super("EngulfingLightning");
        this.damage = 85;
        this.element = ElementalDamageEvent.Element.ELECTRO;
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
    public void onEntityDamage(ElementalDamageEvent event) {
        if(event.getAttacker() instanceof Player player){
            if(id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player)) {
                /*
                Location loc = event.getVictim().getLocation()l;
                LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
                lightningStrike.setCausingPlayer(player);
                lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("weapon"), PersistentDataType.STRING, id);
                ightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.DOUBLE, 100.0);
                 */
                PlayerData playerData = new PlayerData(player);
                if(playerData.getManaMax() >= playerData.getMana()){
                    if(playerData.getManaMax() * 0.02 + playerData.getMana() < playerData.getManaMax()){
                        playerData.setMana((int) (playerData.getManaMax() * 0.02));
                        String bar = Chat.format("&bRestored " + (playerData.getManaMax() * 0.02) + " &bMana");
                        Chat.sendActionBar(player, bar);
                    }
                    else{
                        playerData.setManaMax(playerData.getManaMax());
                    }
                }
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.ELECTRIC_SPARK, Particle.DRAGON_BREATH, 5, damage,2,0);
            slash.drawSlash();
            cd.add(player);
        }
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player)){
            HashSet<Material> transparent = new HashSet<Material>();
            transparent.add(Material.AIR);
            Block block = player.getTargetBlock(transparent, 120);
            player.getWorld().strikeLightning(block.getLocation());
            cd1.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&dEngulfing Lightning"));
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(7);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&4Damage: &f+85")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}