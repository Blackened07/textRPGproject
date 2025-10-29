package main.java.Characters;

import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.UseItemsFromBackPack;
import main.java.Items.Item;
import main.java.Items.Types;
import main.java.Spells.Spell;

public class Organism implements PrintableInterfaces, StatsCalculator, UseItemsFromBackPack {
    private final String NAME;

    private float strength;
    private float stamina;
    private float agility;
    private float intellect;
    private int experience;

    private int level = 1;

    private final float BASE_HEALTH = 20;
    private final float BASE_MANA = 20;

    private float currentHealth;
    private float currentMana;
    private float healthMaxValue;
    private float manaMaxValue;

    private float attackPower;
    private float spellPower;
    private float evasion;
    private float spellResistance;
    private float attackSpeed;

    final float attackPowerANDSpellPowerANDAttackSpeedCoefficient = 1.6f;
    final float evasionCoefficient = 2f;
    final float spellResistanceCoefficient = 16f;
    final float attackSpeedCoefficient = 10f;

    private int gold;

    Equipment inventory;
    BackPack backPack;
    SpellBook spellBook;

    /// ADD QUESTS, DIALOGUES

    public Organism(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        this.NAME = name;
        this.strength = strength;
        this.stamina = stamina;
        this.agility = agility;
        this.intellect = intellect;
        this.experience = experience;
        this.gold = gold;
        this.inventory = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
    }
    /** BASE STATS */
    public String getNAME() {return NAME;}

    public float getStrength() {return strength;}
    public float getStamina() {return stamina;}
    public float getAgility() {return agility;}
    public float getIntellect() {return intellect;}

    /** HEALTH AND MANA */
    public float getCurrentHealth() {return currentHealth;} //currentHealth
    public float getCurrentMana() {return currentMana;}
    public float getHealthMaxValue() {return BASE_HEALTH + getStamina();} //MaxHealth
    public float getManaMaxValue() {return BASE_MANA + getIntellect();}
    public float getBASE_HEALTH() {return BASE_HEALTH;}
    public float getBASE_MANA() {return BASE_MANA;}
    public void setCurrentHealth(float health) {this.currentHealth = health;}
    public void checkRestoreValueWhileHealing(float health) {}
    public void setCurrentMana(float currentMana) {this.currentMana = currentMana;}

    /** SECONDARY STATS/FEATURES */
    public float getAttackPower() {return getStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getSpellPower() {return getIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getEvasion() {return getAgility() / evasionCoefficient;}
    public float getSpellResistance() {return (getIntellect() + getStamina()) / spellResistanceCoefficient;}
    public float getAttackSpeed() {return (getAgility() + getStamina()/attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    /** ECONOMY */
    public int getGold() {return gold;}
    public void setGold(int gold) {this.gold += gold;}

    public void setGoldAfterTrade(int gold) {
        if (getGold() < gold) setGold(0);
        else setGold(gold);
    }

    /** BANK */
    public void addToInventory(Item item) {}
    public void addToBackPack(Item item){}
    public Item getFromBackPack (String name) {return null;}
    public Item getFromBackPackWithIndex (int index) {return null;}
    public boolean findItemWithName(String name) {return false;}
    public String showItemsFromBackPackForTrade() {return "";}
    public String showItemsFromBackPack(){return "";}
    public int getSize(){return 0;}
    public void removeFromBAckPack(int index){}
    public int sumOfWeightInInventory() {return backPack.sumOfWeightOfItemsInBackPack() + inventory.sumOfWeightOfItemsEquipped();}
    public float maxWeight() {return 0;}
    public Types getItemType(int index){return null;}
    public void setWeaponFromBackPackToInventory(int index){};
    public void changeWeapon(int index){};
    public void useFood(int index) {}
    public void dropItem(int index) {}

    /** EXP AND LEVEL*/
    public int getLevel() {return level;}
    public void setLevel() {this.level ++;}
    public int getExperience() {return experience;}
    public void setExperience(int experience) {this.experience = experience;}

    /** SPELLS*/
    public void addToSpellBook(Spell spell){};
}
