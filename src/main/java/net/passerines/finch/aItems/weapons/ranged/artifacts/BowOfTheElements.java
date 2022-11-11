package net.passerines.finch.aItems.weapons.ranged.artifacts;

import de.slikey.effectlib.effect.*;
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
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

public class BowOfTheElements extends FinchBow implements Listener {
    public BowOfTheElements() {
        super("BowOfTheElements");
        this.bowDamage = 150;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    private final Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isLeftClick() && cd.isOffCooldown(player)) {
            if(id.equals(Util.getId(item))) {
                FinchArrow finchArrow = new FinchArrow(player, 2, 0, this.bowDamage);
                finchArrow.shootWaterArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "VortexSplash");
                cd.add(player);
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
        event.getPlayer().sendMessage("SwitchHand");
        if(id.equals(Util.getId(event.getItemStack()))){
            event.getPlayer().sendMessage("You are holding bow of elements");
            Player player = event.getPlayer();
            if(player.getPersistentDataContainer().has(Util.getNamespacedKey("shieldedBOE"))){

            }
            Location loc = player.getEyeLocation();
            player.getPersistentDataContainer().set(Util.getNamespacedKey("shieldedBOE"), PersistentDataType.BYTE, (byte) 1);
            ShieldEffect shieldEffect =  new ShieldEffect(FinchEffectManager.getEffectManager());
            shieldEffect.setLocation(loc);
            shieldEffect.setEntity(player);
            shieldEffect.setTargetEntity(player);
            shieldEffect.particle = Particle.TOTEM;
            FinchEffectManager.getEffectManager().start(shieldEffect);
        }
        else if(event.getPlayer().getPersistentDataContainer().has(Util.getNamespacedKey("shieldedBOE"))){

        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fBow of the &4E&6l&ae&bm&9e&dn&5t&8s"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
