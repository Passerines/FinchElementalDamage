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
                    ArrayList<ItemPrefix> prefixList = new ArrayList<>(PrefixManager.PREFIX_HASH_MAP.values());
                    if(ItemManager.ITEM_HASH_MAP.get(Util.getId(item)) instanceof FinchWeapon){
                        ArrayList<ItemPrefix> weaponPrefixList = new ArrayList<>();
                        for(int i = 0; i < prefixList.size(); i++){
                            if(prefixList.get(i).getType() == ItemPrefix.Type.WEAPON){
                                weaponPrefixList.add(prefixList.get(i));
                            }
                        }
                        int random = Util.rand(0, weaponPrefixList.size()-1);
                        ItemPrefix prefix = weaponPrefixList.get(random);
                        prefix.applyPrefix(item);
                        sender.sendMessage(Chat.format("&cSucessfully reforged weapon with " + prefix.getDisplayName() + "."));
                    }else{
                        sender.sendMessage(Chat.format("&cPlease hold an item in your main hand."));
                    }
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

