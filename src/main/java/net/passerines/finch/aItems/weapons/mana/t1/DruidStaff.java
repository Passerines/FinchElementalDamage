package net.passerines.finch.aItems.weapons.mana.t1;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
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

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DruidStaff extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(30);
    public DruidStaff() {
        super("DruidStaff", 1);
        this.mana = 50;
        this.manaRegen = 25;
        this.element = ElementalDamageEvent.Element.EARTH;
        displayName = Chat.formatC("&aDruid Staff");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&aAbility: &aEarthly &7Decay");
        lore.add("&aSummon A Bolt Of &7Decay &aIn The Direction You Are Facing.");
        lore.add("&aCosts 75 &bMana");
        lore.add("&a1.5 second Cooldown");
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
        PlayerData vPlayer = PlayerMap.PLAYERS.get(player);
        int damage = (int) (vPlayer.getManaMax()*0.05);
        if(vPlayer.getMana() >= 75 && click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            AtomicInteger i = new AtomicInteger(5);
            Location loc = player.getEyeLocation();
            int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst() ,()->{
                loc.add(loc.getDirection().multiply(i.get()));
                Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.FALLING_DUST, Particle.ASH, 6, damage,10,0);
                slash.drawSlash();
                }, 0, 1);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->Bukkit.getScheduler().cancelTask(taskid), 60);
            vPlayer.setMana(vPlayer.getMana() - 75);
            String bar = Chat.format("&a-75 &bMana");
            Chat.sendActionBar(player, bar);
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
        ItemStack wool = new ItemStack(Material.WHITE_WOOL);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, " A ", " A ", " B " , gold, wool);
        finchRecipe.addRecipe();
    }
}
