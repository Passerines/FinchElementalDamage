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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantShop implements Listener {
    private Inventory enchantMenu = Bukkit.createInventory(null, 27, Chat.formatC("Enchants"));
    private HashMap<Integer, ItemEnchant> shopEnchants = new HashMap<Integer, ItemEnchant>();

    public EnchantShop(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        placeHolder();
        buyableEnchants();
    }

    public void placeHolder(){
        ItemStack placeHolder = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        int[] placeHolders = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for(int i = 0; i < placeHolders.length; i++){
            enchantMenu.setItem(placeHolders[i], placeHolder);
        }
    }


    public void buyableEnchants(){
        shopEnchants.put(10, EnchantManager.randomEnchant());
        shopEnchants.put(13, EnchantManager.randomEnchant());
        shopEnchants.put(16, EnchantManager.randomEnchant());
        for(Integer key : shopEnchants.keySet()){
            ItemStack enchantPlaceholder = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = enchantPlaceholder.getItemMeta();
            meta.displayName(shopEnchants.get(key).getDisplayName());
            enchantPlaceholder.setItemMeta(meta);
            enchantMenu.setItem(key, enchantPlaceholder);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTopInventory().equals(enchantMenu)){
            event.setCancelled(true);
            if(shopEnchants.containsKey(event.getSlot())){
                shopEnchants.get(event.getSlot()).applyEnchant(player.getInventory().getItemInMainHand(), Util.rand(1, shopEnchants.get(event.getSlot()).getMaxLevel()));
            }
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
