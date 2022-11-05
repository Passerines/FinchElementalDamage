package net.passerines.finch.cmds;


import net.passerines.finch.FinchElementalDamage;

import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class ReforgeCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public ReforgeCommand() {
        plugin.getCommand("reforge").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            ItemPrefix prefix = PrefixManager.PREFIX_HASH_MAP.get("armorbrittle");
            if(Util.getPrefix(item) == null){
                prefix.applyPrefix(item);
            }
            else{
                prefix.removePrefix(item);
            }
            PlayerMap.PLAYERS.get(sender).calculateHand(item);
        }
        return true;
    }
}

