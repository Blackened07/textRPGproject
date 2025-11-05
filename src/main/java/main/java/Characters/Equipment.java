package main.java.Characters;

import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Equipment implements StatsCalculator{


    private Set<EquipableItem> equipment;

    public Equipment() {
        this.equipment = new HashSet<>();
    }

    public void addToEquipment(EquipableItem item, Organism player) {
        equipment.add(item);
        calculatingWhenAddItem(item, player);
    }

    public void calculatingWhenAddItem(EquipableItem item, Organism player) {
        player.setFullStrength(calculateEquipmentStrength(item));
        player.setFullStamina(calculateEquipmentStamina(item));
        player.setFullAgility(calculateEquipmentAgility(item));
        player.setFullIntellect(calculateEquipmentIntellect(item));
    }

    public int sumOfWeightOfEquippedItems() {
        return equipment.stream().
                mapToInt(Item::getWeight).
                sum();
    }
    public void removeFromEquipment (EquipableItem item) {
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

    public float getArmorFeatures(int indexSlot, int indexStat){
        float[][] stats = new float[SUM_OF_ALL_ITEMS][SUM_OF_ALL_STATS];
        for (EquipableItem a : equipment) {
            for (int i = 0; i < SUM_OF_ALL_ITEMS; i++) {
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

    public StringBuilder showAllEquippedItems() {
        StringBuilder sb = new StringBuilder();
        equipment.forEach(equipableItem -> sb.
                append(" - ").append(equipableItem.getName()).
                append(" - ").append(equipableItem.getFeatures()).append("\n"));
        return sb;
    }

}
