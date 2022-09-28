package net.passerines.finch.events.handler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.players.PlayerMap;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;

public class EquipmentChangeHandler implements Listener {

    public EquipmentChangeHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        PlayerMap.PLAYERS.get(event.getPlayer()).calculate();
    }
    @EventHandler
    public void onWeaponChange(PlayerChangedMainHandEvent event){PlayerMap.PLAYERS.get(event.getPlayer()).calculate();}
}
