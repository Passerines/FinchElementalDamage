package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import static net.passerines.finch.players.PlayerMap.PLAYERS;

import static net.passerines.finch.util.Util.log;
import static net.passerines.finch.util.Util.matchPlayer;


public class FinchAttributesCmd implements CommandExecutor {



    public FinchAttributesCmd(){
        FinchElementalDamage.inst().getCommand("finchattributes").setExecutor(this);
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

                case "setMaxDefense" -> {
                    targetPlayerData.setDefense(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " defense to : " + args[1]);
                }

                case "setMaxMana" -> {
                    targetPlayerData.setManaMax(Integer.parseInt(args[2]));
                    sender.sendMessage("set" + targetPlayer + " mana to : " + args[1]);
                }

                case "heal" -> {
                    targetPlayerData.setHealth(targetPlayerData.getHealthMax());
                    sender.sendMessage("You have been healed!");
                }

                default -> {
                    sender.sendMessage("Unknown Argument. For help, type /finchattributes");
                }
            }
        }else{
            sender.sendMessage("Commands: setMaxHealth (player) (int), setMaxDefense (player) (int), setMaxMana (player) (int), heal (player) (player)");
        }
        return true;
    }















}

