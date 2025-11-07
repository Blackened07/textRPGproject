package main.java.Characters;

import main.java.GameProcesses.Plot.Locations.Location;
import main.java.Items.Item;
import main.java.Items.Loot;

import java.util.List;

public class Creature extends Organism {

    private final int EXPERIENCE_IN;
    private Location location;

    public Creature(String name, float strength, float stamina, float agility, float intellect, int lvl, int gold, GameClass gameClass, int EXPERIENCE_IN, Location location) {
        super(name, strength, stamina, agility, intellect, lvl, gold, gameClass);
        this.EXPERIENCE_IN = EXPERIENCE_IN;
        this.location = location;
        getBackPack().addListOfLootToBackPack(Loot.getLoot(name));
        setHealthMaxValue();
        setManaMaxValue();
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }



    public int getEXPERIENCE_IN() {return EXPERIENCE_IN;}

    @Override public String findQuestItemInCreaturesBackPack() {return getBackPack().findQuestItem();}
    @Override public Item addQuestItemToPlayerFromCreature() {return getBackPack().getQuestItem();}

}
