package main.java.Characters;

import main.java.GameProcesses.Plot.Quests.ActiveQuests;
import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
import main.java.Items.Types;
import main.java.Items.EquipableItem.Weapon.Weapon;
import main.java.Spells.Spell;

public class Player extends Organism implements StatsCalculator {
    private float fullStrength;
    private float fullStamina;
    private float fullAgility;
    private float fullIntellect;
    private int experience;

    GameClass gameClass;
    EquipableItem weapon;
    Equipment equipment;
    BackPack backPack;
    SpellBook spellBook;
    QuestJournal questJournal;
    private final float WEIGHT_COEFFICIENT = 2.9f;

    private float currentHealth;
    private float currentMana;

    public Player(String name, float strength, float stamina, float agility, float intellect, int experience, int gold, GameClass gameClass, Weapon weapon) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.gameClass = gameClass;
        this.equipment = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
        this.weapon = weapon;
        this.questJournal = new QuestJournal();
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }

    /*** BASE STATS*/
    public float getBaseStrength() {return super.getBaseStrength();}
    public float getBaseStamina() {return super.getBaseStamina();}
    public float getBaseAgility() {return super.getBaseAgility();}
    public float getBaseIntellect() {return super.getBaseIntellect();}

    public void setBaseStrength(float baseStrength) {super.setBaseStrength(baseStrength);}
    public void setBaseStamina(float baseStamina) {super.setBaseStamina(baseStamina);}
    public void setBaseAgility(float baseAgility) {super.setBaseAgility(baseAgility);}
    public void setBaseIntellect(float baseIntellect) {super.setBaseIntellect(baseIntellect);}

    public void setFullStrength(float strength) {this.fullStrength = strength;}
    public void setFullStamina(float stamina) {this.fullStamina = stamina;}
    public void setFullAgility(float agility) {this.fullAgility = agility;}
    public void setFullIntellect(float intellect) {this.fullIntellect = intellect;}


    /*** HEALTH AND MANA*/

    @Override public float getCurrentHealth() {return currentHealth;}
    @Override public float getCurrentMana() {return currentMana;}

    @Override public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}
    @Override public void setCurrentMana(float currentMana) {this.currentMana = currentMana;}

    @Override public float getHealthMaxValue() {return getBASE_HEALTH() + getBaseStamina();}
    @Override public float getManaMaxValue() {return getBASE_MANA() + getBaseIntellect();}

    @Override
    public void checkRestoreValueWhileHealing(float health) {
        if ((getCurrentHealth() + health) > getHealthMaxValue()) {
            setCurrentHealth(getHealthMaxValue());
            print("Восстановлено: " + (getHealthMaxValue() - getCurrentHealth()) + " здоровья");
        } else {
            setCurrentHealth(getCurrentHealth() + health);
            print("Восстановлено: " + health + "здоровья");
        }
    }

    /*** SECONDARY STATS/FEATURES*/
    @Override public float getAttackPower() {return (getFullStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient) + weapon.getAttackPower();}
    @Override public float getSpellPower() {return getFullIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    @Override public float getEvasion() {return getFullAgility() / evasionCoefficient;}
    @Override public float getSpellResistance() {return (getFullIntellect() + getFullStamina()) / spellResistanceCoefficient;}
    @Override public float getAttackSpeed() {return ((getFullAgility() + getFullStamina()) / attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}


    /*** EXP AND LEVEL*/
    @Override public int getCurrentExperience() {return super.getCurrentExperience();}
    @Override public int getLevel() {return super.getLevel();}
    @Override public void setLevel() {super.setLevel();}
    @Override public void setExperience(int experience) {super.setExperience(experience);} //for fights and quests
    public void checkExp(int experience) { ExperienceCalculator.checkExp(this.getCurrentExperience(), experience, this);}
    @Override public int getExpNeedToLevelUp() {return ExperienceCalculator.getExpNeeded();}
    @Override public void setExpWhenLevelUp() {super.setExpWhenLevelUp();}

    /*** QUESTS / QUEST JOURNAL */

    @Override
    public void addQuestToJouranl(ActiveQuests quest) {
        questJournal.addQuestToJournal(quest);
        print("Quest " + quest.getQuestName() + " Accepted");
    }

    @Override public void showQuestJournal() {System.out.println(questJournal.ShowAllQuests());}
    @Override public boolean getQuestObjectiveIsComplete(String name) {return questJournal.isItemIsQuestObjective(name);}
    @Override public boolean getQuestObjectiveName(String name) {return questJournal.getQuestObjectiveName(name);}
    @Override public boolean findQuestInJournal(ActiveQuests aq) {return questJournal.findQuestInJournal(aq);}
    @Override public void setQuestObjectiveCounter(String name) {questJournal.setQuestObjectiveCounter(name);}
    @Override public void removeQuestFromJournal(String name) {questJournal.removeQuest(name);}


    /***WORK WITH EQUIPMENT / USING ITEMS*/
    private void setWeapon(EquipableItem weapon) {this.weapon = weapon;}
    @Override
    public void setWeaponFromBackPackToInventory(int index) {
        setWeapon((EquipableItem) backPack.getFromBackPack(index));
        addToEquipment((EquipableItem) backPack.getFromBackPack(index));
        removeFromBAckPack(index);
    }
    @Override
    public void changeWeapon(int index) {
        addToBackPack(this.weapon);
        equipment.removeFromEquipment(this.weapon);
        this.setWeaponFromBackPackToInventory(index);
    }
    public void addToEquipment(EquipableItem a) {
        equipment.addToEquipment(a, this);
        print("\nYou have equipped item: " + a + " " + a.getFeatures());
    }
    @Override
    public void useFood(int index) {
        checkRestoreValueWhileHealing(getFromBackPackWithIndex(index).getRESTORES_HEALTH());
        removeFromBAckPack(index);
    }
    @Override
    public void dropItem(int index) {
        backPack.remove(index);
        //set to world map
    }

    /*** WORK WITH BACKPACK/BUSINESS METHODS*/
    public void addToBackPack(Item item) {
        if (getWeightByStrength(maxWeight(), this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight())) {
            if (item.getType().equals(Types.QUEST_ITEM) && !getQuestObjectiveIsComplete(item.getName())) {
                setQuestObjectiveCounter(item.getName());
                backPack.addToBackPack(item);
            } else backPack.addToBackPack(item);
            if (item instanceof EquipableItem e) {
                print("\nYou receive item: " + e + "\n" + e.getFeatures());
            } else print("\nYou receive item: " + item);
        } else
            print("\nNot enough strength. Required strength: " + requiredStrength(this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight()));
    }

    public int sumOfEquippedWeightAndBackPackWeight() {return backPack.sumOfWeightOfItemsInBackPack() + equipment.sumOfWeightOfEquippedItems();}
    @Override public float maxWeight() {return getBaseStrength() * getWEIGHT_COEFFICIENT();}
    public float getWEIGHT_COEFFICIENT() {return WEIGHT_COEFFICIENT;}
    public boolean findItemWithName(String name) {return backPack.findItemWithName(name);}

    @Override
    public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = backPack.showAllItemsForTrade(this).
                append("\nВаше золото: ").
                append(getGold()).
                append("\nВес в инвентаре: ").
                append(this.sumOfEquippedWeightAndBackPackWeight()).
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
                append(this.sumOfEquippedWeightAndBackPackWeight()).
                append("\nМаксимальный вес: ").append(maxWeight());
        return sb.toString();
    }

    @Override public void showItemsFromEquipment() {System.out.println(equipment.showAllEquippedItems());}
    @Override public Item getFromBackPack(String name) {return backPack.getFromBackPack(name);}
    @Override public Item getFromBackPackWithIndex(int index) {return backPack.getFromBackPack(index);}
    @Override public int getSize() {return backPack.getSize();}

    @Override
    public void setGold(int gold) {
        super.setGold(gold);
        print("You receive : " + gold + " gold");
    }

    @Override public void removeFromBAckPack(int index) {backPack.remove(index);}
    @Override public void removeFromBackPackByName(String name) {backPack.removeByName(name);}
    @Override public Types getItemType(int index) {return backPack.getItemType(index);}

    /*** FIGHT*/
    public void autoAttack(Organism attacker, Organism target) {
        attacker.setFullAttackPower(attacker.getAttackPower() + attacker.getAttackSpeed() / attackPowerANDSpellPowerANDAttackSpeedCoefficient + (float) (Math.random() * 5));
        takingPhysicalDamage(attacker, target);
    }

    public void takingPhysicalDamage(Organism attacker, Organism target) {
        target.setCurrentHealth(target.getCurrentHealth() - attacker.getAttackPower() + target.getEvasion());}

    public void addToSpellBook(Spell spell) {
        spellBook.addToSpellBook(spell);
    }

}
