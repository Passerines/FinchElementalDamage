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

public class DevourScythe extends FinchWeapon implements Listener {
    public DevourScythe() {
        super("DevourScythe");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        this.damage = 100;
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
                vPlayerData.setHealth(vPlayerData.getHealth() + vPlayerData.getDamage() * 0.05);
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Devour Scythe"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
