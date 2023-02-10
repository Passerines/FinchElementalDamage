package net.passerines.finch.enchants;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class EnchantShop implements Listener {
    private Inventory enchantMenu = Bukkit.createInventory(null, 27, Chat.formatC("Enchants"));

    public EnchantShop(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        placeHolder();

    }

    public void placeHolder(){
        ItemStack placeHolder = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        //excluding 12, 14, 16
        int[] placeHolders = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        int[] enchants = {12, 14, 16};
        for(int i = 0; i < placeHolders.length; i++){
            enchantMenu.setItem(placeHolders[i], placeHolder);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event){
        Util.log("InteractEvent Fired");
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTopInventory().equals(enchantMenu)){
            event.setCancelled(true);
            Util.log("InteractEvent cancelled");
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event){
        if(event.getClickedBlock() != null){
            if(event.getClickedBlock().getType() == Material.ENCHANTING_TABLE){
                if(event.getAction().isRightClick()){
                    event.setCancelled(true);
                    event.getPlayer().openInventory(enchantMenu);
                }
            }
        }
    }

}
