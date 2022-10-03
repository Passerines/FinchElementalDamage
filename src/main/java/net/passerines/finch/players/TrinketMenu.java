package net.passerines.finch.players;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.http.WebSocket;

public class TrinketMenu implements Listener {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    private Inventory egui;

    private final int cooldownTimeMilliseconds = 6000;
    public TrinketMenu() {
        egui = Bukkit.createInventory(null, 9, Component.text("TrinketMenu"));
        Bukkit.getPluginManager().registerEvents(this, plugin);
        initialize();
    }
    public void open(Player player){
        player.openInventory(egui);
    }
    public void initialize() {

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = placeholder.getItemMeta();
        itemMeta.displayName(Chat.formatC(" "));
        egui.setItem(8, placeholder);
    }
}
