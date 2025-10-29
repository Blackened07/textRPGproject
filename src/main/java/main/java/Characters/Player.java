package main.java.Characters;

import main.java.Items.Item;
import main.java.Items.Types;
import main.java.Items.Weapon.Weapon;
import main.java.Spells.Spell;

public class Player extends Organism implements StatsCalculator {
    GameClass gameClass;
    Item weapon;
    Equipment inventory;
    BackPack backPack;
    SpellBook spellBook;
    private final float WEIGHT_COEFFICIENT = 2.9f;

    public Player(String name, float strength, float stamina, float agility, float intellect, int experience, int gold, GameClass gameClass, Weapon weapon) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.gameClass = gameClass;
        this.inventory = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
        this.weapon = weapon;
    }
    /** BASE STATS */
    @Override
    public float getStrength() {return super.getStrength() + getStat(inventory, STRENGTH_INDEX);}
    public float getStamina() {return super.getStamina() + getStat(inventory, STAMINA_INDEX);}
    public float getAgility() {return super.getAgility() + getStat(inventory, AGILITY_INDEX);}
    public float getIntellect() {return super.getIntellect() + getStat(inventory, INTELLECT_INDEX);}

    /** HEALTH AND MANA */
    @Override public float getHealthMaxValue() {return getBASE_HEALTH() + getStamina();}
    @Override public float getManaMaxValue() {return getBASE_MANA() + getIntellect();}

    /** SECONDARY STATS/FEATURES */
    @Override public float getAttackPower() {return (getStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient) + weapon.getAttackPower();}
    @Override public float getSpellPower() {return getIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    @Override public float getEvasion() {return getAgility() / evasionCoefficient;}
    @Override public float getSpellResistance() {return (getIntellect() + getStamina()) / spellResistanceCoefficient;}
    @Override public float getAttackSpeed() {return ((getAgility() + getStamina()) / attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    /** EXP AND LEVEL*/
    @Override public int getExperience() {return super.getExperience();}
    @Override public int getLevel() {return super.getLevel();}
    @Override public void setLevel() {super.setLevel();} //afterLvlUpInvokeThis
    @Override public void setExperience(int experience) {super.setExperience(experience);} //for fights and quests
    public void checkExp() {if (ExperienceCalculator.checkExp(getExperience())) setLevel();} //final of fight or quest

    public float getWEIGHT_COEFFICIENT() {return WEIGHT_COEFFICIENT;}

    /***/

    private void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    @Override
    public void setWeaponFromBackPackToInventory(int index) {
        setWeapon(backPack.getFromBackPack(index));
        addToInventory(backPack.getFromBackPack(index));
        removeFromBAckPack(index);
    }

    public void changeWeapon(Weapon weapon) {
        addToBackPack(this.weapon);
        setWeapon(weapon);
        addToInventory(weapon);
    }

    /** BUSINESS*/
    public void addToInventory(Item a) {
        inventory.addToInventory(a);
        System.out.println("\nYou receive item: " + a + " " + a.getFeatures());
    }
    public void addToBackPack(Item item) {
        if (getWeightByStrength(maxWeight(), sumOfWeightInInventory() + item.getWeight())) {
        backPack.addToBackPack(item);
        print("\nYou receive item: " + item + "\n");
        } else print("\nNot enough strength. Required strength: " + requiredStrength(sumOfWeightInInventory() + item.getWeight()));
    }
    public int sumOfWeightInInventory () {return backPack.sumOfWeightOfItemsInBackPack() + inventory.sumOfWeightOfItemsEquipped();}
    @Override public float maxWeight() {return getStrength() * getWEIGHT_COEFFICIENT();}
    @Override public String findItemCanRestore(Item item) {return backPack.findItemCanRestore(item);}
    public boolean findItemWithName (String name) {return backPack.findItemWithName(name);}
    @Override
    public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = backPack.showAllItemsForTrade(this).
                append("\nВаше золото: ").
                append(getGold()).
                append("\nВес в инвентаре: ").
                append(sumOfWeightInInventory()).
                append("\nМаксимальный вес: ").append(maxWeight());
        return sb.toString();
    }
    @Override
    public String showItemsFromBackPack() {
        StringBuilder sb;
        sb = backPack.showAllItems().
                append("\nВаше золото: ").
                append(getGold()).
                append("\nВес в инвентаре: ").
                append(sumOfWeightInInventory()).
                append("\nМаксимальный вес: ").append(maxWeight());
        return sb.toString();
    }
    @Override public Item getFromBackPack(String name) {return backPack.getFromBackPack(name);}
    @Override public Item getFromBackPackWithIndex(int index) {return backPack.getFromBackPack(index);}
    @Override public int getSize() {return backPack.getSize();}
    @Override public void setGold(int gold) {super.setGold(gold);}
    @Override public void removeFromBAckPack(int index) {backPack.remove(index);}
    @Override public Types getItemType(int index) {return backPack.getItemType(index);}

    public void addToSpellBook(Spell spell) {
        spellBook.addToSpellBook(spell);
    }

}
