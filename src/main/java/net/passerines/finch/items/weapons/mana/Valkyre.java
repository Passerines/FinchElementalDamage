package net.passerines.finch.items.weapons.mana;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.passerines.finch.events.ElementalDamageEvent.Element.ELECTRO;
import static net.passerines.finch.players.PlayerMap.PLAYERS;


public class Valkyre extends FinchWeapon implements Listener {

    public Valkyre() {
        super("Valkyre");
        this.damage = 50;
        this.mana = 500;
        this.light = 2;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        PlayerData vPlayer = PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (id.equals(Util.getId(item)) && vPlayer.getMana() >= 250 && event.getAction().isRightClick()) {
            Location loc1 = player.getLocation();
            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc1, 10);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 0.5f);
            List<Entity> entityList = player.getNearbyEntities(4, 4, 4);
            for(Entity entity : entityList){
                new ElementalDamageEvent(player, entity, ELECTRO, ((1 + vPlayer.getManaMax() / 90 + 0.0)*5)).apply();
            }
            vPlayer.setMana(vPlayer.getMana() - 250);
            String bar = Chat.format("&c-250 &bMana");
            Chat.sendActionBar(player, bar);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Entity entity =  event.getDamager();
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.2f, 0.7f);
            entity.getWorld().playSound(entity.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 0.3f, 0.5f);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cValkyre"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&f +50 &cDamage")));
        lore.add(Component.text(Chat.format("&f +500 &bMana " )));
        lore.add(Component.text(Chat.format(" ")));;
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
