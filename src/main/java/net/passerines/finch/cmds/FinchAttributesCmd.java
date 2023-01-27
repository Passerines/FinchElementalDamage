package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

import static net.passerines.finch.util.Util.log;
import static net.passerines.finch.util.Util.matchPlayer;


public class FinchAttributesCmd implements CommandExecutor, TabCompleter {

    public FinchAttributesCmd(){
        FinchElementalDamage.inst().getCommand("finchattributes").setExecutor(this);
        FinchElementalDamage.inst().getCommand("finchattributes").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length > 0){
            Player targetPlayer = matchPlayer(args[1]);
            PlayerData targetPlayerData = PLAYERS.get(targetPlayer);
            switch(args[0]) {
                case "setMaxHealth" -> {
                    targetPlayerData.setHealthMax(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " health to : " + args[1]);
                }

                case "setDefense" -> {
                    targetPlayerData.setDefense(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " defense to : " + args[1]);
                }

                case "setMaxMana" -> {
                    targetPlayerData.setManaMax(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " mana to : " + args[1]);
                }

                case "setAttack" -> {
                    targetPlayerData.setAttack(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " attack to : " + args[1]);
                }

                case "setStrength" -> {
                    targetPlayerData.setStrength(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " strength to : " + args[1]);
                }

                case "setDexterity" -> {
                    targetPlayerData.setDexterity(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " dexterity to : " + args[1]);
                }

                case "heal" -> {
                    targetPlayerData.setHealth(targetPlayerData.getHealthMax());
                    sender.sendMessage("You have been healed!");
                }

                default -> {
                    sender.sendMessage("Unknown Argument.");
                }
            }
        }else{
            sender.sendMessage("Refer to tabComplete for commands.");
        }
        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> results = new ArrayList<>();
        if(args.length == 1){
            Util.copyPartialContains(args[0], Arrays.asList("setMaxHealth", "setDefense", "setMaxMana", "heal", "setAttack", "setStrength", "setDexterity"), results);
        }
        if(args.length == 2){

            Util.copyPartialContains(args[1], Util.getOnlinePlayerNames(), results);
        }
        return results;
    }
}

