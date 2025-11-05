package main.java.Characters;

import main.java.GameProcesses.Plot.Quests.ActiveQuests;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.UseItemsFromBackPack;
import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
import main.java.Items.Types;
import main.java.Spells.Spell;

public class Organism implements PrintableInterfaces, StatsCalculator, UseItemsFromBackPack {
    private final String NAME;

    private float baseStrength;
    private float baseStamina;
    private float baseAgility;
    private float baseIntellect;

    private float fullStrength;
    private float fullStamina;
    private float fullAgility;
    private float fullIntellect;
    private int experience;

    private int level = 1;

    private final float BASE_HEALTH = 50;
    private final float BASE_MANA = 50;

    private float currentHealth;
    private float currentMana;
    private float healthMaxValue;
    private float manaMaxValue;

    private float attackPower;
    private float spellPower;
    private float evasion;
    private float spellResistance;
    private float attackSpeed;

    private float fullAttackPower;

    final float attackPowerANDSpellPowerANDAttackSpeedCoefficient = 1.6f;
    final float evasionCoefficient = 2f;
    final float spellResistanceCoefficient = 16f;
    final float attackSpeedCoefficient = 10f;

    private int gold;

    Equipment inventory;
    BackPack backPack;
    SpellBook spellBook;

    public Organism(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        this.NAME = name;
        this.baseStrength = strength;
        this.baseStamina = stamina;
        this.baseAgility = agility;
        this.baseIntellect = intellect;
        this.experience = experience;
        this.gold = gold;
        this.inventory = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
    }
    /** BASE STATS */
    public String getNAME() {return NAME;}

    public float getBaseStrength() {return baseStrength;}
    public float getBaseStamina() {return baseStamina;}
    public float getBaseAgility() {return baseAgility;}
    public float getBaseIntellect() {return baseIntellect;}

    public void setBaseStrength(float baseStrength) {this.baseStrength = baseStrength;}
    public void setBaseStamina(float baseStamina) {this.baseStamina = baseStamina;}
    public void setBaseAgility(float baseAgility) {this.baseAgility = baseAgility;}
    public void setBaseIntellect(float baseIntellect) {this.baseIntellect = baseIntellect;}

    public float getFullStrength() {return fullStrength;}
    public float getFullStamina() {return fullStamina;}
    public float getFullAgility() {return fullAgility;}
    public float getFullIntellect() {return fullIntellect;}

    public void setFullStrength(float strength) {this.fullStrength = strength;}
    public void setFullStamina(float stamina) {this.fullStamina = stamina;}
    public void setFullAgility(float agility) {this.fullAgility = agility;}
    public void setFullIntellect(float intellect) {this.fullIntellect = intellect;}

    /** HEALTH AND MANA */
    public float getCurrentHealth() {return currentHealth;} //currentHealth
    public float getCurrentMana() {return currentMana;}
    public float getHealthMaxValue() {return BASE_HEALTH + getBaseStamina();} //MaxHealth
    public float getManaMaxValue() {return BASE_MANA + getBaseIntellect();}
    public float getBASE_HEALTH() {return BASE_HEALTH;}
    public float getBASE_MANA() {return BASE_MANA;}
    public void setCurrentHealth(float health) {this.currentHealth = health;}
    public void checkRestoreValueWhileHealing(float health) {}
    public void setCurrentMana(float currentMana) {this.currentMana = currentMana;}

    /** SECONDARY STATS/FEATURES */
    public float getAttackPower() {return getBaseStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getSpellPower() {return getBaseIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getEvasion() {return getBaseAgility() / evasionCoefficient;}
    public float getSpellResistance() {return (getBaseIntellect() + getBaseStamina()) / spellResistanceCoefficient;}
    public float getAttackSpeed() {return (getBaseAgility() + getBaseStamina()/attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    public float getFullAttackPower() {return fullAttackPower;}
    public void setFullAttackPower(float fullAttackPower) {this.fullAttackPower = fullAttackPower;}

    /** ECONOMY */
    public int getGold() {return gold;}
    public void setGold(int gold) {this.gold += gold;}

    public void setGoldAfterTrade(int gold) {
        if (getGold() < gold) setGold(0);
        else setGold(gold);
    }

    /** BANK */
    public void addToEquipment(EquipableItem item) {}
    public void addToBackPack(Item item){}
    public Item getFromBackPack (String name) {return null;}
    public Item getFromBackPackWithIndex (int index) {return null;}
    public boolean findItemWithName(String name) {return false;}
    public int getSize(){return 0;}
    public void removeFromBAckPack(int index){}
    public int sumOfEquippedWeightAndBackPackWeight() {return backPack.sumOfWeightOfItemsInBackPack() + inventory.sumOfWeightOfEquippedItems();}
    public float maxWeight() {return 0;}
    public Types getItemType(int index){return null;}
    public void setWeaponFromBackPackToInventory(int index){};
    public void changeWeapon(int index){};
    public void useFood(int index) {}
    public void dropItem(int index) {}
    public void removeFromBackPackByName(String name) {}


    /**ShowItems */
    public String showItemsFromBackPackForTrade() {return "";}
    public String showItemsFromBackPack(){return "";}
    public void showItemsFromEquipment(){}

    /** QUESTS */
    public void addQuestToJouranl (ActiveQuests quest){};
    public void showQuestJournal(){}
    public boolean getQuestObjectiveIsComplete(String name){return false;}
    public boolean getQuestObjectiveName(String name) {return false;}
    public String findQuestItemInCreaturesBackPack(){return "";}
    public Item addQuestItemToPlayerFromCreature () {return null;}
    public boolean findQuestInJournal(ActiveQuests aq) {return true;}
    public void setQuestObjectiveCounter (String name) {}
    public void removeQuestFromJournal (String name) {}

    /** EXP AND LEVEL*/
    public int getLevel() {return level;}
    public void setLevel() {this.level ++;}
    public int getCurrentExperience() {return experience;}
    public void setExperience(int experience) {this.experience += experience;}
    public void checkExp(int exp){}
    public int getExpNeedToLevelUp(){return 0;}
    public void setExpWhenLevelUp(){this.experience = 0;}

    /**Attack */

    public void autoAttack (Organism attacker, Organism target) {
        attacker.setFullAttackPower(attacker.getAttackPower() + attacker.getAttackSpeed() / attackPowerANDSpellPowerANDAttackSpeedCoefficient + (float)(Math.random() * 5));
        takingPhysicalDamage(attacker, target);

    }
    public void takingPhysicalDamage(Organism attacker, Organism target) {
        target.setCurrentHealth(target.getCurrentHealth() - attacker.getFullAttackPower() + target.getEvasion());
    }

    /** SPELLS*/
    public void addToSpellBook(Spell spell){};
}
