package net.passerines.finch.integrations;

import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicDropLoadEvent;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class MythicMobsBridge implements Listener {
    Plugin plugin = FinchElementalDamage.inst();

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
    @EventHandler
    public void onMythicDropLoad(MythicDropLoadEvent event){
        if(event.getDropName() != null){
            if(ItemManager.ITEM_HASH_MAP.containsKey(event.getDropName())){
                event.register(new MythicDrop(event.getDropName()));
            }
        }
    }

    public static ActiveMob getActiveMob(Entity entity) {
        return MythicBukkit.inst().getMobManager().getActiveMob(entity.getUniqueId()).orElse(null);
    }

}
