package main.java.Items.EquipableItem.Weapon;

import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
import main.java.Items.SlotType;
import main.java.Items.Types;

public class Weapon extends EquipableItem{
    private float attackPower;
    private WeaponTypes weaponTypes;

    public Weapon(String name, int cost, int weight, Types type, float strength, float stamina, float agility, float intellect, SlotType slotType, float attackPower, WeaponTypes weaponTypes) {
        super(name, cost, weight, type, strength, stamina, agility, intellect, slotType);
        this.attackPower = attackPower;
        this.weaponTypes = weaponTypes;
    }

    @Override
    public String getFeatures() {
        return "Сила атаки: " + getAttackPower() + "; " +  super.getFeatures();
    }

    public WeaponTypes getWeaponTypes() {return weaponTypes;}
    @Override public float getAttackPower() {
        return attackPower;
    }
    @Override public float[] getAllStats() {return super.getAllStats();}

}
