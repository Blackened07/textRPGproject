package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.*;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Armor.ArmorType;
import main.java.Items.Armor.Armors;
import main.java.Items.Armor.SlotType;
import main.java.Items.Types;
import main.java.Items.Weapon.Weapon;
import main.java.Items.Weapon.WeaponTypes;

import java.util.*;

public class StartGameEvent extends Events implements PrintableInterfaces {
    Weapon weapon;
    Organism player;
    Dialogue dialogue;
    private final String START = "StartEvent";

    public StartGameEvent(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue) {
        super(eventName, LOCATION, story);
        this.dialogue = dialogue;

        /*textAndCommands = new String[][]{
                {phraseSetter(0, 3), phraseSetter(3, 1), phraseSetter(4, 1), phraseSetter(5, 1)},
                {phraseSetter(6, 1), phraseSetter(7, 1), phraseSetter(8, 1)},
                {phraseSetter(9, 1), phraseSetter(10, 1), phraseSetter(11, 1)},
                {phraseSetter(12, 1), phraseSetter(13, 1), phraseSetter(14, 1)},
                {phraseSetter(15,2)}
        };
        dialogue.setKeysToTextsMapAndSetKeysToKeyList(getEventName(), 5);
        dialogue.addTextsToListsInMap(textAndCommands);*/

    }

    @Override public Organism startGameEvent(Scanner sc) {
        /*dialogue.printEventTextAndCommands(getSTART_EVENT_NUMBER());*/
        dialogue.printJson(START);
        chooseWeapon(sc);

        try {
            Thread.sleep(2500);
            features(player);
            Thread.sleep(2500);
            dialogue.printEventWithoutCommands(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.player;
    }

    public void chooseWeapon (Scanner sc) {
        int weaponTyped = gameScanner(sc, dialogue.getInnerListSize(getSTART_EVENT_NUMBER()));
        switch (weaponTyped) {
            case 1 -> {
                this.dialogue.printEventTextAndCommands(1 );
                weapon = new Weapon("Axe of Strength", 10, 5, Types.WEAPON, 15, 4, 2, 0, 7, WeaponTypes.AXE );}
            case 2 -> {
                this.dialogue.printEventTextAndCommands(2);;
                weapon = new Weapon("Wizard Stave", 12, 3, Types.WEAPON, 0, 5, 0, 25, 3, WeaponTypes.STAFF);
            }
            case 3 -> {
                this.dialogue.printEventTextAndCommands(3);
                weapon = new Weapon("Stinger", 9, 2, Types.WEAPON, 2, 3, 10, 10, 5, WeaponTypes.DAGGER );
            }
        }
        chooseSpec(player, weapon, weaponTyped, sc);
    }

    public String enterName(Scanner sc) {
        System.out.println("Введите имя персонажа: ");
        return sc.nextLine();
    }
    public void chooseSpec (Organism player, Weapon weapon, int weaponTyped, Scanner sc) {
        int spec = gameScanner(sc, dialogue.getInnerListSize(3));
        switch (weaponTyped) {
            case 1 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 13, 10,5,7,0,10 , GameClass.PALADIN, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case 2 -> player = new Player(enterName(sc), 14, 11,5,5,0,10, GameClass.WARRIOR, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon );
                }
                player.addToInventoryW(weapon);
                armorForWarrior(player);
            }
            case 2 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 5, 8,5,17,0,10, GameClass.FIRE_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case 2 -> player = new Player(enterName(sc), 5, 7,5,18,0,10, GameClass.FROST_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForMage(player);
            }
            case 3 -> {
                switch (spec) {
                    case 1 -> player = new Player(enterName(sc), 5, 10,15,5,0,10, GameClass.ROGUE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case 2 -> player = new Player(enterName(sc), 5, 8,8,14,0,10, GameClass.NECROMANT, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForRogue(player);
            }
        }
    }

    public void armorForWarrior (Organism player) {
        player.addToInventoryA(new Armors("Giant's Chest", 12, 10, Types.ARMOR, 2, 1, 0,0, ArmorType.PLATE, SlotType.CHEST));
        player.addToInventoryA(new Armors("Giant's Hands", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.PLATE, SlotType.HANDS));
        player.addToInventoryA(new Armors("Giant's Legs", 12, 10, Types.ARMOR, 1, 0, 0,0, ArmorType.PLATE, SlotType.LEGS));
        player.addToInventoryA(new Armors("Giant's Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.PLATE, SlotType.FEET));
        this.player = player;
    }
    public void armorForMage (Organism player) {
        player.addToInventoryA(new Armors("Robe of Mage", 12, 10, Types.ARMOR, 0, 1, 0,2, ArmorType.CLOTH, SlotType.CHEST));
        player.addToInventoryA(new Armors("Hands of Mage", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.CLOTH, SlotType.HANDS));
        player.addToInventoryA(new Armors("Magic Pants", 12, 10, Types.ARMOR, 0, 0, 0,2, ArmorType.CLOTH, SlotType.LEGS));
        player.addToInventoryA(new Armors("Magic Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.CLOTH, SlotType.FEET));
        this.player = player;
    }
    public void armorForRogue (Organism player) {
        player.addToInventoryA(new Armors("Shadow Chest", 12, 10, Types.ARMOR, 0, 1, 2,0, ArmorType.LEATHER, SlotType.CHEST));
        player.addToInventoryA(new Armors("Shadow Hands", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.LEATHER, SlotType.HANDS));
        player.addToInventoryA(new Armors("Pants of Darkness", 12, 10, Types.ARMOR, 0, 0, 1,0, ArmorType.LEATHER, SlotType.LEGS));
        player.addToInventoryA(new Armors("Spider Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.LEATHER, SlotType.FEET));
        this.player = player;
    }

}
