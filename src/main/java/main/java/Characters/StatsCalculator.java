package main.java.Characters;

public interface StatsCalculator {
    int STRENGTH_INDEX = 0;
    int STAMINA_INDEX = 1;
    int AGILITY_INDEX = 2;
    int INTELLECT_INDEX = 3;
    int SUM_OF_ALL_ITEMS = 8;
    int SUM_OF_ALL_STATS = 4;
    int HEAD_INDEX = 0;
    int BACK_INDEX = 1;
    int CHEST_INDEX = 2;
    int SHOULDERS_INDEX = 3;
    int HANDS_INDEX = 4;
    int LEGS_INDEX = 5;
    int FEET_INDEX = 6;
    int WEAPON_INDEX = 7;
    float WEIGHT_COEFFICIENT = 2.9f;

    default float getStat(Equipment equipment, int statIndex) {
        return equipment.getArmorFeatures(HEAD_INDEX, statIndex) + equipment.getArmorFeatures(BACK_INDEX, statIndex) + equipment.getArmorFeatures(CHEST_INDEX, statIndex)
                +  equipment.getArmorFeatures(SHOULDERS_INDEX, statIndex) + equipment.getArmorFeatures(HANDS_INDEX, statIndex) + equipment.getArmorFeatures(LEGS_INDEX, statIndex)
                + equipment.getArmorFeatures(FEET_INDEX, statIndex) + equipment.getArmorFeatures(WEAPON_INDEX, statIndex);
    }
    default boolean getWeightByStrength (float strength, int weight) {
        return !(strength  < weight);
    }
    default float requiredStrength (int weight) {
        return weight / WEIGHT_COEFFICIENT;
    }
}
