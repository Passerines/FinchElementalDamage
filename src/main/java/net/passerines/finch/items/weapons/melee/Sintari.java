package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
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
    private Cooldown cd = new Cooldown(10);
    public Sintari() {
        super("Sintari");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
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
        if(id.equals(Util.getId(player.getInventory().getItemInMainHand())) && click.getAction().isLeftClick() && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() ,Particle.ASH, Particle.CRIT, 7,0,85,0);
            slash.drawSlash();
            cd.add(player, 7);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Sintari"));
        item.setItemMeta(itemMeta);
        itemMeta.setUnbreakable(true);
        return writeId(item);
    }
}
