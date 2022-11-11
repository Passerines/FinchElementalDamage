package net.passerines.finch.cmds;


import net.passerines.finch.FinchElementalDamage;

import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.util.Chat;
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
            if(ItemManager.ITEM_HASH_MAP.containsKey(Util.getId(item))){
                if(Util.getPrefix(item) == null){
                    ArrayList<ItemPrefix> typePrefixList = PrefixManager.getPrefixes(Util.getItemPrefixType(item));
                    int random = Util.rand(0, typePrefixList.size()-1);
                    ItemPrefix prefix = typePrefixList.get(random);
                    prefix.applyPrefix(item);
                    sender.sendMessage(Chat.format("&cSucessfully reforged your " + Chat.asLegacy(item.displayName()) +  " with " + prefix.getDisplayName() + "."));
                }
                else{
                    ItemPrefix prefix = PrefixManager.PREFIX_HASH_MAP.get(Util.getPrefix(item));
                    prefix.removePrefix(item);
                    sender.sendMessage(Chat.format("&c" + prefix.getDisplayName() + " was successfully removed from your " + Chat.asLegacy(item.displayName()) + ".") );
                }
                PlayerMap.PLAYERS.get(sender).calculateHand(item);
            }
            else{
                sender.sendMessage(Chat.format("&cPlease hold an item in your main hand."));
            }
        }
        return true;
    }
}

