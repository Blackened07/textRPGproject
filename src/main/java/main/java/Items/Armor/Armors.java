package main.java.Items.Armor;

import main.java.Items.Item;
import main.java.Items.Types;

public class Armors extends Item implements Features{
    private float strength;
    private float stamina;
    private float agility;
    private float intellect;
    ArmorType armorType;
    SlotType slotType;

    public Armors(String name, int cost, int weight, Types type, float strength, float stamina, float agility, float intellect, ArmorType armorType, SlotType slotType) {
        super(name, cost, weight, type);
        this.strength = strength;
        this.stamina = stamina;
        this.agility = agility;
        this.intellect = intellect;
        this.armorType = armorType;
        this.slotType = slotType;
    }

    @Override
    public String getFeatures() {
        return String.valueOf(getStrength() + " " + getStamina() + " " + getAgility() + " " + getIntellect());
    }
    public float[] getAllStats() {
        return new float[]{getStrength(), getStamina(), getAgility(), getIntellect()};
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Types getType() {
        return super.getType();
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

    public ArmorType getArmorType() {
        return armorType;
    }
    @Override
    public SlotType getSlotType() {
        return slotType;
    }
}
