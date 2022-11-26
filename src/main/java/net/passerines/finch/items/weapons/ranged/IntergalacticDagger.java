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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static net.passerines.finch.events.ElementalDamageEvent.Element.DARK;
import static net.passerines.finch.events.ElementalDamageEvent.Element.ELECTRO;


public class IntergalacticDagger extends FinchWeapon implements Listener {

    public IntergalacticDagger() {
        super("IntergalacticDagger");
        this.attack = 15;
        this.electro = 2;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData vPlayer = PlayerMap.PLAYERS.get(player);
        ItemStack item = player.getInventory().getItemInMainHand();
        if((id.equals(Util.getId(item)) && event.getAction().isRightClick() && cd.isOffCooldown(player))){
            ThrowBlade throwBlade = new ThrowBlade(id, player, Particle.SONIC_BOOM, ELECTRO, 5, vPlayer.getAttack()*500);
            throwBlade.throwItem();
            cd.add(player, 20);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bIntergalactic Dagger"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
