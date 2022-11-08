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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ReforgeCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public ReforgeCommand() {
        plugin.getCommand("reforge").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            ArrayList<String> prefixList = new ArrayList<>(PrefixManager.PREFIX_HASH_MAP.keySet());
            int random = Util.rand(0, prefixList.size()-1);
            ItemPrefix prefix = PrefixManager.PREFIX_HASH_MAP.get(prefixList.get(random));
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

