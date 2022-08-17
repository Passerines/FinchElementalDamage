package net.passerines.finch.events;

import com.comphenix.protocol.PacketType;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class DamageHandler {
    public static final HashMap<Player, PlayerData> DAMAGE = new HashMap<>();

    public DamageHandler(){
        FinchElementalDamage.inst();
    }
    @EventHandler
    public void takeDamage(EntityDamageByEntityEvent hit){
        Entity vEntity = hit.getEntity();
        if(vEntity instanceof Player){
            PlayerData vPlayerData = PlayerMap.PLAYERS.get(((Player) hit.getEntity()));
            int finalDamage = (int) hit.getDamage();
            vPlayerData.setHealth(vPlayerData.getHealth()  - (finalDamage - finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500))));
        }
    }
}
