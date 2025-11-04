package main.java.Items.Food;

import main.java.Items.Item;
import main.java.Items.Types;

public class Food extends Item {

    private final float RESTORES_HEALTH;
    public Food(String name, int cost, int weight, Types type, float RESTORES_HEALTH) {
        super(name, cost, weight, type);
        this.RESTORES_HEALTH = RESTORES_HEALTH;
    }

    public float getRESTORES_HEALTH() {return RESTORES_HEALTH;}

}
