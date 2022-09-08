package net.passerines.finch.entity;

import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.items.ItemManager;
import net.passerines.finch.util.Util;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EntityData {

    private Entity entity;
    private double health;
    private int defense;
    private int damage;

    public EntityData(Entity entity){
        this.entity = entity;
        LivingEntity livingEntity = (LivingEntity) entity;
        health = Util.getMaxHealth(livingEntity) * 3;
        defense = 5;
        if(livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
            damage = (int) (2 * livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
        }
        else{
            damage = 1;
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
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
