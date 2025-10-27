package main.java.Characters;

import main.java.Items.Armor.Armors;
import main.java.Items.Item;
import main.java.Items.Weapon.Weapon;
import main.java.Spells.Spell;

public class Player extends Organism implements StatsCalculator {
    GameClass gameClass;
    Weapon weapon;
    Inventory inventory;
    BackPack backPack;
    SpellBook spellBook;

    public Player(String name, float strength, float stamina, float agility, float intellect, int experience, int gold, GameClass gameClass, Inventory inventory, BackPack backPack, SpellBook spellBook, Weapon weapon) {
        super(name, strength, stamina, agility, intellect, experience, gold);
        this.gameClass = gameClass;
        this.inventory = inventory;
        this.backPack = backPack;
        this.spellBook = spellBook;
        this.weapon = weapon;
    }
    /** BASE STATS */
    @Override
    public float getStat(Inventory inventory, int statIndex) {return StatsCalculator.super.getStat(inventory, statIndex);}
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

    /** EXP AND LEVEL*/
    @Override public int getExperience() {return super.getExperience();}
    @Override public int getLevel() {return super.getLevel();}
    @Override public void setLevel() {super.setLevel();} //afterLvlUpInvokeThis
    @Override public void setExperience(int experience) {super.setExperience(experience);} //for fights and quests
    public void checkExp() {if (ExperienceCalculator.checkExp(getExperience())) setLevel();} //final of fight or quest
    /***/

    private void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    /** BUSINESS*/
    public void addToInventoryA(Armors a) {
        inventory.addToInventoryA(a);
        System.out.println("You receive item: " + a + " " + a.getFeatures());
    }
    public void addToInventoryW(Weapon w) {
        inventory.addToInventoryW(w);
        System.out.println("You receive item: " + w + " " + w.getFeatures());
    }

    public void changeWeapon(Weapon weapon) {
        addToBackPack(this.weapon);
        setWeapon(weapon);
        addToInventoryW(weapon);
    }

    public void addToBackPack(Item item) {
        backPack.addToBackPack(item);
        System.out.println("You receive item: " + item);
    }

    public void addToSpellBook(Spell spell) {
        spellBook.addToSpellBook(spell);
    }

}
