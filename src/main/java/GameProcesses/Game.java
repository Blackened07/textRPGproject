package main.java.GameProcesses;

import main.java.Characters.*;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Armor.ArmorType;
import main.java.Items.Armor.Armors;
import main.java.Items.Armor.SlotType;
import main.java.Items.Types;
import main.java.Items.Weapon.Weapon;
import main.java.Items.Weapon.WeaponTypes;

import java.util.Scanner;

public class Game {
    private String message;
    private String combatMessage;

    Organism player;
    Organism creature;
    Organism npc;
    StoryReader story;
    Scanner sc = new Scanner(System.in);;

    Weapon weapon;

    public Game(StoryReader story) {
        this.story = story;
    }// new locations, events, npc for location with items

    public void startGame () throws InterruptedException {


        story.print(story.phrasesBuilder(0, 6));
        chooseWeapon();
        features();
        Thread.sleep(5000);
        story.print(story.phrasesBuilder(15, 2));
        Thread.sleep(5000);
        story.print(story.phrasesBuilder(17, 3));
        //methodFromStartVillage
    }

    //Вынести в отдельный класс старт гейм

    //with weapon player choose class
    public void chooseWeapon () {
        String weaponTyped = sc.nextLine();
        switch (weaponTyped) {
            case "Axe" -> {
                story.print(story.phrasesBuilder(6, 3));
                weapon = new Weapon("Axe of Strength", 10, 5, Types.WEAPON, 15, 4, 2, 0, 7, WeaponTypes.AXE );
            }
            case "Staff" -> {
                story.print(story.phrasesBuilder(9, 3));
                weapon = new Weapon("Wizard Stave", 12, 3, Types.WEAPON, 0, 5, 0, 25, 3, WeaponTypes.STAFF);
            }
            case "Dagger" -> {
                story.print(story.phrasesBuilder(12, 3));
                weapon = new Weapon("Stinger", 9, 2, Types.WEAPON, 2, 3, 10, 10, 5, WeaponTypes.DAGGER );
            }
        }
        chooseSpec(weaponTyped);
        sc.close();
    }

    public String enterName() {
        System.out.println("Введите имя персонажа: ");
        return sc.nextLine();
    }
    public void chooseSpec (String weaponTyped) {
        String spec = sc.nextLine();
        switch (weaponTyped) {
            case "Axe" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(), 13, 10,5,7,0,10 ,GameClass.PALADIN, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(), 14, 11,5,5,0,10, GameClass.WARRIOR, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon );
                }
                player.addToInventoryW(weapon);
                armorForWarrior();
            }
            case "Staff" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(), 5, 8,5,17,0,10, GameClass.FIRE_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(), 5, 7,5,18,0,10, GameClass.FROST_MAGE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForMage();
            }
            case "Dagger" -> {
                switch (spec) {
                    case "1" -> player = new Player(enterName(), 5, 10,15,5,0,10, GameClass.ROGUE, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                    case "2" -> player = new Player(enterName(), 5, 8,8,14,0,10, GameClass.NECROMANT, Inventory.createInventory(), BackPack.createBackPack(), SpellBook.createSpellBook(), weapon);
                }
                player.addToInventoryW(weapon);
                armorForRogue();
            }
        }
    }

    public void armorForWarrior () {
        player.addToInventoryA(new Armors("Giant's Chest", 12, 10, Types.ARMOR, 2, 1, 0,0, ArmorType.PLATE, SlotType.CHEST));
        player.addToInventoryA(new Armors("Giant's Hands", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.PLATE, SlotType.HANDS));
        player.addToInventoryA(new Armors("Giant's Legs", 12, 10, Types.ARMOR, 1, 0, 0,0, ArmorType.PLATE, SlotType.LEGS));
        player.addToInventoryA(new Armors("Giant's Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.PLATE, SlotType.FEET));
    }
    public void armorForMage () {
        player.addToInventoryA(new Armors("Robe of Mage", 12, 10, Types.ARMOR, 0, 1, 0,2, ArmorType.CLOTH, SlotType.CHEST));
        player.addToInventoryA(new Armors("Hands of Mage", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.CLOTH, SlotType.HANDS));
        player.addToInventoryA(new Armors("Magic Pants", 12, 10, Types.ARMOR, 0, 0, 0,2, ArmorType.CLOTH, SlotType.LEGS));
        player.addToInventoryA(new Armors("Magic Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.CLOTH, SlotType.FEET));
    }
    public void armorForRogue () {
        player.addToInventoryA(new Armors("Shadow Chest", 12, 10, Types.ARMOR, 0, 1, 2,0, ArmorType.LEATHER, SlotType.CHEST));
        player.addToInventoryA(new Armors("Shadow Hands", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.LEATHER, SlotType.HANDS));
        player.addToInventoryA(new Armors("Pants of Darkness", 12, 10, Types.ARMOR, 0, 0, 1,0, ArmorType.LEATHER, SlotType.LEGS));
        player.addToInventoryA(new Armors("Spider Boots", 12, 10, Types.ARMOR, 0, 0, 0,0, ArmorType.LEATHER, SlotType.FEET));
    }
    public void features () {
        player.setCurrentHealth(player.getHealthMaxValue());
        player.setCurrentMana(player.getManaMaxValue());
        System.out.println("Ваши характеристикки: \nИмя: " + player.getNAME() + "\nУровень: " + player.getLevel() + "\nЗдоровье : " + player.getHealthMaxValue()
                + "\nМана: " + player.getManaMaxValue() + "\nСила: " + player.getStrength() + "\nВыносливость: " + player.getStamina() + "\nЛовкость: " + player.getAgility()
                + "\nИнтеллект: " + player.getIntellect() + "\nСила Атаки: " + player.getAttackPower() + "\nСкорость Атаки: " + player.getAttackSpeed()
        + "\nУклонение: " + player.getEvasion() + "\nСила Заклинаний: " + player.getSpellPower() + "\nСопротивление магии: " + player.getSpellResistance());
    }

}
