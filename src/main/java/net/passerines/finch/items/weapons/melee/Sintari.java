package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
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

public class Sintari extends FinchWeapon implements Listener {
    public Sintari() {
        super("Sintari");
        this.damage = 30;
        this.health = -10;
        this.defense = -10;
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
        Slash slash = new Slash(player, player.getEyeLocation(), getItem() ,Particle.ASH, Particle.CRIT, 7,0,85,0);
        slash.drawSlash();
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Sintari"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
