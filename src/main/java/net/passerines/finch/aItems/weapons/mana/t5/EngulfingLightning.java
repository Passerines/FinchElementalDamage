package net.passerines.finch.aItems.weapons.mana.t5;

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
import java.util.UUID;

public class EngulfingLightning extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(3);
    Cooldown cd1 = new Cooldown<>(30);
    Cooldown cd2 = new Cooldown<>(300);
    Cooldown cd3 = new Cooldown<>(1200);
    public EngulfingLightning() {
        super("EngulfingLightning", 6);
        this.attack = 135;
        this.mana = 600;
        this.manaRegen = 140;
        this.element = ElementalDamageEvent.Element.ELECTRO;

        // Set the displayname and lore
        displayName = Chat.formatC("&dEngulfing Lightning");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &dDivine Punishment (100 Mana)");
        lore.add("&7Summon a bolt of &dlightning in the direction");
        lore.add("&7you are facing dealing damage based on your &bMana");
        lore.add("&11.5 second Cooldown");
        lore.add("&6Ability: &dDivine Wrath");
        lore.add("&7Summon 50 bolts of &dlightning in a 10 block");
        lore.add("&7radius and damage scales with &bMana");
        lore.add("&115 second Cooldown");
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
    @EventHandler
    public void onEntityDamage(ElementalDamageEvent event) {
        if(event.getAttacker() instanceof Player player){
            if(id.equals(Util.getId(event.getWeapon())) && cd2.isOffCooldown(player)) {
                /*
                Location loc = event.getVictim().getLocation()l;
                LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
                lightningStrike.setCausingPlayer(player);
                lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("weapon"), PersistentDataType.STRING, id);
                ightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.DOUBLE, 100.0);
                 */
                PlayerData playerData = PlayerMap.PLAYERS.get(player);
                if(playerData.getManaMax() >= playerData.getMana()){
                    if(playerData.getManaMax() * 0.02 + playerData.getMana() < playerData.getManaMax()){
                        playerData.setMana(playerData.getMana() + (int) (playerData.getManaMax() * 0.02));
                        String bar = Chat.format("&bRestored " + (playerData.getManaMax() * 0.02) + " &bMana");
                        Chat.sendActionBar(player, bar);
                    }
                    else{
                        playerData.setManaMax(playerData.getManaMax());
                    }
                }
                cd2.add(player);
            }
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onLightning(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof LightningStrike lightningStrike){
            if(lightningStrike.getPersistentDataContainer().has(Util.getNamespacedKey("Elightning"))){
                event.setCancelled(true);
                new ElementalDamageEvent(lightningStrike.getCausingEntity(), event.getEntity(), EntityDamageEvent.DamageCause.LIGHTNING, ElementalDamageEvent.Element.ELECTRO, 10.0 + (PlayerMap.PLAYERS.get((Player) lightningStrike.getCausingEntity()).getManaMax() / 100f + 0.0)*6, ((Player) lightningStrike.getCausingEntity()).getInventory().getItemInMainHand()).apply();
            }
        }
    }
    @Override
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.ELECTRIC_SPARK, Particle.ELECTRIC_SPARK, 5, attack,2,0, null);
            slash.drawSlash();
            cd.add(player);
        }
        if(!player.isSneaking() && click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player) && PlayerMap.PLAYERS.get(player).getMana() >= 100){
            playerData.setMana(playerData.getMana()-100);
            HashSet<Material> transparent = new HashSet<>();
            transparent.add(Material.AIR);
            Block block = player.getTargetBlock(transparent, 80);
            Location loc = block.getLocation();
            LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
            lightningStrike.setCausingPlayer(player);
            lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("ELightning"), PersistentDataType.STRING, id);
            String bar = Chat.format("&c-100 &bMana");
            Chat.sendActionBar(player, bar);
            cd1.add(player);
        }
        if(player.isSneaking() && click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd2.isOffCooldown(player) && PlayerMap.PLAYERS.get(player).getMana() >= 1000){
            playerData.setMana(playerData.getMana()-1000);
            for(int i = 0; i <= 50; i++) {
                Location loc = player.getLocation();
                loc = loc.add(Util.rand(-10,10),Util.rand(-1,1),Util.rand(-10,10));
                LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
                lightningStrike.setCausingPlayer(player);
                lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("ELightning"), PersistentDataType.STRING, id);
            }
            String bar = Chat.format("&c-1000 &bMana");
            Chat.sendActionBar(player, bar);
            cd2.add(player);
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
        // Format the item instead of setting displayname and lore
        format(item);
        //
        return writeId(item);
    }
}
