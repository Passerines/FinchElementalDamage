package net.passerines.finch.items;

import net.passerines.finch.events.ElementalDamageEvent;

public abstract class FinchWeapon extends FinchEquipment {

    protected ElementalDamageEvent.Element element;

    public FinchWeapon(String id) {
        this(id, 1);
    }
    public FinchWeapon(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.FIRE;
    }
    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}