package main.java.Items.EquipableItem;

import main.java.Items.EquipableItem.Armor.Features;
import main.java.Items.Item;
import main.java.Items.SlotType;
import main.java.Items.Types;

public class EquipableItem extends Item implements Features {
    private final float strength;
    private final float stamina;
    private final float agility;
    private final float intellect;
    SlotType slotType;

    public EquipableItem(String name, int cost, int weight, Types type, float strength, float stamina, float agility, float intellect, SlotType slotType) {
        super(name, cost, weight, type);
        this.strength = strength;
        this.stamina = stamina;
        this.agility = agility;
        this.intellect = intellect;
        this.slotType = slotType;
    }

    public String getFeatures() {return "Сила: " + getStrength() + "; Выносливость: " + getStamina() + "; Ловкость: " + getAgility() + "; Интеллект: " + getIntellect();}
    public float[] getAllStats() {return new float[]{getStrength(), getStamina(), getAgility(), getIntellect()};}
    public float getAttackPower(){return 0;};
    public float getStrength() {return strength;}
    public float getStamina() {return stamina;}
    public float getAgility() {return agility;}
    public float getIntellect() {return intellect;}
    public SlotType getSlotType() {return slotType;}
}
