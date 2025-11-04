package main.java.Characters;

import main.java.Items.Item;
import main.java.Items.Loot;

import java.util.List;

public class Creature extends Organism {

    private BackPack loot;

    public Creature(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.loot = new BackPack();
        loot.addListOfLootToBackPack(Loot.getLoot(name));
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }

    @Override
    public Item getFromBackPackWithIndex(int index) {
        return loot.getFromBackPack(index);
    }

    @Override
    public String findQuestItemInCreaturesBackPack() {
        return loot.findQuestItem();
    }

    @Override
    public Item addQuestItemToPlayerFromCreature() {
        return loot.getQuestItem();
    }

    @Override
    public int getSize() {
        return loot.getSize();
    }

    @Override
    public void autoAttack(Organism attacker, Organism target) {
        super.autoAttack(attacker, target);
    }

    @Override
    public void takingPhysicalDamage(Organism attacker, Organism target) {
        super.takingPhysicalDamage(attacker, target);
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }
}
