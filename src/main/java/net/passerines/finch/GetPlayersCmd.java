package net.passerines.finch;

import com.comphenix.protocol.utility.Util;
import net.kyori.adventure.text.ComponentLike;
import net.passerines.finch.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GetPlayersCmd implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.getPlugin(FinchElementalDamage.class);

    public GetPlayersCmd() {
        plugin.getCommand("FinchElementalDamage").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage((ComponentLike) PlayerMap.PLAYERS.get(Util.getOnlinePlayers()));
        }
        return false;
    }
}
