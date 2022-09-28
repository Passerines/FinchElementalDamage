package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerConfig {

    private final Player player;
    private final PlayerData playerData;
    private File file;
    private YamlConfiguration config;

    //A simple system for saving player data
    public PlayerConfig(Player player) {
        this.player = player;
        playerData = PlayerMap.PLAYERS.get(player);
        initialize();
        load();
    }
    public void initialize() {
        File folder = new File(FinchElementalDamage.inst().getDataFolder().getPath() + File.separator + "players");
        if (!folder.exists() || !folder.isDirectory()) folder.mkdirs();
        file = new File(FinchElementalDamage.inst().getDataFolder().getPath() + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            Util.log("&4WARNING: &cUnable to create new save file for player &4" + player.getName());
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    private void load() {
        Util.log("&bLoading data for &7" + player.getName());
        config.set("User.Username", player.getName());
        config.set("User.UUID", player.getUniqueId().toString());
        config.set("User.LastLogin", "CURRENTLY ONLINE");
    }
    public void save() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date date = new Date(System.currentTimeMillis());
        config.set("User.LastLogin", simpleDateFormat.format(date));
        config.set("Player.Health", playerData.getHealth());
        config.set("Player.Mana", playerData.getMana());
        Util.log("&bSaving data for &7" + player.getName());
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
