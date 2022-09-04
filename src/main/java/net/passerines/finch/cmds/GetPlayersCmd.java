package net.passerines.finch.cmds;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static net.passerines.finch.players.PlayerMap.PLAYERS;
import static net.passerines.finch.entity.EntityMap.ENTITIES;


public class GetPlayersCmd implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public GetPlayersCmd() {
        plugin.getCommand("getplayers").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(Player player : PLAYERS.keySet()){
            //sender.sendMessage(PLAYERS.get(player) + "HP: " + PLAYERS.get(player).getHealth());
            sender.sendMessage(player.getName() + " Health: " + PLAYERS.get(player).getHealth());
        }
        for(Entity entity : ENTITIES.keySet()){
            sender.sendMessage("Entity Health: " + ENTITIES.get(entity).getHealth());
        }
        return false;
    }
}

