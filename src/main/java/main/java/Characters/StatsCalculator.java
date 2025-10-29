package main.java.Characters;

public interface StatsCalculator {
    int STRENGTH_INDEX = 0;
    int STAMINA_INDEX = 1;
    int AGILITY_INDEX = 2;
    int INTELLECT_INDEX = 3;
    float WEIGHT_COEFFICIENT = 2.9f;
    default float getStat(Inventory inventory, int statIndex) {
        return inventory.getWeaponFeatures(statIndex) + inventory.getArmorFeatures(0, statIndex) + inventory.getArmorFeatures(1, statIndex) + inventory.getArmorFeatures(2, statIndex)
                +  inventory.getArmorFeatures(3, statIndex) + inventory.getArmorFeatures(4, statIndex) + inventory.getArmorFeatures(5, statIndex)
                + inventory.getArmorFeatures(6, statIndex);
    }
    default boolean getWeightByStrength (float strength, int weight) {
        return !(strength  < weight);
    }
    default float requiredStrength (int weight) {
        return weight / WEIGHT_COEFFICIENT;
    }
}
