package net.passerines.finch;

import com.comphenix.protocol.utility.Util;
import net.passerines.finch.data.PlayerData;
import net.passerines.finch.events.PlayerMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static net.passerines.finch.events.PlayerMap.PLAYERS;


public class GetPlayersCmd implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public GetPlayersCmd() {
        plugin.getCommand("getplayers").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
            for(player : PLAYERS.keySet()){
                player.sendMessage(PLAYERS.get(player) + "HP: " + PLAYERS.get(player).getHealth());
            }
        return false;
    }
}

