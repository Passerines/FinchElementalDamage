package net.passerines.finch.aItems.weapons.melee.katanas.t1;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
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

public class GoldenKatana extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(3);
    public GoldenKatana() {
        super("GoldenKatana", 1);
        this.attack = 10;
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        displayName = Chat.formatC("&fGolden Katana");
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
    @Override
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 215, 84), 1.0F);
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.CRIT, 4, attack,85,0, dust);
            slash.drawSlash();
            cd.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(101);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemStack handle = ItemManager.ITEM_HASH_MAP.get("KatanaHandle").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, " A ", " A ", " B " , gold, handle);
        finchRecipe.addRecipe();
    }
}
