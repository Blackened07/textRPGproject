package main.java.Characters;

import main.java.Items.EquipableItem.EquipableItem;


public interface StatsCalculator {

    float WEIGHT_COEFFICIENT = 2.9f;

    default float calculateEquipmentStrength (EquipableItem item) {return item.getStrength();}
    default float calculateEquipmentStamina (EquipableItem item) {return item.getStamina();}
    default float calculateEquipmentAgility (EquipableItem item) {return item.getAgility();}
    default float calculateEquipmentIntellect (EquipableItem item) {return item.getIntellect();}
    default boolean getWeightByStrength (float strength, int weight) {return !(strength  < weight);}
    default float requiredStrength (int weight) {return weight / WEIGHT_COEFFICIENT;}
}
