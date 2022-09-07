package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static net.passerines.finch.players.PlayerMap.PLAYERS;


public class GetEffectiveHealthCmd implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public GetEffectiveHealthCmd() {
        plugin.getCommand("getehp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((sender));
        int effectiveHealth = Util.getEffectiveHealth((int) vPlayerData.getHealth(), vPlayerData.getDefense());
        for(Player player : PLAYERS.keySet()){
            sender.sendMessage("Effective Health: " + effectiveHealth);
        }
        return false;
    }
}

