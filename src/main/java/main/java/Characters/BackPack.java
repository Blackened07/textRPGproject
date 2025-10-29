package main.java.Characters;

import main.java.Items.Food.Food;
import main.java.Items.Item;
import main.java.Items.Types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BackPack {
    private List<Item> backPack;

    public BackPack() {
        this.backPack = new ArrayList<>();
    }

    public void addToBackPack(Item item) {
        backPack.add(item);
    }

    public String findItemCanRestore(Item item) {
        for (Item i : backPack) {
            switch (item.getType()) {
                case FOOD -> {
                    Food food = (Food) item;
                    return "Restores " + food.getRESTORES_HEALTH() + " health";
                }
                case POTION -> {
                    return "Potion";
                }

            }
        }
        return "";
    }
    public boolean findItemWithName (String name) {
        for (Item i : backPack) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }
    public Item getFromBackPack(String name) {
        for (Item i : backPack) {
            if (i.getName().equals(name)) return i;
        }
        return null;
    }
    public Item getFromBackPack(int index) {
        return backPack.get(index);
    }
    public void remove(int index) {
        backPack.remove(index);
    }
    public int sumOfWeightOfItemsInBackPack () {
        return backPack.stream().
                mapToInt(Item::getWeight).
                sum();
    }

    public StringBuilder showAllItemsForTrade(Organism o) {
        StringBuilder sb = new StringBuilder("Товар " + o.getNAME() + "'a\n");
        int count;
        for (int i = 0; i < backPack.size(); i++) {
            count = i + 1;
            sb.append(count).
                    append(" - ").append(backPack.get(i).getName()).
                    append(": цена - ").append(backPack.get(i).getCost()).
                    append("; вес - ").append(backPack.get(i).getWeight()).append("\n");
        }
        return sb;
    }
    public StringBuilder showAllItems() {
        StringBuilder sb = new StringBuilder();
        int count;
        for (int i = 0; i < backPack.size(); i++) {
            count = i + 1;
            sb.append(count).
                    append(" - ").append(backPack.get(i).getName()).
                    append(": цена - ").append(backPack.get(i).getCost()).
                    append("; вес - ").append(backPack.get(i).getWeight()).append("\n");
        }
        return sb;
    }

    public int getSize() {
        return backPack.size();
    }
    public Types getItemType (int index) {
        return backPack.get(index).getType();
    }
}
