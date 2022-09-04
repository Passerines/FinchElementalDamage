package net.passerines.finch.events;

import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ElementalDamageEvent extends Event implements Cancellable {

    private int damage;
    private Entity attacker;
    private Entity victim;
    private Element element;

    private boolean cancelled;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public enum Element {
        FIRE(Chat.format("&6Fire"), 1f),
        WATER(Chat.format("&bWater"), 1f),
        EARTH(Chat.format("&2Earth"), 1f),
        WIND(Chat.format("&fWind"), 1f),
        ELECTRO(Chat.format("&dElectro"), 1f),
        LIGHT(Chat.format("&eLight"), 1f),
        NEUTRAL(Chat.format("Neutral"), 1f),
        UNDEAD(Chat.format("&4Undead"), 1f),
        DARK(Chat.format("&8Dark"), 1f);
        private final String displayName;
        private final float elementalMultiplier;
        Element(String displayName, float elementalMultiplier) {
            this.displayName = displayName;
            this.elementalMultiplier = elementalMultiplier;
        }
        public String getDisplayName() {
            return displayName;
        }
        public float getElementalMultiplier() {
            return elementalMultiplier;
        }
    }

    public ElementalDamageEvent(Entity attacker, Entity victim, Element element, int damage) {
        this.attacker = attacker;
        this.victim = victim;
        this.element = element;
        this.damage = damage;
    }

    public void apply() {
        Bukkit.getPluginManager().callEvent(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Entity getAttacker() {
        return attacker;
    }

    public Entity getVictim() {
        return victim;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
