package net.passerines.finch.trinkets;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class TrinketMenu implements Listener{
    private static final ItemStack placeholder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    static {
        placeholder.getItemMeta().getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte) 1);
    }

    private final FinchElementalDamage plugin = FinchElementalDamage.inst();
    private final Inventory egui;
    public TrinketMenu() {
        egui = Bukkit.createInventory(null, 9, Component.text("TrinketMenu"));
        Bukkit.getPluginManager().registerEvents(this, plugin);
        initialize();
    }
    public void open(Player player){
        player.openInventory(egui);
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent click) {
        if(click.getClickedInventory() != null && click.getClickedInventory().equals(egui)) {
            if(click.getCurrentItem() != null && click.getCurrentItem().hasItemMeta() && click.getCurrentItem().getItemMeta().getPersistentDataContainer().has(Util.getNamespacedKey("unmovable"))) {
                click.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void statAdder(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        Inventory inventory = event.getInventory();
        if(inventory.equals(egui)) {
            for (int i = 0; i < 3; i++) {
                ItemStack item = egui.getItem(i + 3);
                playerData.calculateAccessory(item, i);
            }
        }
    }
    public Inventory getMenu(){
        return egui;
    }
    public void initialize() {
        egui.setItem(0, placeholder);
        egui.setItem(1, placeholder);
        egui.setItem(2, placeholder);
        egui.setItem(6, placeholder);
        egui.setItem(7, placeholder);
        egui.setItem(8, placeholder);
    }
}
