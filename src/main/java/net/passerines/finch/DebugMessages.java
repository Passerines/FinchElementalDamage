package net.passerines.finch;

import com.ticxo.modelengine.api.ModelEngineAPI;
import io.lumine.mythic.api.MythicPlugin;
import io.lumine.mythic.api.mobs.MythicMob;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DebugMessages implements Listener{
    public DebugMessages(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void onHit(ElementalDamageEvent event){
        if(event.getAttacker() instanceof Player player && event.getVictim() instanceof LivingEntity victim){
            if(ModelEngineAPI.isModeledEntity(victim.getUniqueId())) {
                player.sendMessage(Chat.formatC("You hit: A model engine mob " + victim.getName() + " " + victim.getUniqueId()) + " " + victim.getType());
            }
        }

    }
}
