package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static net.passerines.finch.players.PlayerMap.PLAYERS;


public class GetPlayersCmd implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public GetPlayersCmd() {
        plugin.getCommand("getplayers").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerData player = PLAYERS.get(sender);
        sender.sendMessage("Health: " + player.getHealth());
        sender.sendMessage("Defense: " + player.getDefense());
        sender.sendMessage("Damage (Melee): " + player.getDamage());
        sender.sendMessage("Damage (Ranged): " + player.getBowDamage());
        return false;
    }
}

