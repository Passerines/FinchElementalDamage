package net.passerines.finch.cmds;


import net.passerines.finch.FinchElementalDamage;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static net.passerines.finch.enchants.EnchantManager.ENCHANTS_HASH_MAP;


public class EnchantCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public EnchantCommand() {
        plugin.getCommand("ench").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            if(ItemManager.ITEM_HASH_MAP.containsKey(Util.getId(item))){
                if(args.length == 2){
                    if(ENCHANTS_HASH_MAP.get(args[0]) != null){
                        try{
                            ENCHANTS_HASH_MAP.get(args[0]).applyEnchant(item, Integer.parseInt(args[1]));
                            sender.sendMessage(Chat.format("&cSucessfully enchanted your " + Chat.asLegacy(item.displayName()) +  " with " + args[0] + "."));
                            PlayerMap.PLAYERS.get(sender).calculateHand(item);
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
}

