package net.passerines.finch.events.handler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.players.TrinketMenu;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.inventory.ItemStack;

public class EquipmentChangeHandler implements Listener {

    public EquipmentChangeHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        PlayerMap.PLAYERS.get(event.getPlayer()).calculate();
    }
    @EventHandler
    public void onWeaponChange(PlayerChangedMainHandEvent event){PlayerMap.PLAYERS.get(event.getPlayer()).calculate();}
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
