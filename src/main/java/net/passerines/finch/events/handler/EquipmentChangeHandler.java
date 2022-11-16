package net.passerines.finch.events.handler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EquipmentChangeHandler implements Listener {

    public EquipmentChangeHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        PlayerMap.PLAYERS.get(event.getPlayer()).calculatePiece(event.getOldItem(), event.getNewItem());
    }
    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player player) {
            if(player.getInventory().getItemInMainHand().getType().isAir()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                    if(!player.getInventory().getItemInMainHand().getType().isAir()){
                        //PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                        Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, player.getInventory().getItemInMainHand()));
                    }
                });
            }
        }
    }
    @EventHandler
    public void playerSwitchItem(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        //PlayerMap.PLAYERS.get(player).calculateHand(event.getMainHandItem());
        Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, event.getMainHandItem()));

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType().isAir()) {
            //PlayerMap.PLAYERS.get(player).calculateHand(new ItemStack(Material.AIR));
            Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, new ItemStack(Material.AIR)));
        }
    }
    @EventHandler
    public void onWeaponInventoryChange(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(),()->{
            if(PlayerMap.PLAYERS.get(player).getOldItem() == null || !PlayerMap.PLAYERS.get(player).getOldItem().isSimilar(player.getInventory().getItemInMainHand())) {
                //PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, player.getInventory().getItemInMainHand()));
            }
        });
    }
    @EventHandler
    public void onWeaponChange(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        //PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItem(event.getNewSlot()));
        Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, player.getInventory().getItem(event.getNewSlot())));

    }
    @EventHandler
    public void dragEvent(InventoryDragEvent event){
        Player player = (Player) event.getWhoClicked();
        if(player.getInventory().getItemInMainHand().getType().isAir()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                if(!player.getInventory().getItemInMainHand().getType().isAir()){
                    //PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                    Bukkit.getPluginManager().callEvent(new ItemChangeEvent(player, player.getInventory().getItemInMainHand()));
                }
            });
        }
    }
    @EventHandler
    public void onItemChange(ItemChangeEvent itemChangeEvent){
        Player player = itemChangeEvent.getPlayer();
        ItemStack item = itemChangeEvent.getItemStack();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        playerData.calculateHand(item);
    }
}
