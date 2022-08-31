package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.ItemManager;
import net.passerines.finch.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemGiveCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    public ItemGiveCommand(){
        plugin.getCommand("ItemGive").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("finchelementaldamage.cmd.itemgive")){
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
}
