package net.passerines.finch.items.weapons.ranged;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.ThrowBlade;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static net.passerines.finch.events.ElementalDamageEvent.Element.DARK;


public class CrescentKnives extends FinchWeapon implements Listener {

    public CrescentKnives() {
        super("CrescentKnives");
        this.damage = 5;
        this.dark = 2;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData vPlayer = PlayerMap.PLAYERS.get(player);
        ItemStack item = player.getInventory().getItemInMainHand();
        if((event.getAction().isRightClick() || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && cd.isOnCooldown(player)){
                ThrowBlade throwBlade = new ThrowBlade(id, player, Particle.CRIT_MAGIC, DARK, 1, vPlayer.getDamage());
                throwBlade.throwItem();
                cd.add(player, 20);
                Util.log("Thrown Crescent Knives");
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bCrescent Knives"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
