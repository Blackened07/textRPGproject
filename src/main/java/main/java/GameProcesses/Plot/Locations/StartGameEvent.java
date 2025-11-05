package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.*;
import main.java.GameProcesses.Dialogue;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.Items.EquipableItem.Armor.ArmorType;
import main.java.Items.EquipableItem.Armor.Armors;
import main.java.Items.SlotType;
import main.java.Items.Types;
import main.java.Items.EquipableItem.Weapon.Weapon;
import main.java.Items.EquipableItem.Weapon.WeaponTypes;

import java.util.*;

public class StartGameEvent extends Events implements PrintableInterfaces {
    Weapon weapon;
    Organism player;
    Dialogue dialogue;
    private final String START_CHOOSE_Spec_1 = "ChooseSpec1";
    private final String START_CHOOSE_Spec_2 = "ChooseSpec2";
    private final String START_CHOOSE_Spec_3 = "ChooseSpec3";
    private final String GAME_MESSAGE = "GameMessage";

    public StartGameEvent(String eventName, Location LOCATION, Dialogue dialogue) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
    }

    @Override public Organism startGameEvent(Scanner sc) {
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        chooseWeapon(sc);
        try {
            Thread.sleep(2500);
            features(player);
            Thread.sleep(2500);
            printEventTextAndCommands(GAME_MESSAGE, this.dialogue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.player;
    }

    public void chooseWeapon (Scanner sc) {
        int weaponTyped = gameScanner(sc, dialogue.getInnerListSize(getSTART_EVENT()), player, this);
        switch (weaponTyped) {
            case 1 -> {
                printEventTextAndCommands(START_CHOOSE_Spec_1, this.dialogue);
                weapon = new Weapon("Axe of Strength", 10, 5, Types.WEAPON, 15, 4, 2, 0, SlotType.WEAPON, 7,   WeaponTypes.AXE );}
            case 2 -> {
                printEventTextAndCommands(START_CHOOSE_Spec_2, this.dialogue);;
                weapon = new Weapon("Wizard Stave", 12, 3, Types.WEAPON, 0, 5, 0, 25, SlotType.WEAPON, 3,WeaponTypes.STAFF );
            }
            case 3 -> {
                printEventTextAndCommands(START_CHOOSE_Spec_3, this.dialogue);
                weapon = new Weapon("Stinger", 9, 2, Types.WEAPON, 2, 3, 10, 10, SlotType.WEAPON, 5, WeaponTypes.DAGGER );
            }
        }
        chooseSpec(player, weapon, weaponTyped, sc);
    }

    public String enterName(Scanner sc) {
        System.out.println("Введите имя персонажа: ");
        return sc.nextLine();
    }
    public void chooseSpec (Organism player, Weapon weapon, int weaponTyped, Scanner sc) {
        int spec = gameScanner(sc, dialogue.getInnerListSize(START_CHOOSE_Spec_3), player, this);
        switch (weaponTyped) {
            case 1 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 13, 10,5,7,0,10 , GameClass.PALADIN,  weapon);
                    case 2 -> player = new Player(enterName(sc), 14, 11,5,5,0,10, GameClass.WARRIOR,  weapon );
                }
                armorForWarrior(player, weapon);
            }
            case 2 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 5, 8,5,17,0,10, GameClass.FIRE_MAGE,  weapon);
                    case 2 -> player = new Player(enterName(sc), 5, 7,5,18,0,10, GameClass.FROST_MAGE,  weapon);
                }
                armorForMage(player, weapon);
            }
            case 3 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 5, 10,15,5,0,10, GameClass.ROGUE,  weapon);
                    case 2 -> player = new Player(enterName(sc), 5, 8,8,14,0,10, GameClass.NECROMANT,  weapon);
                }
                armorForRogue(player, weapon);
            }
        }
    }

    public void armorForWarrior (Organism player, Weapon weapon) {
        player.addToEquipment(new Armors("Giant's Chest", 12, 10, Types.ARMOR, 2, 1, 0,0, SlotType.CHEST, ArmorType.PLATE));
        player.addToEquipment(new Armors("Giant's Hands", 12, 6, Types.ARMOR, 0, 0, 0,0, SlotType.HANDS, ArmorType.PLATE));
        player.addToEquipment(new Armors("Giant's Legs", 12, 9, Types.ARMOR, 1, 0, 0,0, SlotType.LEGS, ArmorType.PLATE));
        player.addToEquipment(new Armors("Giant's Boots", 12, 7, Types.ARMOR, 0, 0, 0,0, SlotType.FEET, ArmorType.PLATE ));
        player.addToBackPack(weapon);
        this.player = player;
    }
    public void armorForMage (Organism player, Weapon weapon) {
        player.addToEquipment(new Armors("Robe of Mage", 12, 3, Types.ARMOR, 0, 1, 0,2, SlotType.CHEST, ArmorType.CLOTH ));
        player.addToEquipment(new Armors("Hands of Mage", 12, 1, Types.ARMOR, 0, 0, 0,0, SlotType.HANDS, ArmorType.CLOTH ));
        player.addToEquipment(new Armors("Magic Pants", 12, 2, Types.ARMOR, 0, 0, 0,2, SlotType.LEGS, ArmorType.CLOTH));
        player.addToEquipment(new Armors("Magic Boots", 12, 1, Types.ARMOR, 0, 0, 0,0, SlotType.FEET, ArmorType.CLOTH));
        player.addToBackPack(weapon);
        this.player = player;
    }
    public void armorForRogue (Organism player, Weapon weapon) {
        player.addToEquipment(new Armors("Shadow Chest", 12, 5, Types.ARMOR, 0, 1, 2,0, SlotType.CHEST, ArmorType.LEATHER));
        player.addToEquipment(new Armors("Shadow Hands", 12, 2, Types.ARMOR, 0, 0, 0,0, SlotType.HANDS, ArmorType.LEATHER));
        player.addToEquipment(new Armors("Pants of Darkness", 12, 4, Types.ARMOR, 0, 0, 1,0, SlotType.LEGS, ArmorType.LEATHER));
        player.addToEquipment(new Armors("Spider Boots", 12, 3, Types.ARMOR, 0, 0, 0,0, SlotType.FEET, ArmorType.LEATHER));
        player.addToBackPack(weapon);
        this.player = player;
    }
}
