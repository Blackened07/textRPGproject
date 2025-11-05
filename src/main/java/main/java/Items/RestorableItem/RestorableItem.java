package main.java.Items.RestorableItem;

import main.java.Items.Item;
import main.java.Items.Types;

public class RestorableItem extends Item {

    private final float RESTORE;

    public RestorableItem(String name, int cost, int weight, Types type, float RESTORE) {
        super(name, cost, weight, type);
        this.RESTORE = RESTORE;
    }

    public float getRESTORES_HEALTH() {return RESTORE;}

}
