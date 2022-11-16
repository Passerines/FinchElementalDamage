package net.passerines.finch.aItems.weapons.ranged.artifacts;

import de.slikey.effectlib.effect.*;
import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchEffectManager;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.events.handler.ItemChangeEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchBow;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BowOfTheElements extends FinchBow implements Listener {
    public BowOfTheElements() {
        super("BowOfTheElements");
        this.bowDamage = 150;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    public static final HashMap<Player, ShieldEffect> SHIELDED_PLAYER_BOE = new HashMap<>();
    private final Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isLeftClick() && cd.isOffCooldown(player)) {
            if(id.equals(Util.getId(item))) {
                FinchArrow finchArrow = new FinchArrow(player, 2, 0, this.bowDamage);
                cd.add(player);
                PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
                if(data.getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (0)){
                    data.set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 1);
                    finchArrow.shootWaterArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralWater");
                    SHIELDED_PLAYER_BOE.get(player).particle = Particle.CRIMSON_SPORE;
                }
                else if(data.getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (1)){
                    data.set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0);
                    finchArrow.shootWaterArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralFire");
                    SHIELDED_PLAYER_BOE.get(player).particle = Particle.BUBBLE_POP;

                }
            }
        }
    }
    @EventHandler
    public void onContact(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow arrow){
            if(arrow.getPersistentDataContainer().has(Util.getNamespacedKey("ElementalArrow"))){
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                    Location loc = arrow.getLocation();
                    HelixEffect effect = new HelixEffect(FinchEffectManager.getEffectManager());
                    effect.setLocation(loc.add(0, 0,0));
                    FinchEffectManager.getEffectManager().start(effect);
                }, 6);

            }
        }
    }
    @EventHandler
    public void onHold(ItemChangeEvent event){
        Player player = event.getPlayer();
        if(SHIELDED_PLAYER_BOE.containsKey(player)){
            FinchEffectManager.getEffectManager().done(SHIELDED_PLAYER_BOE.get(player));
            SHIELDED_PLAYER_BOE.remove(player);
        }
        if(id.equals(Util.getId(event.getItemStack()))){
            Location loc = player.getEyeLocation();
            ShieldEffect shieldEffect =  new ShieldEffect(FinchEffectManager.getEffectManager());
            shieldEffect.setLocation(loc);
            shieldEffect.setEntity(player);
            shieldEffect.setTargetEntity(player);
            shieldEffect.duration = Integer.MAX_VALUE;
            FinchEffectManager.getEffectManager().start(shieldEffect);
            SHIELDED_PLAYER_BOE.put(player, shieldEffect);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fBow of the &4E&6l&ae&bm&9e&dn&5t&8s"));
        item.getItemMeta().getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0);
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&4Damage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
