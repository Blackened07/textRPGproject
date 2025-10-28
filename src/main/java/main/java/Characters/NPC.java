package main.java.Characters;

import main.java.Items.Item;

public class NPC extends Organism implements StatsCalculator{

    Inventory inventory;
    BackPack backPack;

    public NPC(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.inventory = new Inventory();
        this.backPack = new BackPack();
    }

    @Override
    public void addToBackPack(Item item) {
        backPack.addToBackPack(item);
    }

    @Override
    public String showItemsFromBackPack() {
        StringBuilder sb = new StringBuilder();
        sb = backPack.showAllItems().append("\nЗолото торговца: ").append(getGold());
        return sb.toString();
    }

    @Override
    public Item getFromBackPack(String name) {
        return backPack.getFromBackPack(name);
    }

    @Override
    public Item getFromBackPackWithIndex(int index) {
        return backPack.getFromBackPack(index);
    }

    @Override
    public int getSize() {
        return backPack.getSize();
    }

    @Override
    public int getGold() {
        return super.getGold();
    }

    @Override
    public void setGold(int gold) {
        super.setGold(gold);
    }

    @Override
    public void removeFromBAckPack(int index) {
        backPack.remove(index);
    }
}
