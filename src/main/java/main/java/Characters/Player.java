package main.java.Characters;

import main.java.GameProcesses.Plot.Quests.ActiveQuests;
import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
import main.java.Items.QuestItem;
import main.java.Items.Types;
import main.java.Items.EquipableItem.Weapon.Weapon;
import main.java.Spells.Spell;

public class Player extends Organism implements StatsCalculator {
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
    }
    /** BASE STATS */
    @Override
    public float getStrength() {return super.getStrength() + getStat(equipment, STRENGTH_INDEX);}
    public float getStamina() {return super.getStamina() + getStat(equipment, STAMINA_INDEX);}
    public float getAgility() {return super.getAgility() + getStat(equipment, AGILITY_INDEX);}
    public float getIntellect() {return super.getIntellect() + getStat(equipment, INTELLECT_INDEX);}


    /** HEALTH AND MANA */

    @Override public float getCurrentHealth() {return currentHealth;}
    @Override public float getCurrentMana() {return currentMana;}
    @Override public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}
    @Override public void setCurrentMana(float currentMana) {this.currentMana = currentMana;}
    @Override public float getHealthMaxValue() {return getBASE_HEALTH() + getStamina();}
    @Override public float getManaMaxValue() {return getBASE_MANA() + getIntellect();}

    @Override public void checkRestoreValueWhileHealing(float health) {
        if ((getCurrentHealth() + health) > getHealthMaxValue()) {
            setCurrentHealth(getHealthMaxValue());
            print("Восстановлено: " + (getHealthMaxValue() - getCurrentHealth()) + " здоровья");
        } else {setCurrentHealth(getCurrentHealth() + health);
        print("Восстановлено: " + health + "здоровья");}
    }

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

    /** QUESTS */

    @Override
    public void addQuestToJouranl(ActiveQuests quest) {
        questJournal.addQuestToJournal(quest);
        print("Quest " + quest.getQuestName() + " Accepted");
    }

    @Override public void showQuestJournal() {System.out.println(questJournal.ShowAllQuests());}

    @Override
    public boolean getQuestObjective(String name) {
        return questJournal.getQuestObjective(name);
    }

    /***USING ITEMS*/

    private void setWeapon(EquipableItem weapon) {
        this.weapon = weapon;
    }
    @Override public void setWeaponFromBackPackToInventory(int index) {
        setWeapon((EquipableItem) backPack.getFromBackPack(index));
        addToEquipment((EquipableItem) backPack.getFromBackPack(index));
        removeFromBAckPack(index);
    }
    @Override public void changeWeapon(int index) {
        addToBackPack(this.weapon);
        equipment.removeFromEquipment(this.weapon);
        this.setWeaponFromBackPackToInventory(index);
    }
    public void addToEquipment(EquipableItem a) {
        equipment.addToEquipment(a);
        print("\nYou have equipped item: " + a + " " + a.getFeatures());
    }



    @Override
    public void useFood(int index) {
        checkRestoreValueWhileHealing(getFromBackPackWithIndex(index).getRESTORES_HEALTH());
    }

    @Override
    public void dropItem(int index) {
        backPack.remove(index);
        //set to world map
    }

    /** BUSINESS*/
    public void addToBackPack(Item item) {
        if (getWeightByStrength(maxWeight(), this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight())) {
            if (item.getType().equals(Types.QUEST_ITEM) && !questJournal.getQuest(((QuestItem) item).getFOR_QUEST()).isComplete()) {
                questJournal.getQuest(((QuestItem) item).getFOR_QUEST()).setQuestObjectiveCounter(1);
                backPack.addToBackPack(item);
            }
        backPack.addToBackPack(item);
        if (item instanceof EquipableItem e) {
            print("\nYou receive item: " + e + "\n" + e.getFeatures() );
        } else print("\nYou receive item: " + item );
        } else print("\nNot enough strength. Required strength: " + requiredStrength(this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight()));
    }
    public int sumOfEquippedWeightAndBackPackWeight () {return backPack.sumOfWeightOfItemsInBackPack() + equipment.sumOfWeightOfEquippedItems();}
    @Override public float maxWeight() {return getStrength() * getWEIGHT_COEFFICIENT();}
    public boolean findItemWithName (String name) {return backPack.findItemWithName(name);}
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

    @Override
    public void showItemsFromEquipment() {
        System.out.println(equipment.showAllEquippedItems());
    }

    @Override public Item getFromBackPack(String name) {return backPack.getFromBackPack(name);}
    @Override public Item getFromBackPackWithIndex(int index) {return backPack.getFromBackPack(index);}
    @Override public int getSize() {return backPack.getSize();}
    @Override public void setGold(int gold) {super.setGold(gold);}
    @Override public void removeFromBAckPack(int index) {backPack.remove(index);}
    @Override public Types getItemType(int index) {return backPack.getItemType(index);}


    @Override
    public void autoAttack(Organism attacker, Organism target) {
        attacker.setFullAttackPower(attacker.getAttackPower() + attacker.getAttackSpeed() / attackPowerANDSpellPowerANDAttackSpeedCoefficient + (float)(Math.random() * 5));
        takingPhysicalDamage(attacker, target);
    }

    @Override
    public void takingPhysicalDamage(Organism attacker, Organism target) {
        target.setCurrentHealth(target.getCurrentHealth() - attacker.getAttackPower() + target.getEvasion());
    }

    /** FIGHT */



    public void addToSpellBook(Spell spell) {
        spellBook.addToSpellBook(spell);
    }

}
