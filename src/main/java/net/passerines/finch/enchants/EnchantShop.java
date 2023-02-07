package net.passerines.finch.enchants;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class EnchantShop implements Listener {
    private Inventory enchantMenu = Bukkit.createInventory(null, InventoryType.CHEST, Chat.formatC("Enchants"));

    public EnchantShop(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event){
        if(event.getClickedBlock() != null){
            if(event.getClickedBlock().getType() == Material.ENCHANTING_TABLE){
                event.getPlayer().openInventory(enchantMenu);
            }
        }
    }
}
