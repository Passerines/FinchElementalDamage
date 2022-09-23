package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.DrawLine;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
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

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class Comet extends FinchWeapon implements Listener {
    private Cooldown cd = new Cooldown(10);
    public Comet() {
        super("Comet");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
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
        PlayerData vPlayer = PLAYERS.get(click.getPlayer());
        Player player = click.getPlayer();
        if(id.equals(Util.getId(player.getInventory().getItemInMainHand())) && click.getAction().isLeftClick() && cd.isOffCooldown(player)){
            new DrawLine(player, player.getEyeLocation(), getItem() ,Particle.ENCHANTMENT_TABLE, Particle.SOUL, 50,(1 + vPlayer.getManaMax()*0.1)).draw();
            cd.add(player, 7);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Comet"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
