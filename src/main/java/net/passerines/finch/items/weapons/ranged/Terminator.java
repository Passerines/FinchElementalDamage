package net.passerines.finch.items.weapons.ranged;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
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


public class Terminator extends FinchWeapon implements Listener {

    public Terminator() {
        super("Terminator");
        this.attack = 100;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if((event.getAction().isLeftClick() || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) || event.getAction().isRightClick() || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && cd.isOffCooldown(player)) {
            if(id.equals(Util.getId(item))) {
                FinchArrow finchArrow = new FinchArrow(player, item, 4, -3, this.attack);
                FinchArrow finchArrow1 = new FinchArrow(player, item, 4, 0, this.attack);
                FinchArrow finchArrow2 = new FinchArrow(player, item, 4, 3, this.attack);
                finchArrow.shootElectroArrow();
                finchArrow1.shootLightArrow();
                finchArrow2.shootDarkArrow();
                cd.add(player, 3);
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Terminator"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
