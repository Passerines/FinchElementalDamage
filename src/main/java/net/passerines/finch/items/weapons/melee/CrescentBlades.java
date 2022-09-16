package net.passerines.finch.items.weapons.melee;

import jdk.jfr.Label;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.attacks.ThrowBlade;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.http.WebSocket;

public class CrescentBlades extends FinchWeapon implements Listener {

    public CrescentBlades() {
        super("CresentBlades");
        this.damage = 45;
        this.defense = 45;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand()))){
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.ASH, Particle.CRIT, 7, damage,85,0);
            slash.drawSlash();
        }
        if(click.getAction().isRightClick()){
            ThrowBlade throwBlade = new ThrowBlade(id, player, Particle.ASH, ElementalDamageEvent.Element.DARK, 1, damage);
            throwBlade.throwItem();
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
