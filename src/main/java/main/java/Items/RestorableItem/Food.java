package main.java.Items.RestorableItem;

import main.java.Items.Item;
import main.java.Items.Types;

public class Food extends RestorableItem {

    public Food(String name, int cost, int weight, Types type, float RESTORE) {
        super(name, cost, weight, type, RESTORE);
    }
}
