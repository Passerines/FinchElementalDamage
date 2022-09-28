package net.passerines.finch.players;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.net.http.WebSocket;

public class TrinketMenu implements Listener {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    private Inventory egui;

    private final int cooldownTimeMilliseconds = 6000;
    public TrinketMenu() {
        egui = Bukkit.createInventory(null, 27, Component.text("TrinketMenu"));
        Bukkit.getPluginManager().registerEvents(this, plugin);
        initialize();
    }
    public void open(Player player){
        player.openInventory(egui);
    }
    public void initialize() {

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        egui.setItem(0, placeholder);
        egui.setItem(1, placeholder);
        egui.setItem(2, placeholder);
        egui.setItem(3, placeholder);
        egui.setItem(4, placeholder);
        egui.setItem(5, placeholder);
        egui.setItem(6, placeholder);
        egui.setItem(7, placeholder);
        egui.setItem(8, placeholder);
        egui.setItem(9, placeholder);
    }
}
