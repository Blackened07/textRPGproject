package main.java.Characters;

import main.java.Items.Armor.Armors;
import main.java.Items.Weapon.Weapon;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private Set<Armors> inventoryA;
    private Set<Weapon> inventoryW;

    public Inventory() {
        this.inventoryA = new HashSet<>();
        this.inventoryW = new HashSet<>();
    }
    public static Inventory createInventory () {
        return new Inventory();
    }

    public void addToInventoryA(Armors armor) {
        inventoryA.add(armor);
    }
    public void addToInventoryW(Weapon weapon) {
        inventoryW.add(weapon);
    }
    public float checkWeaponAP() {
        for (Weapon w : inventoryW)
            return w.getAttackPower() ;
        return 0;
    }

    public float getWeaponFeatures(int index){
        float[] features = new float[4];
        for (Weapon w : inventoryW) {
            features = w.getAllStats();
        }
        return features[index];
    }
    public float getArmorFeatures(int indexSlot, int indexStat){
        float[][] stats = new float[7][4];
        for (Armors a : inventoryA) {
            for (int i = 0; i < 7; i++) {
                switch (a.getSlotType()) {
                    case HEAD -> stats[0] = a.getAllStats();
                    case BACK -> stats[1] = a.getAllStats();
                    case CHEST -> stats[2] = a.getAllStats();
                    case SHOULDERS -> stats[3] = a.getAllStats();
                    case HANDS -> stats[4] = a.getAllStats();
                    case LEGS -> stats[5] = a.getAllStats();
                    case FEET -> stats[6] = a.getAllStats();
                }
            }
        }
        return stats[indexSlot][indexStat];
    }
}
