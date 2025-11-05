package main.java.Characters;

import main.java.GameProcesses.Plot.Quests.ActiveQuests;
import main.java.Items.EquipableItem.EquipableItem;
import main.java.Items.Item;
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

    public Player(String name, float strength, float stamina, float agility, float intellect, int experience, int gold, GameClass gameClass, Weapon weapon) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.gameClass = gameClass;
        this.equipment = new Equipment();
        this.backPack = new BackPack();
        this.spellBook = new SpellBook();
        this.weapon = weapon;
        this.questJournal = new QuestJournal();
        setHealthMaxValue();
        setManaMaxValue();
    }

    @Override public void checkRestoreValueWhileHealing(float health) {
        if ((getCurrentHealth() + health) > getHealthMaxValue()) {
            setCurrentHealth(getHealthMaxValue());
            print("Восстановлено: " + (getHealthMaxValue() - getCurrentHealth()) + " здоровья");
        } else {
            setCurrentHealth(getCurrentHealth() + health);
            print("Восстановлено: " + health + "здоровья");
        }
    }

    /*** EXP AND LEVEL*/
    public void checkExp(int experience) { ExperienceCalculator.checkExp(this.getCurrentExperience(), experience, this);}

    /*** QUESTS / QUEST JOURNAL */
    @Override
    public void addQuestToJournal(ActiveQuests quest) {
        questJournal.addQuestToJournal(quest);
        print("Квест " + quest.getQuestName() + " добавлен в журнал заданий");
    }

    @Override public void showQuestJournal() {System.out.println(questJournal.ShowAllQuests());}
    @Override public boolean getQuestObjectiveIsComplete(String name) {return questJournal.isItemIsQuestObjective(name);}
    @Override public boolean getQuestObjectiveName(String name) {return questJournal.getQuestObjectiveName(name);}
    @Override public boolean findQuestInJournal(ActiveQuests aq) {return questJournal.findQuestInJournal(aq);}
    @Override public void setQuestObjectiveCounter(String name) {questJournal.setQuestObjectiveCounter(name);}
    @Override public void removeQuestFromJournal(String name) {questJournal.removeQuest(name);}


    /***WORK WITH EQUIPMENT / USING ITEMS*/
    private void setWeapon(EquipableItem weapon) {this.weapon = weapon;}
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
    @Override public void addToEquipment(EquipableItem item) {
        super.addToEquipment(item);
        print("\nВы надели: " + item + " " + item.getFeatures());
    }

    /*** WORK WITH BACKPACK/BUSINESS METHODS*/
    @Override public void addToBackPack(Item item) {
        if (getWeightByStrength(maxWeight(), this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight())) {
            if (item.getType().equals(Types.QUEST_ITEM) && !getQuestObjectiveIsComplete(item.getName())) {
                setQuestObjectiveCounter(item.getName());
                backPack.addToBackPack(item);
            } else backPack.addToBackPack(item);
            if (item instanceof EquipableItem e) {
                print("\nВы получили: " + e + "\n" + e.getFeatures());
            } else print("\nВы получили: " + item);
        } else
            print("\nНедостаточно силы! Необходимый показатель силы: " + requiredStrength(this.sumOfEquippedWeightAndBackPackWeight() + item.getWeight()));
    }

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
    /** SHOW METHODS **/
    @Override public void showItemsFromEquipment() {System.out.println(equipment.showAllEquippedItems());}


    @Override public void setGold(int gold) {
        super.setGold(gold);
        print("You receive : " + gold + " gold");
    }

    /*** FIGHT*/
    public void addToSpellBook(Spell spell) {spellBook.addToSpellBook(spell);}

}
