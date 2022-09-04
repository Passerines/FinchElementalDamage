package net.passerines.finch.entities;

import org.bukkit.entity.Entity;

public class EntityData {

    private Entity entity;
    private double health;
    private double healthMax;
    private int defense;
    private int damage;

    public EntityData(Entity entity){
        this.entity = entity;
        health = 100;
        defense = 10;
    }

    public void reset() {
        setHealthMax(100);
        setDefense(10);
        setDamage(3);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(double healthMax) {
        this.healthMax = healthMax;
        this.health = Math.min(healthMax, health);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
