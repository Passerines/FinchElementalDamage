package net.passerines.finch.events.handler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.players.TrinketMenu;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
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
    public void onWeaponChange(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        player.sendMessage(Chat.formatC("Changed hand"));
        PlayerMap.PLAYERS.get(player).calculateHand(player.getInventory().getItem(event.getPreviousSlot()), player.getInventory().getItem(event.getNewSlot()));
    }
    @EventHandler
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
    }
}
