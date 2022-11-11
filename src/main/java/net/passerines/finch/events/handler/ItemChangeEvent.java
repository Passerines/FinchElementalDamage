package net.passerines.finch.events.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemChangeEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player player;
    private ItemStack itemStack;

    public ItemChangeEvent(Player player, ItemStack itemStack){
        this.player = player;
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    public Player getPlayer() {
        return player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
