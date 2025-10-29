package main.java.Characters;

import main.java.Items.Item;

public class NPC extends Organism implements StatsCalculator{

    Equipment inventory;
    BackPack backPack;

    public NPC(String name, float strength, float stamina, float agility, float intellect, int experience, int gold) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.inventory = new Equipment();
        this.backPack = new BackPack();
    }

    /** BASE STATS */
    @Override
    public float getStrength() {return super.getStrength() + getStat(inventory, STRENGTH_INDEX);}
    public float getStamina() {return super.getStamina() + getStat(inventory, STAMINA_INDEX);}
    public float getAgility() {return super.getAgility() + getStat(inventory, AGILITY_INDEX);}
    public float getIntellect() {return super.getIntellect() + getStat(inventory, INTELLECT_INDEX);}

    /** HEALTH AND MANA */
    @Override public float getHealthMaxValue() {return getBASE_HEALTH() + getStamina();}
    @Override public float getManaMaxValue() {return getBASE_MANA() + getIntellect();}

    /** SECONDARY STATS/FEATURES */
    @Override public float getAttackPower() {return getStrength() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    @Override public float getSpellPower() {return getIntellect() / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}
    @Override public float getEvasion() {return getAgility() / evasionCoefficient;}
    @Override public float getSpellResistance() {return (getIntellect() + getStamina()) / spellResistanceCoefficient;}
    @Override public float getAttackSpeed() {return ((getAgility() + getStamina()) / attackSpeedCoefficient) / attackPowerANDSpellPowerANDAttackSpeedCoefficient;}

    @Override public void addToBackPack(Item item) {backPack.addToBackPack(item);}
    @Override
    public String showItemsFromBackPackForTrade() {
        StringBuilder sb;
        sb = backPack.showAllItemsForTrade(this).append("\nЗолото торговца: ").append(getGold());
        return sb.toString();
    }
    @Override public Item getFromBackPack(String name) {return backPack.getFromBackPack(name);}
    @Override public Item getFromBackPackWithIndex(int index) {return backPack.getFromBackPack(index);}
    @Override public int getSize() {return backPack.getSize();}
    @Override public int getGold() {return super.getGold();}
    @Override public void setGold(int gold) {super.setGold(gold);}
    @Override public void removeFromBAckPack(int index) {backPack.remove(index);}
}
