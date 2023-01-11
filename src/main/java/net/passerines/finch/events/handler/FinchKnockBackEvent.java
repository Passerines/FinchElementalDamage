package net.passerines.finch.events.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import static io.papermc.paper.event.server.ServerResourcesReloadedEvent.HANDLER_LIST;

public class FinchKnockBackEvent extends Event {
    private Entity victim;
    private Entity attacker;
    private int velocity;
    private boolean setUpVelocity;
    private int upVelocity;
    public FinchKnockBackEvent(Entity victim, Entity attacker, int velocity, int upVelocity){
        this.victim = victim;
        this.attacker = attacker;
        this.velocity = velocity;
        this.upVelocity = upVelocity;
        setUpVelocity = true;
    }
    public FinchKnockBackEvent(Entity victim, Entity attacker, int velocity){
        this(victim, attacker, velocity, 0);
        setUpVelocity = false;
    }
    public void apply(){
        Bukkit.getPluginManager().callEvent(this);
        Vector knockbackVelocity;
        if(setUpVelocity) {
            knockbackVelocity = victim.getLocation().subtract(attacker.getLocation()).toVector().normalize().multiply(velocity);
        }
        else{
            knockbackVelocity = victim.getLocation().subtract(attacker.getLocation()).toVector().normalize().multiply(velocity).setY(upVelocity);
        }
        victim.setVelocity(knockbackVelocity);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
