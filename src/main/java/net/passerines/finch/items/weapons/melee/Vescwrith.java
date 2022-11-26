package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static net.passerines.finch.events.ElementalDamageEvent.Element.DARK;
import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class Vescwrith extends FinchWeapon implements Listener {
    public Vescwrith() {
        super("Vescwrith");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        this.attack = 10;
        this.health = -10;
        this.defense = -10;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        PlayerData vPlayerData = PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();

    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player) {
            PlayerData vPlayerData = PLAYERS.get(event.getDamager());
            Player player = (Player) event.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            if(id.equals(Util.getId(item))){
                for(int i = 0; i < 50; i+=10){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () ->{
                        new ElementalDamageEvent(player, event.getEntity(), DARK, vPlayerData.getAttack()-3).apply();
                    }, i);
                }
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Vescwrith"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
