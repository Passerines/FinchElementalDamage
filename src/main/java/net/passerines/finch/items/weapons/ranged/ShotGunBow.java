package net.passerines.finch.items.weapons.ranged;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ShotGunBow extends FinchWeapon implements Listener {

    public ShotGunBow() {
        super("ShotGunBow");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (event.getAction().isLeftClick() || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (id.equals(Util.getId(item))) {
             Util.ShootArrow(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 8, 5);
             Util.ShootArrow(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0, 5);
             Util.ShootArrow(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, -8, 5);
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cShotGun Bow"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
