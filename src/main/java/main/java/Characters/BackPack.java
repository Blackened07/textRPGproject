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

    public void addToBackPack(Item item) {backPack.add(item);}
    public void addListOfLootToBackPack(List<Item> items) {
        backPack.addAll(items);
    }

    /**Return Object**/
    public Item getFromBackPack(String name) {
        for (Item i : backPack) {
            if (i.getName().equals(name)) return i;
        }
        return null;
    }
    public Item getQuestItem () {
        for (Item i : backPack) {
            if (i.getType().equals(Types.QUEST_ITEM)) return i;
        }
        return null;
    }
    public Item getFromBackPack(int index) {return backPack.get(index);}

    /**Return Boolean*/
    public boolean findItemWithName (String name) {
        for (Item i : backPack) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }

    /**Return TypeOfItem*/
    public Types getItemType (int index) {return backPack.get(index).getType();}

    /**Remove Methods*/

    public void remove(int index) {backPack.remove(index);}
    public void removeByName(String name) {
        for (int i = 0; i < backPack.size(); i++) {
            if (backPack.get(i).getName().equals(name)){
                backPack.remove(i);
                break;
            }
        }
    }

    /**Return String or Int**/
    public int sumOfWeightOfItemsInBackPack () {
        return backPack.stream().
                mapToInt(Item::getWeight).
                sum();
    }
    public String findQuestItem () {
        for (Item i : backPack) {
            if (i.getType().equals(Types.QUEST_ITEM)) return i.getName();
        }
        return "";
    }
    public int getSize() {return backPack.size();}

    /**Show Methods*/
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
}
