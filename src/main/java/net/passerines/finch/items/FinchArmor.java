package net.passerines.finch.items;

public abstract class FinchArmor extends FinchEquipment {
    protected String armorSetName;
    public FinchArmor(String id, int rarity) {
        super(id, rarity);
        armorSetName = " ";
    }
    public FinchArmor(String id) {
        this(id, 1);
    }
    public String getArmorSetName(){
        return armorSetName;
    }
}
