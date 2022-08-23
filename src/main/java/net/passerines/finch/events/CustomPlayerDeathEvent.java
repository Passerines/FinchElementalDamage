package net.passerines.finch.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomPlayerDeathEvent extends Event {
    private Player deadVictim;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public CustomPlayerDeathEvent(Player deadVictim){
        this.deadVictim = deadVictim;
    }

    public void apply() {
        Bukkit.getPluginManager().callEvent(this);
    }

    public Player getDeadVictim() {
        return deadVictim;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
