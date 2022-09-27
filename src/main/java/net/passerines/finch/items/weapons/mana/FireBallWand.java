package net.passerines.finch.items.weapons.mana;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;


public class FireBallWand extends FinchWeapon implements Listener {


    public FireBallWand() {
        super("FireBallWand");
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    private Cooldown<Player> cd = new Cooldown<>(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && vPlayer.getMana() >= 50 && id.equals( Util.getId(item)) && cd.isOffCooldown(player)){
            Fireball fireball = player.launchProjectile(Fireball.class);
            fireball.getPersistentDataContainer().set(Util.getNamespacedKey("weapon"), PersistentDataType.STRING, id);
            fireball.getPersistentDataContainer().set(Util.getNamespacedKey("damage"), PersistentDataType.DOUBLE, (1 + vPlayer.getManaMax() / 100 + 0.0)*10);
            fireball.setYield(5);
            vPlayer.setMana(vPlayer.getMana() - 50);
            String bar = Chat.format("&c-50 &bMana");
            Chat.sendActionBar(player, bar);
            cd.add(player, 5);
        }
    }

    @EventHandler
    public void onDamage(ElementalDamageEvent event){
        if(event.getAttacker() instanceof Fireball){
            if(id.equals(event.getAttacker().getPersistentDataContainer().get(Util.getNamespacedKey("weapon"), PersistentDataType.STRING))){
                Vector victimPosition = event.getVictim().getLocation().toVector();
                Vector fireBallPosition = event.getAttacker().getLocation().toVector();
                Vector offset = victimPosition.subtract(fireBallPosition);
                double magnitude = offset.length();
                Vector unitDirection = offset.clone().normalize();
                event.setDamage(event.getAttacker().getPersistentDataContainer().get(Util.getNamespacedKey("damage"), PersistentDataType.DOUBLE));
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cFireBall Wand"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
