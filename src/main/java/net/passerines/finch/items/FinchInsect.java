package net.passerines.finch.items;

import net.passerines.finch.events.ElementalDamageEvent;
import org.bukkit.Location;

public abstract class FinchInsect extends FinchEquipment {

    protected ElementalDamageEvent.Element element;

    public FinchInsect(String id) {
        this(id, 1);
    }
    public FinchInsect(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.NEUTRAL;
    }
    public abstract void onContact(Location loc);

    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}