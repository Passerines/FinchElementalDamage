package net.passerines.finch.items;

public abstract class FinchArmor extends FinchEquipment {


    public FinchArmor(String id, int rarity) {
        super(id, rarity);
    }
    public FinchArmor(String id) {
        this(id, 0);
    }
}
