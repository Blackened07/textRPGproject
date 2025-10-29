package main.java.Characters;

import main.java.Items.Item;
import main.java.Items.Types;

import java.util.*;

public class Equipment implements StatsCalculator{
    private Set<Item> equipment;

    public Equipment() {
        this.equipment = new HashSet<>();
    }

    public void addToInventory(Item item) {
        equipment.add(item);
    }

    public int sumOfWeightOfItemsEquipped() {
        return equipment.stream().
                mapToInt(Item::getWeight).
                sum();
    }
    public void removeFromEquipment (Item item) {
        equipment.remove(item);
    }



    /// ЭТО ИДИОТИЗМ? ОСТАВЛЮ ДЛЯ ИСТОРИИ

 /*   public float checkWeaponAP() {
        for (Item i : equipment) {
            if (Objects.requireNonNull(i.getType()) == Types.WEAPON) {
                Weapon weapon = (Weapon) i;
                return weapon.getAttackPower();
            }
        }
        return 0;
    }*/

    public float getWeaponFeatures(int index) {
        float[] features = new float[SUM_OF_ALL_STATS];
        for (Item w : equipment) {
            if (Objects.requireNonNull(w.getType()) == Types.WEAPON) {
                features = w.getAllStats();
            }
        }
        return features[index];
    }
    public float getArmorFeatures(int indexSlot, int indexStat){
        float[][] stats = new float[SUM_OF_ALL_ITEMS][SUM_OF_ALL_STATS];
        for (Item a : equipment) {
            for (int i = 0; i < 7; i++) {
                switch (a.getSlotType()) {
                    case HEAD -> stats[HEAD_INDEX] = a.getAllStats();
                    case BACK -> stats[BACK_INDEX] = a.getAllStats();
                    case CHEST -> stats[CHEST_INDEX] = a.getAllStats();
                    case SHOULDERS -> stats[SHOULDERS_INDEX] = a.getAllStats();
                    case HANDS -> stats[HANDS_INDEX] = a.getAllStats();
                    case LEGS -> stats[LEGS_INDEX] = a.getAllStats();
                    case FEET -> stats[FEET_INDEX] = a.getAllStats();
                    case WEAPON -> stats[WEAPON_INDEX] = a.getAllStats();
                }
            }
        }
        return stats[indexSlot][indexStat];
    }

}
