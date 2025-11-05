package main.java.Characters;
import main.java.Items.Loot;

public class NPC extends Organism implements StatsCalculator{

    public NPC(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        getBackPack().addListOfLootToBackPack(Loot.getForVendor(name));
        setHealthMaxValue();
        setManaMaxValue();
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }

    @Override public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = getBackPack().showAllItemsForTrade(this).append("\nЗолото торговца: ").append(getGold());
        return sb.toString();
    }
}
