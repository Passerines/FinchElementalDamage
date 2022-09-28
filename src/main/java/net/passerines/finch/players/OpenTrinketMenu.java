package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenTrinketMenu implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public OpenTrinketMenu() {
        plugin.getCommand("TrinketMenu").setExecutor(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            TrinketMenu trinketMenu = new TrinketMenu();
            trinketMenu.open(player);
            player.sendMessage(Chat.formatC("Sent"));
        }
        return false;
    }
}
