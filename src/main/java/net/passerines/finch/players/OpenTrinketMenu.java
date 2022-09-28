package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenTrinketMenu implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public OpenTrinketMenu() {
        plugin.getCommand("TrinketsMenu").setExecutor(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            TrinketMenu trinketMenu = new TrinketMenu();
            Player player = (Player) sender;
            trinketMenu.open(player);
        }
        return false;
    }
}
