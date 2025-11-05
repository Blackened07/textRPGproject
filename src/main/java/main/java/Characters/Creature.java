package main.java.Characters;

import main.java.Items.Item;
import main.java.Items.Loot;

import java.util.List;

public class Creature extends Organism {


    public Creature(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        getBackPack().addListOfLootToBackPack(Loot.getLoot(name));
        setHealthMaxValue();
        setManaMaxValue();
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }

    @Override public String findQuestItemInCreaturesBackPack() {return getBackPack().findQuestItem();}
    @Override public Item addQuestItemToPlayerFromCreature() {return getBackPack().getQuestItem();}

}
