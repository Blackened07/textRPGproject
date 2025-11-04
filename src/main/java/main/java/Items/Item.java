package main.java.Items;

public class Item {
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

    public String getName() {
        return name;
    }
    public Types getType() {
        return type;
    }
    public int getCost() {return cost;}
    public int getWeight() {return weight;}

    public float getRESTORES_HEALTH(){return 0;};

    @Override
    public String toString() {
        return name;
    }
    public String getFOR_QUEST() {
        return "";
    }
}
