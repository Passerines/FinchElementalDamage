package net.passerines.finch.players;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class PlayerMap implements Listener {
    public static final HashMap<Player, PlayerData> PLAYERS = new HashMap<>();


    public PlayerMap(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PLAYERS.put(event.getPlayer(), new PlayerData(event.getPlayer()));
        Util.log("&7Player Joined: " + event.getPlayer().getName());
        event.getPlayer().sendMessage("You Have: " + PLAYERS.get(event.getPlayer()).getMana() + " Mana" );
        event.getPlayer().sendMessage("You Have: " + PLAYERS.get(event.getPlayer()).getHealth() + " Health" );
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        PLAYERS.get(event.getPlayer()).dispose();
        PLAYERS.remove(event.getPlayer());
        Util.log("&7Player Left: " + event.getPlayer().getName());
    }

}

