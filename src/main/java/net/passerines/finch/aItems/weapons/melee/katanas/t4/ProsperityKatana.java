package net.passerines.finch.aItems.weapons.melee.katanas.t4;

import de.slikey.effectlib.effect.CylinderEffect;
import de.slikey.effectlib.effect.FountainEffect;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchEffectManager;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class ProsperityKatana extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(15);
    Cooldown<Player> cd1 = new Cooldown<>(300);
    public ProsperityKatana() {
        super("ProsperityKatana", 4);
        this.attack = 30;
        this.critChance = 160;
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        displayName = Chat.formatC("&9Prosperity Katana");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
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
    @EventHandler
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 215, 84), 1.0F);
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.FLAME, 2, attack,80,30, dust);
            slash.drawSlash();
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.FLAME, 3, attack,90,60, dust);
                slash0.drawSlash();
            }, 4);

            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.FLAME, 4, attack,100,120, dust);
                slash0.drawSlash();
            }, 8);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.FLAME, 5, attack,110,180, dust);
                slash0.drawSlash();
            }, 12);
            cd.add(player);
        }
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0, false, false, true));
            cd1.add(player);
            player.sendMessage("You used ability");
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(5);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack gold = ItemManager.ITEM_HASH_MAP.get("CondensedEnergy").getItem();
        ItemStack handle = ItemManager.ITEM_HASH_MAP.get("DestinyKatana").getItem();
        ItemStack luck = ItemManager.ITEM_HASH_MAP.get("AngelicEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "CAC", "CBC", "CAC" , gold, handle, luck);
        finchRecipe.addRecipe();
    }
}
