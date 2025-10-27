package main.java.Characters;

import main.java.Items.Food.Food;
import main.java.Items.Item;

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

    public static BackPack createBackPack () {
        return new BackPack();
    }

    public String findItem (Item item) {
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

}
