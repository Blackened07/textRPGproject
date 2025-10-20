package main.java.Characters;

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

}
