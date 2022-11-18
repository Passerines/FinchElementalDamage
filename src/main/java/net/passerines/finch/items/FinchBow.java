package net.passerines.finch.items;

public abstract class FinchBow extends FinchWeapon {

    public FinchBow(String id){
        this(id, 0);
    }
    public FinchBow(String id, int rarity) {
        super(id, rarity);
    }
}