package main.java.Characters;

import main.java.Items.Item;

public class NPC extends Organism implements StatsCalculator{

    Equipment inventory;
    BackPack backPack;

    public NPC(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.inventory = new Equipment();
        this.backPack = new BackPack();
        setHealthMaxValue();
        setManaMaxValue();
    }

    @Override
    public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = backPack.showAllItemsForTrade(this).append("\nЗолото торговца: ").append(getGold());
        return sb.toString();
    }

}
