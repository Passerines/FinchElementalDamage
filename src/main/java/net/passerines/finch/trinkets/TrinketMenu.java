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
    public void statAdder(InventoryClickEvent click) {
        Player player = (Player) click.getWhoClicked();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(click.getClickedInventory() != null && click.getClickedInventory().equals(egui)){
            if(ItemManager.ITEM_HASH_MAP.get(Util.getId(egui.getItem(click.getSlot()))) instanceof FinchTrinkets finchTrinkets){
                if(click.getSlot() == 1 && finchTrinkets.getType() == 1){
                    playerData.calculateAccessory(click.getCurrentItem(), click.getCursor());
                }
                else if(click.getSlot() == 2 && finchTrinkets.getType() == 2){
                    playerData.calculateAccessory(click.getCurrentItem(), click.getCursor());
                }
                else if(click.getSlot() == 3 && finchTrinkets.getType() == 3){
                    playerData.calculateAccessory(click.getCurrentItem(), click.getCursor());
                }
                else{
                    click.setCancelled(true);
                }
            }
            else if(egui.getItem(click.getSlot()).getType().isAir()){
                playerData.calculateAccessory(click.getCursor(), egui.getItem(click.getSlot()));
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
