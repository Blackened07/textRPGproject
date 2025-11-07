package main.java.Characters;

import main.java.GameProcesses.Services.RandomNumberGenerator;
import main.java.Items.EquipableItem.EquipableItem;

import java.util.ArrayList;
import java.util.List;


public interface StatsCalculator extends RandomNumberGenerator {
    int STATS_EXP = 6;
    int BASE_EXP = 45;
    int BASE_STAT = 5;
    int EXP_INDEX = 0;
    int STR_INDEX = 1;
    int STAM_INDEX = 2;
    int AGI_INDEX = 3;
    int INT_INDEX = 4;
    int LEVEL_INDEX = 5;
    int MAIN_STATS_COEFFICIENT = 4;
    int SECONDARY_STATS_COEFFICIENT = 2;
    float WEIGHT_COEFFICIENT = 2.9f;

    default List<Integer> calculateStatsForNewOrganism (GameClass gameClass, int ... level) {
        int lvl = chooseLvl(level);

        List<Integer> stats = new ArrayList<>(STATS_EXP);
        switch (gameClass) {
            case AI_WARRIOR -> {
                return calc(STR_INDEX, STAM_INDEX, AGI_INDEX, lvl, stats);
            }
            case AI_CASTER -> {
                return calc(INT_INDEX, STAM_INDEX, AGI_INDEX, lvl, stats);
            }
            case AI_ROGUE -> {
                return calc(AGI_INDEX, INT_INDEX, STAM_INDEX, lvl, stats);
            }
        }
        return null;
    }

    default List<Integer> calc(int main1, int main2, int sec1, int lvl, List<Integer> stats) {
        int riseStat = 2;
        int riseExp = 4;
        int lvlAndStatsCorrelation = (lvl - 1) * riseStat;
        for (int i = 0; i < STATS_EXP; i++) {
            if (i == EXP_INDEX) stats.add(BASE_EXP + (lvl - 1) * riseExp);
            else if (i == LEVEL_INDEX) stats.add(lvl);
            else if (i == main1 || i == main2) stats.add(BASE_STAT * MAIN_STATS_COEFFICIENT + lvlAndStatsCorrelation);
            else if (i == sec1) stats.add(BASE_STAT * SECONDARY_STATS_COEFFICIENT + lvlAndStatsCorrelation);
            else stats.add(BASE_STAT + lvlAndStatsCorrelation);
        }
        return stats;
    }

    default float calculateEquipmentStrength (EquipableItem item) {return item.getStrength();}
    default float calculateEquipmentStamina (EquipableItem item) {return item.getStamina();}
    default float calculateEquipmentAgility (EquipableItem item) {return item.getAgility();}
    default float calculateEquipmentIntellect (EquipableItem item) {return item.getIntellect();}
    default boolean getWeightByStrength (float strength, int weight) {return !(strength  < weight);}
    default float requiredStrength (int weight) {return weight / WEIGHT_COEFFICIENT;}
}
