package main.java.Items.EquipableItem.Armor;

import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
import main.java.Items.SlotType;
import main.java.Items.Types;

public class Armors extends EquipableItem implements Features{

    ArmorType armorType;

    public Armors(String name, int cost, int weight, Types type, float strength, float stamina, float agility, float intellect, SlotType slotType, ArmorType armorType) {
        super(name, cost, weight, type, strength, stamina, agility, intellect, slotType);
        this.armorType = armorType;
    }

    @Override public String getFeatures() {return super.getFeatures();}
    @Override public float[] getAllStats() {return super.getAllStats();}
    @Override public String getName() {
        return super.getName();
    }
    @Override public Types getType() {
        return super.getType();
    }
    public ArmorType getArmorType() {
        return armorType;
    }

}
