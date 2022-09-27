package net.passerines.finch.items.weapons.melee.spears;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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
    /*
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getCause()){
            if(id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player)) {
                Location loc = event.getEntity().getLocation();
                LightningStrike lightningStrike = loc.getWorld().strikeLightning(loc);
                lightningStrike.setCausingPlayer(player);
                lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("weapon"), PersistentDataType.STRING, id);
                lightningStrike.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.DOUBLE, 100.0);
                loc.getWorld().str
            }
        }
    }
    */
    @EventHandler
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.ELECTRIC_SPARK, Particle.DRAGON_BREATH, 5, damage,2,0);
            slash.drawSlash();
            cd.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&dEngulfing Lightning"));
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(7);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
