package main.java.Items;

import main.java.Items.Armor.Features;

public class Item implements Features {
    private final String name;
    private final int cost;
    private final int weight;
    private Types type;

    public Item(String name, int cost, int weight, Types type) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.type = type;
    }

    @Override
    public String getFeatures() {
        return "";
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public int getCost() {return cost;}

    public int getWeight() {return weight;}

    @Override
    public String toString() {
        return name;
    }

}
