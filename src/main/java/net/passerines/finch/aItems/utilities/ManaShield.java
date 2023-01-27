package net.passerines.finch.aItems.utilities;

import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchEquipment;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.recipebook.RecipeBookDefault;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ManaShield extends FinchEquipment implements Listener, FinchCraftableItem {
    Cooldown cd = new Cooldown<>(100);
    ArrayList<Player> shieldedPlayers = new ArrayList<Player>();
    public ManaShield() {
        super("ManaShield", 4);
        displayName = Chat.formatC("&8Mana Shield");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("blue?");
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(event.getPlayer());
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isRightClick() && id.equals(Util.getId(item)) && cd.isOffCooldown(player)){
            int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), () -> {
                player.sendMessage("ManaShield Started");
                shieldedPlayers.add(player);
                if(shieldedPlayers.contains(player)){
                    player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 3, 1,1,1);
                }
            }, 0, 1);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () -> {
                player.sendMessage("ManaShield Ended");
                Bukkit.getScheduler().cancelTask(taskid);
                shieldedPlayers.remove(player);
                if(shieldedPlayers.contains(player)){
                    player.sendMessage("shieldedPlayers Has not been removed");
                }
            }, 60);
            cd.add(player);
        }
    }

    @EventHandler
    public void hit(ElementalDamageEvent event){
        if(event.getVictim() instanceof Player player){
            if(shieldedPlayers.contains(player)){
                PlayerData vPlayerData = new PlayerData(player);
                int manaDamageTaken = (int) (event.getDamage()*2);
                player.sendMessage("Mana before" + vPlayerData.getMana());
                if(vPlayerData.getMana() >= manaDamageTaken){
                    event.setDamage(event.getDamage() * 0.1);
                    vPlayerData.setMana((vPlayerData.getMana() - manaDamageTaken));
                    String bar = Chat.format("&c-" + manaDamageTaken + " &bMana");
                    Chat.sendActionBar(player, bar);
                    player.sendMessage("Mana after:" + vPlayerData.getMana());
                }
            }
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "AAA", "AAA" , diamond);
        finchRecipe.addRecipe();
    }
}
