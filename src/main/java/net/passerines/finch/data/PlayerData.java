package net.passerines.finch.data;

public class PlayerData {
    private int health;
    private int healthMax;
    private int defense;
    private int mana;
    private int manaMax;
    private int damage;
    private double fireProf;
    private double waterProf;
    private double earthProf;
    private double windProf;
    private double electroProf;
    private double lightProf;
    private double darknessProf;

    public PlayerData(){
        setHealthMax(100);
        setHealth(100);
        setDefense(10);
        setManaMax(100);
        setMana(100);
        setDamage(15);
        setFireProf(0);
        setWaterProf(0);
        setEarthProf(0);
        setWindProf(0);
        setElectroProf(0);
        setLightProf(0);
        setDarknessProf(0);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getFireProf() {
        return fireProf;
    }

    public void setFireProf(double fireProf) {
        this.fireProf = fireProf;
    }

    public double getWaterProf() {
        return waterProf;
    }

    public void setWaterProf(double waterProf) {
        this.waterProf = waterProf;
    }

    public double getEarthProf() {
        return earthProf;
    }

    public void setEarthProf(double earthProf) {
        this.earthProf = earthProf;
    }

    public double getWindProf() {
        return windProf;
    }

    public void setWindProf(double windProf) {
        this.windProf = windProf;
    }

    public double getElectroProf() {
        return electroProf;
    }

    public void setElectroProf(double electroProf) {
        this.electroProf = electroProf;
    }

    public double getLightProf() {
        return lightProf;
    }

    public void setLightProf(double lightProf) {
        this.lightProf = lightProf;
    }

    public double getDarknessProf() {
        return darknessProf;
    }

    public void setDarknessProf(double darknessProf) {
        this.darknessProf = darknessProf;
    }
}
