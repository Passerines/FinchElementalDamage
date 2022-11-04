package net.passerines.finch.items;

import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchItem;

public abstract class FinchBow extends FinchWeapon {
    protected int bowDamage;
    public FinchBow(String id){
        super(id);
        this.bowDamage = 0;
    }
    public int getBowDamage() {
        return bowDamage;
    }

    public void setBowDamage(int bowDamage) {
        this.bowDamage = bowDamage;
    }
}