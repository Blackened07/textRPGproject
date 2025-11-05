package main.java.Characters;

import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;

import java.util.*;

public class Equipment implements StatsCalculator{
    final float attackPowerANDSpellPowerANDAttackSpeedCoefficient = 1.6f;
    final float evasionCoefficient = 2f;
    final float spellResistanceCoefficient = 16f;
    final float attackSpeedCoefficient = 10f;

    private Set<EquipableItem> equipment;

    public Equipment() {
        this.equipment = new HashSet<>();
    }

    public void addToEquipment(EquipableItem item, Organism player) {
        equipment.add(item);
        calculatingEquipped(item, player);
    }

    public void calculatingEquipped(EquipableItem item, Organism player) {
        player.setFullStrength(calculateEquipmentStrength(item));
        player.setFullStamina(calculateEquipmentStamina(item));
        player.setFullAgility(calculateEquipmentAgility(item));
        player.setFullIntellect(calculateEquipmentIntellect(item));

        player.setAttackPower(calculateAttackPower(player.getFullStrength()));
        player.setSpellPower(calculateSpellPower(player.getFullIntellect()));
        player.setEvasion(calculateEvasion(player.getFullAgility()));
        player.setSpellResistance(calculateSpellResistance(player.getFullIntellect(), player.getFullStamina()));
        player.setAttackSpeed(calculateAttackSpeed(player.getFullAgility(), player.getFullStamina()));

        player.setHealthMaxValue();
        player.setManaMaxValue();

        player.setCurrentHealth(player.getHealthMaxValue());
        player.setCurrentMana(player.getManaMaxValue());
    }
    public float calculateAttackPower(float fullStrength) {return fullStrength / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float calculateSpellPower(float fullIntellect) {return fullIntellect / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float calculateEvasion(float fullAgility) {return fullAgility / evasionCoefficient;}
    public float calculateSpellResistance(float fullIntellect, float fullStamina) {return (fullIntellect + fullStamina) / spellResistanceCoefficient;}
    public float calculateAttackSpeed(float fullAgility, float fullStamina) {return (fullAgility + fullStamina/attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    public int sumOfWeightOfEquippedItems() {
        return equipment.stream().
                mapToInt(Item::getWeight).
                sum();
    }
    public StringBuilder showAllEquippedItems() {
        StringBuilder sb = new StringBuilder();
        equipment.forEach(equipableItem -> sb.
                append(" - ").append(equipableItem.getName()).
                append(" - ").append(equipableItem.getFeatures()).append("\n"));
        return sb;
    }
    public void iterateAllEquippedItems(Organism player) {
        for (EquipableItem e : equipment) {
            calculatingEquipped(e, player);
        }
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
    }
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
    }*/
}
