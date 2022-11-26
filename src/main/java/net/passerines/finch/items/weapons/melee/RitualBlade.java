package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static net.passerines.finch.events.ElementalDamageEvent.Element.ELECTRO;
import static net.passerines.finch.players.PlayerMap.PLAYERS;


public class RitualBlade extends FinchWeapon implements Listener {

    public RitualBlade() {
        super("Ritualblade");
        this.attack = 30;
        this.health = -10;
        this.defense = -10;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayerData = PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (id.equals(Util.getId(item)) && event.getAction().isRightClick()) {
            if (vPlayerData.getMana() >= 50 && vPlayerData.getHealth() >= 51) {
                vPlayerData.setMana(vPlayerData.getMana() - 50);
                vPlayerData.setHealth(vPlayerData.getHealth() - 50);
                String bar = Chat.format("&c-50 &bMana and Health");
                Chat.sendActionBar(player, bar);

                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () -> {
                }, 100);
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Ritual Blade"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
