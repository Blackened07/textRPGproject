package main.java.Characters;

public class NPC extends Organism implements StatsCalculator{
    public NPC(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
    }


}
