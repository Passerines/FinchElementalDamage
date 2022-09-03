package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;


public class Teleporter extends FinchWeapon implements Listener {

    public Teleporter() {
        super("Teleporter");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(vPlayer.getMana() >= 75 && id.equals(Util.getId(item)) && event.getAction().isRightClick()) {
                int length = 7;
                World world = player.getWorld();
                Location loc1 = player.getLocation();
                Block b = player.getTargetBlock((Set<Material>)null, 12);
                Location loc = new Location(b.getWorld(), b.getX(), b.getY(), b.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                player.teleport(loc);
                vPlayer.setMana(vPlayer.getMana() - 75);
                String bar = Chat.format("&c-75 &bMana");
                Chat.sendActionBar(player, bar);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTeleporter"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
