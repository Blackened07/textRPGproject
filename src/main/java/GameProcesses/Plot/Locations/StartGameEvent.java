package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.*;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Armor.ArmorType;
import main.java.Items.Armor.Armors;
import main.java.Items.Armor.SlotType;
import main.java.Items.Types;
import main.java.Items.Weapon.Weapon;
import main.java.Items.Weapon.WeaponTypes;

import java.util.Scanner;

public class StartGameEvent extends Events{
    Weapon weapon;
    Organism player;

    public StartGameEvent(String eventName, Location LOCATION, StoryReader story) {
        super(eventName, LOCATION, story);
        super.setEventsTexts(story.phrasesBuilder(0, 6));
        super.setEventsTexts(story.phrasesBuilder(6, 3));
        super.setEventsTexts(story.phrasesBuilder(9, 3));
        super.setEventsTexts(story.phrasesBuilder(12, 3));
        super.setEventsTexts(story.phrasesBuilder(15, 2));
        super.setEventsTexts(story.phrasesBuilder(17, 3));
    }

    @Override public void printEvent(int index) {System.out.println(getEventsText(index));}
    @Override public void features(Organism player) {super.features(player);}
    @Override public String gameScanner(Scanner sc) {return super.gameScanner(sc);}
    @Override public Organism startEvent(Scanner sc) {
        printEvent(0);
        chooseWeapon(sc);
        features(player);
        return this.player;
    }

    public void chooseWeapon (Scanner sc) {
        String weaponTyped = gameScanner(sc);
        switch (weaponTyped) {
            case "Axe" -> {
                printEvent(1);
                weapon = new Weapon("Axe of Strength", 10, 5, Types.WEAPON, 15, 4, 2, 0, 7, WeaponTypes.AXE );}
            case "Staff" -> {
                printEvent(2);
                weapon = new Weapon("Wizard Stave", 12, 3, Types.WEAPON, 0, 5, 0, 25, 3, WeaponTypes.STAFF);
            }
            case "Dagger" -> {
                printEvent(3);
                weapon = new Weapon("Stinger", 9, 2, Types.WEAPON, 2, 3, 10, 10, 5, WeaponTypes.DAGGER );
            }
        }
        chooseSpec(player, weapon, weaponTyped, sc);
        sc.close();
    }

    public String enterName(Scanner sc) {
        System.out.println("Введите имя персонажа: ");
        return sc.nextLine();
    }
    public void chooseSpec (Organism player, Weapon weapon, String weaponTyped, Scanner sc) {
        String spec = gameScanner(sc);
        switch (weaponTyped) {
            case "Axe" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(sc), 13, 10,5,7,0,10 , GameClass.PALADIN, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(sc), 14, 11,5,5,0,10, GameClass.WARRIOR, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon );
                }
                player.addToInventoryW(weapon);
                armorForWarrior(player);
            }
            case "Staff" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(sc), 5, 8,5,17,0,10, GameClass.FIRE_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(sc), 5, 7,5,18,0,10, GameClass.FROST_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForMage(player);
            }
            case "Dagger" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(sc), 5, 10,15,5,0,10, GameClass.ROGUE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(sc), 5, 8,8,14,0,10, GameClass.NECROMANT, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForRogue(player);
            }
        }
        sc.close();
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
