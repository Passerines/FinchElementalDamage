package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
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

public class ManaKatana extends FinchWeapon implements Listener {
    private Cooldown cd = new Cooldown(10);
    public ManaKatana() {
        super("ManaKatana");
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
            Slash slash = new Slash(player, player.getEyeLocation(), getItem() ,Particle.DRAGON_BREATH, Particle.BUBBLE_POP, 7,(1 + vPlayer.getManaMax()*0.01),85,0);
            slash.drawSlash();
            cd.add(player, 7);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Mana Katana"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
