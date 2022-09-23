package net.passerines.finch.items.weapons.melee;

import jdk.jfr.Label;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.attacks.ThrowBlade;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.http.WebSocket;

public class CrescentBlades extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(25);
    Cooldown cd0 = new Cooldown<>(3);
    public CrescentBlades() {
        super("CresentBlades");
        this.damage = 45;
        this.defense = 45;
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
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd0.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.ASH, Particle.CRIT, 7, damage,85,0);
            slash.drawSlash();
            cd0.add(player);
        }
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            ThrowBlade throwBlade = new ThrowBlade(id, player, Particle.ASH, ElementalDamageEvent.Element.DARK, 1, damage);
            throwBlade.throwItem();
            cd.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HOE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Crescent Blades"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
