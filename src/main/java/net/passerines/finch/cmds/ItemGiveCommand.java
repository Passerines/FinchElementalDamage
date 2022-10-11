package net.passerines.finch.cmds;

import net.kyori.adventure.title.TitlePart;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

public class ItemGiveCommand implements CommandExecutor, TabCompleter {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    public ItemGiveCommand(){
        plugin.getCommand("itemgive").setExecutor(this);
        plugin.getCommand("itemgive").setTabCompleter(this);
    }

    //        not included    0         1       2  (index)
    // Length in array is 3   1         2       3
    //if args is /itemgive "itemname" , give 1 item to commandSender
    //if args is /itemgive "itemname" player amount , give amount item to player
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("finch.admin")){
            if(sender instanceof Player player) {
                ItemStack itemStack;
                int amount = 1;
                if(args.length > 0){
                    if(ItemManager.ITEM_HASH_MAP.containsKey(args[0])){
                        itemStack = ItemManager.ITEM_HASH_MAP.get(args[0]).getItem();
                        if(args.length > 1){
                            try {
                                //if args is /itemgive "itemname" amount , give amount item to commandSender
                                amount = Integer.parseInt(args[1]);
                                if(player.getInventory().firstEmpty() < 0){
                                    Chat.sendTitle(player, "&cInventory Full");
                                }
                                else{
                                    itemStack.setAmount(amount);
                                    player.getInventory().addItem(itemStack);
                                }
                            }
                            catch(NumberFormatException e){
                                Player targetPlayer = Util.matchPlayer(args[1]);
                                if(targetPlayer != null){
                                    //if args is /itemgive "itemname" player , give 1 item to player
                                    if(targetPlayer.getInventory().firstEmpty() < 0){
                                        Chat.sendTitle(player, "&cInventory Full");
                                    }
                                    else if(args.length > 2){
                                        try{
                                            amount = Integer.parseInt(args[3]);
                                            itemStack.setAmount(amount);
                                            targetPlayer.getInventory().addItem(itemStack);
                                        }
                                        catch(NumberFormatException ev){
                                            player.sendMessage("Amount null");
                                        }
                                    }
                                    else{
                                        targetPlayer.getInventory().addItem(itemStack);
                                    }
                                }
                                else{
                                    sender.sendMessage(Chat.format("&cThere is no such player as: " + args[1]));
                                    return true;
                                }
                            }
                        }
                        else{
                            if(player.getInventory().firstEmpty() < 0){
                                Chat.sendTitle(player, "&cInventory Full");
                            }
                            else{
                                player.getInventory().addItem(itemStack);
                            }
                        }
                    }
                    else{
                        sender.sendMessage(Chat.format("&cThere is no such item as: " + args[0]));
                    }
                }
                else{
                    sender.sendMessage(Chat.format("&cAnother argument is required for this command!"));
                }
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
