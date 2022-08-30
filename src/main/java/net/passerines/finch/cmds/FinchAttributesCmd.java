package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.passerines.finch.util.Util.log;

public class FinchAttributesCmd implements CommandExecutor {



    public FinchAttributesCmd(){
        FinchElementalDamage.inst().getCommand("finchattributes").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0]) {
                case "setMaxHealth" -> {
                    sender.sendMessage("Havent added this yet, TODO: FINISH THIS ");
                }

                case "setMaxDefense" -> {
                    sender.sendMessage("Havent added this yet, TODO: FINISH THIS pls ");
                }

                case "setMaxMana" -> {
                    sender.sendMessage("Havent added this yet, TODO: FINISH THIS davaid");
                }

                default -> {
                    sender.sendMessage("Unknown Argument. For help, type /finchattributes");
                }
            }
        }else{
            sender.sendMessage("big blob of help code; dont want to write. get gud");
        }
        return true;
    }




}

