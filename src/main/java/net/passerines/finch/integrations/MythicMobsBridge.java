package net.passerines.finch.integrations;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import net.passerines.finch.FinchElementalDamage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MythicMobsBridge implements Listener {

    public MythicMobsBridge() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event)	{
        String name = event.getMechanicName();
        if(name.equalsIgnoreCase("ELEMENTALDAMAGE")) {
            event.register(new MythicElementalDamage(event.getConfig()));
        }
    }
}
