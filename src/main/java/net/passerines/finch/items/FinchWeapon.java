package net.passerines.finch.items;

import net.passerines.finch.events.ElementalDamageEvent;

public abstract class FinchWeapon extends FinchEquipment {

    protected int durability;
    protected ElementalDamageEvent.Element element;

    public FinchWeapon(String id) {
        this(id, 0);
    }
    public FinchWeapon(String id, int rarity) {
        super(id, rarity);
        this.durability = 0;
        this.element = ElementalDamageEvent.Element.FIRE;
    }
    public int getDurability(){return durability;}

    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}