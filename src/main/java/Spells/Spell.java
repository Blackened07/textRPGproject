package main.java.Spells;

public class Spell {
    private float spellDamage;
    private float manaCost;

    public Spell(float spellDamage, float manaCost) {
        this.spellDamage = spellDamage;
        this.manaCost = manaCost;
    }

    public float getSpellDamage() {
        return spellDamage;
    }

    public float getManaCost() {
        return manaCost;
    }
}
