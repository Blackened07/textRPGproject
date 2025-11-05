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
    private final float WEIGHT_COEFFICIENT = 2.9f;

    private int gold;

    Equipment equipment;
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
        this.equipment = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
    }
    /** BASE STATS */
    public String getNAME() {return NAME;}

    public float getBaseStrength() {return baseStrength;}
    public float getBaseStamina() {return baseStamina;}
    public float getBaseAgility() {return baseAgility;}
    public float getBaseIntellect() {return baseIntellect;}
    //BaseStats is onlyForLvlUp
    public void setBaseStrength(float baseStrength) {this.baseStrength = baseStrength;}
    public void setBaseStamina(float baseStamina) {this.baseStamina = baseStamina;}
    public void setBaseAgility(float baseAgility) {this.baseAgility = baseAgility;}
    public void setBaseIntellect(float baseIntellect) {this.baseIntellect = baseIntellect;}

    public float getFullStrength() {return fullStrength;}
    public float getFullStamina() {return fullStamina;}
    public float getFullAgility() {return fullAgility;}
    public float getFullIntellect() {return fullIntellect;}

    public void setFullStrength(float strength) {this.fullStrength = strength + getBaseStrength();}
    public void setFullStamina(float stamina) {this.fullStamina = stamina + getBaseStamina();}
    public void setFullAgility(float agility) {this.fullAgility = agility + getBaseAgility();}
    public void setFullIntellect(float intellect) {this.fullIntellect = intellect + getBaseIntellect();}

    /** HEALTH AND MANA */
    public float getCurrentHealth() {return currentHealth;} //currentHealth
    public float getCurrentMana() {return currentMana;}

    public void setCurrentHealth(float health) {this.currentHealth = health;}
    public void setCurrentMana(float currentMana) {this.currentMana = currentMana;}

    public float getHealthMaxValue() {return BASE_HEALTH + getFullStamina();} //MaxHealth
    public float getManaMaxValue() {return BASE_MANA + getFullIntellect();}

    public void checkRestoreValueWhileHealing(float health) {}

    /** SECONDARY STATS/FEATURES */
    public float getAttackPower() {return getFullStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getSpellPower() {return getFullIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    public float getEvasion() {return getFullAgility() / evasionCoefficient;}
    public float getSpellResistance() {return (getFullIntellect() + getFullStamina()) / spellResistanceCoefficient;}
    public float getAttackSpeed() {return (getFullAgility() + getFullStamina()/attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    public float getFullAttackPower() {return fullAttackPower;}
    public void setFullAttackPower(float fullAttackPower) {this.fullAttackPower = fullAttackPower;}

    /** ECONOMY */
    public int getGold() {return gold;}
    public void setGold(int gold) {this.gold += gold;}
    public void setGoldAfterTrade(int gold) {
        if (getGold() < gold) setGold(0);
        else setGold(gold);
    }

    /** EQUIPMENT */
    public void addToEquipment(EquipableItem item) {equipment.addToEquipment(item, this);;}
    public void setWeaponFromBackPackToInventory(int index){};
    public void changeWeapon(int index){};

    /** BACKPACK **/
    public void addToBackPack(Item item){}
    public Item getFromBackPack(String name) {return backPack.getFromBackPack(name);}
    public Item getFromBackPackWithIndex(int index) {return backPack.getFromBackPack(index);}
    public boolean findItemWithName(String name) {return backPack.findItemWithName(name);}
    public int getSize() {return backPack.getSize();}
    public void removeFromBAckPack(int index) {backPack.remove(index);}
    public int sumOfEquippedWeightAndBackPackWeight() {return backPack.sumOfWeightOfItemsInBackPack() + equipment.sumOfWeightOfEquippedItems();}
    public float maxWeight() {return getFullStrength() * WEIGHT_COEFFICIENT;}
    public Types getItemType(int index) {return backPack.getItemType(index);}
    public void removeFromBackPackByName(String name) {backPack.removeByName(name);}

    public void dropItem(int index) {backPack.remove(index);}//set to world map}
    /** FOOD AND DRINKS */
    public void useFood(int index) {
        checkRestoreValueWhileHealing(getFromBackPackWithIndex(index).getRESTORES_HEALTH());
        removeFromBAckPack(index);
    }
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
    public int getExpNeedToLevelUp() {return ExperienceCalculator.getExpNeeded();}
    public void setExpWhenLevelUp(){this.experience = 0;}

    /** FIGHT **/
    /** Physical_Attack */
    public void autoAttack(Organism attacker, Organism target) {
        attacker.setFullAttackPower(attacker.getAttackPower() + attacker.getAttackSpeed() / attackPowerANDSpellPowerANDAttackSpeedCoefficient + (float) (Math.random() * 5));
        takingPhysicalDamage(attacker, target);
    }

    public void takingPhysicalDamage(Organism attacker, Organism target) {
        target.setCurrentHealth(target.getCurrentHealth() - attacker.getAttackPower() + target.getEvasion());}

    /** SPELLS */
    public void addToSpellBook(Spell spell){};
}
