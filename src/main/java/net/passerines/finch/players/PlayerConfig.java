package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {

    private Player player;
    private File file;
    private YamlConfiguration config;

    //A simple system for saving player data
    public PlayerConfig(Player player) {
        this.player = player;
        initialize();
        load();
    }
    public void initialize() {
        file = new File(FinchElementalDamage.inst().getDataFolder().getPath() + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            Util.log("&4WARNING: &cUnable to create new save file for player &4" + player.getName());
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    private void load() {
        config.set("User.Username", player.getName());
        config.set("User.UUID", player.getUniqueId());
    }
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            Util.log("&4WARNING: &cUnable to save the save file for &4" + player.getName());
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }

}
