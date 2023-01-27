package net.passerines.finch.cmds;


import net.passerines.finch.FinchElementalDamage;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerMap;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.passerines.finch.enchants.EnchantManager.*;


public class EnchantCommand implements CommandExecutor, TabCompleter {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public EnchantCommand() {
        plugin.getCommand("ench").setExecutor(this);
        plugin.getCommand("ench").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            if(ItemManager.ITEM_HASH_MAP.containsKey(Util.getId(item))){
                if(args.length == 2){
                    if(ENCHANTS_HASH_MAP.get(args[0]) != null){
                        FinchItem finchItem = Util.getFinchItem(item);
                        try{
                            if(getType(finchItem) == getEnchant(args[0]).getType()){
                                ENCHANTS_HASH_MAP.get(args[0]).applyEnchant(item, Integer.parseInt(args[1]));
                                sender.sendMessage(Chat.format("&cSucessfully enchanted your " + Chat.asLegacy(item.displayName()) +  " with " + args[0] + "."));
                                PlayerMap.PLAYERS.get(sender).calculateHand(item);
                            }
                            else{
                                sender.sendMessage("Item has the wrong enchantment type.");
                            }
                        }
                        catch(NumberFormatException commandException){
                            sender.sendMessage("&cUsuage: /ench <id> <level>");
                        }
                    }
                    else{
                        sender.sendMessage("&cThe id you typed does not exist.");
                    }
                }else{
                    sender.sendMessage("&cUsuage: /ench <id> <level>");
                }
            }
            else{
                sender.sendMessage(Chat.format("&cPlease hold an item in your main hand."));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> results = new ArrayList<>();
        if(args.length == 1){
            if(sender instanceof Player){
                Util.log("DEBUG:tried to autocomplete");
                Util.copyPartialContains(args[0], getOkEnchants(((Player) sender).getInventory().getItemInMainHand()), results);
            }
            else{
                Util.log("go away console");
            }
        }
        if(args.length == 2){

            Util.copyPartialContains(args[1], Util.getOnlinePlayerNames(), results);
        }
        return results;
    }
}

