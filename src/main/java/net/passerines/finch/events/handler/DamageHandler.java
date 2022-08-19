package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageHandler implements Listener {

    public DamageHandler(){
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        //Converts entity damage by entity to elemental damage
        ElementalDamageEvent elementalDamage = new ElementalDamageEvent(event.getDamager(), event.getEntity(), ElementalDamageEvent.Element.FIRE, (int) event.getDamage());
        elementalDamage.apply();
        event.setCancelled(true);
    }
    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        //Handle elemental damage here
    }

    /*@EventHandler
    public void takeDamage(EntityDamageByEntityEvent hit){
        Entity victim = hit.getEntity();
        Entity attacker = hit.getDamager();
        if(victim instanceof Player){
            PlayerData vPlayerData = PlayerMap.PLAYERS.get(((Player) hit.getEntity()));
            int finalDamage = (int) hit.getDamage();
            vPlayerData.setHealth(vPlayerData.getHealth()  - (finalDamage - finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500))));
            int damageTaken = finalDamage - finalDamage * (vPlayerData.getDefense()/ (vPlayerData.getDefense() + 500));
            attacker.sendMessage("Damage Dealt: " + damageTaken);
            victim.sendMessage("Damage Taken: " + damageTaken);
        }
    }*/
}
