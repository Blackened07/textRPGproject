package main.java.Items.Weapon;

import main.java.Items.Item;
import main.java.Items.Types;

public class Weapon extends Item implements Attack {
    private float attackPower;
    private WeaponTypes weaponTypes;
    private float strength;
    private float stamina;
    private float agility;
    private float intellect;

    public Weapon(String name, int cost, int weight, Types type, float strength, float stamina, float agility, float intellect, float attackPower, WeaponTypes weaponTypes) {
        super(name, cost, weight, type);
        this.strength = strength;
        this.stamina = stamina;
        this.agility = agility;
        this.intellect = intellect;
        this.attackPower = attackPower;
        this.weaponTypes = weaponTypes;
    }

    @Override public float getAttackPower() {
        return attackPower;
    }
    public float getStrength() {
        return strength;
    }
    public float getStamina() {
        return stamina;
    }
    public float getAgility() {
        return agility;
    }
    public float getIntellect() {
        return intellect;
    }
    @Override public float[] getAllStats() {return new float[]{getStrength(), getStamina(), getAgility(), getIntellect()};}

    @Override
    public float attack() {
        return attackPower;
    }
}
