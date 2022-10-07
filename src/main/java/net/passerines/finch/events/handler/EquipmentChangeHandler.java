package net.passerines.finch.events.handler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
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
        Util.log("Event ONPICKUP Called");
        Entity entity = event.getEntity();
        if(entity instanceof Player player) {
            player.sendMessage(Chat.formatC("(Pickup)"));
            if(player.getInventory().getItemInMainHand().getType().isAir()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                    if(!player.getInventory().getItemInMainHand().getType().isAir()){
                        PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                        player.sendMessage(Chat.formatC("(Calculated Picked Up Item)"));
                    }
                });
            }
        }
    }
    @EventHandler
    public void playerSwitchItem(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        PlayerMap.PLAYERS.get(player).calculateHand(event.getMainHandItem());
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Util.log("Event ONDROP Called");
        Player player = event.getPlayer();
        player.sendMessage(Chat.formatC("Changed hand (Drop)"));
        if(player.getInventory().getItemInMainHand().getType().isAir()) {
            PlayerMap.PLAYERS.get(player).calculateHand(new ItemStack(Material.AIR));
        }
    }
    @EventHandler
    public void onWeaponInventoryChange(InventoryClickEvent event){
        Util.log("Event ONWEAPONINVENTORYCHANGE Called");
        Player player = (Player) event.getWhoClicked();
        player.sendMessage(Chat.formatC("Changed Inventory"));
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(),()->{
            if(!PlayerMap.PLAYERS.get(player).getOldItem().isSimilar(player.getInventory().getItemInMainHand())){
                PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
            }
        });
        /*switch (event.getAction()){
            case PLACE_ALL, PLACE_ONE, PLACE_SOME ->{
                if(event.getSlotType().equals(InventoryType.SlotType.QUICKBAR)){
                    if(player.getInventory().getHeldItemSlot() == event.getSlot()){
                        PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                    }
                }
            }
            case HOTBAR_SWAP -> {
                if(event.getSlotType().equals(InventoryType.SlotType.QUICKBAR)){
                    if(player.getInventory().getHeldItemSlot() == event.getSlot()){
                        PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                    }
                }
                if(event.get){}
        }
         */
    }
    @EventHandler
    public void onWeaponChange(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        player.sendMessage(Chat.formatC("Changed hand"));
        PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItem(event.getNewSlot()));
    }
    @EventHandler
    public void dragEvent(InventoryDragEvent event){
        Player player = (Player) event.getWhoClicked();
        if(player.getInventory().getItemInMainHand().getType().isAir()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                if(!player.getInventory().getItemInMainHand().getType().isAir()){
                    PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItemInMainHand());
                    player.sendMessage(Chat.formatC("(Calculated Picked Up Item)"));
                }
            });
        }
    }
    /*@EventHandler
    public void clickEvent(InventoryClickEvent click) {
        Player player = (Player) click.getWhoClicked();
        if(click.getClickedInventory() != null && click.getClickedInventory().equals(PlayerMap.PLAYERS.get(player))) {
            ItemStack[] items = click.getInventory().getContents();
            for(ItemStack item : items) {
                if (ItemManager.ITEM_HASH_MAP.containsKey(Util.getId(item))){
                    FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get((Util.getId(item)));
                    if(finchItem instanceof FinchTrinkets finchTrinkets){

                    }
                }
            }
        }
    }*/
}
