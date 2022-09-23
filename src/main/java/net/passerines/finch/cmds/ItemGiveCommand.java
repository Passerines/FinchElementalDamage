package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemGiveCommand implements CommandExecutor, TabCompleter {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    public ItemGiveCommand(){
        plugin.getCommand("itemgive").setExecutor(this);
        plugin.getCommand("itemgive").setTabCompleter(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("finch.admin")){
            if(sender instanceof Player player) {
                if(args.length > 0){
                    if(ItemManager.ITEM_HASH_MAP.containsKey(args[0])){
                        player.getInventory().addItem(ItemManager.ITEM_HASH_MAP.get(args[0]).getItem());
                        sender.sendMessage(Chat.format("&cYou have received: "+ Chat.asLegacy(ItemManager.ITEM_HASH_MAP.get(args[0]).getItem().getItemMeta().displayName())));
                    }
                    else{
                        sender.sendMessage(Chat.format("&cThere is no such item as: " + args[0]));
                    }
                }
                else{
                    sender.sendMessage(Chat.format("&cAnother argument is required for this command!"));
                }
                return true;
            }
        }
        else{
            sender.sendMessage(Chat.format("&cYou don't have permissions for this command!"));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> results = new ArrayList<>();
        if(args.length<=1) {
            Util.copyPartialContains(args[args.length-1], ItemManager.ITEM_HASH_MAP.keySet(), results);
        }
        return results;
    }
}
