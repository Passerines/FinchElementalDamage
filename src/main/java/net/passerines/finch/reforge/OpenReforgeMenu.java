package net.passerines.finch.reforge;

import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenReforgeMenu implements Listener {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public OpenReforgeMenu(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if(event.getInventory().getType() == InventoryType.ANVIL){
            Player player = (Player) event.getPlayer();
            ReforgeMenu reforgeMenu = new ReforgeMenu();
            reforgeMenu.initialize();
            event.setCancelled(true);
            reforgeMenu.open(player);
        }
    }
}
