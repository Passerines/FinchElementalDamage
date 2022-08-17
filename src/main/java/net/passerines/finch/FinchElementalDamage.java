package net.passerines.finch;

import net.passerines.finch.events.DamageHandler;
import net.passerines.finch.events.PlayerMap;
import net.passerines.finch.util.Util;
import org.bukkit.plugin.java.JavaPlugin;

public class FinchElementalDamage extends JavaPlugin {

    @Override
    public void onLoad() {
        Util.log("&bFinchElementalDamage is now loading!");
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        new PlayerMap();
        new GetPlayersCmd();
        new DamageHandler();
    }

    public static FinchElementalDamage inst() {
        return FinchElementalDamage.getPlugin(FinchElementalDamage.class);
    }
}
