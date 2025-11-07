package main.java.Characters;
import main.java.Items.Loot;

public class NPC extends Organism implements StatsCalculator{

    private final int EXPERIENCE_IN;

    public NPC(String name, float strength, float stamina, float agility, float intellect, int lvl, int gold, GameClass gameClass, int EXPERIENCE_IN) {
        super(name, strength, stamina, agility, intellect, lvl, gold, gameClass);
        this.EXPERIENCE_IN = EXPERIENCE_IN;
        getBackPack().addListOfLootToBackPack(Loot.getForVendor(name));
        setHealthMaxValue();
        setManaMaxValue();
        setCurrentHealth(getHealthMaxValue());
        setCurrentMana(getManaMaxValue());
    }

    public int getEXPERIENCE_IN() {return EXPERIENCE_IN;}

    @Override public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = getBackPack().showAllItemsForTrade(this).append("\nЗолото торговца: ").append(getGold());
        return sb.toString();
    }
}
