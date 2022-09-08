package net.passerines.finch.items.weapons.ranged;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class HellfireCrossbow extends FinchWeapon implements Listener {

    public HellfireCrossbow() {
        super("HellfireCrossbow");
        this.fire = 2;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private Cooldown cd = new Cooldown(15);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && id.equals(Util.getId(item)) && cd.isOffCooldown(player)){
            Util.shootArrow(player, Sound.BLOCK_METAL_HIT, 0, 8);
            Util.shootArrow(player, Sound.BLOCK_METAL_HIT, 0, 8);
            Util.shootArrow(player, Sound.BLOCK_METAL_HIT, 0, 8);
            Util.shootArrow(player, Sound.BLOCK_METAL_HIT, 0, 8);
            cd.add(player);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cHellfire Crossbow"));
        item.setItemMeta(itemMeta);
        itemMeta.setUnbreakable(true);
        return writeId(item);
    }
}
