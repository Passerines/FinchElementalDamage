package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static net.passerines.finch.players.PlayerMap.PLAYERS;


public class ReforgeCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public ReforgeCommand() {
        plugin.getCommand("reforge").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ItemPrefix prefix = new ItemPrefix("chicken", 2, 600, 1233, 690, 420);
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            prefix.applyPrefix(item);
        }
        return true;
    }
}

