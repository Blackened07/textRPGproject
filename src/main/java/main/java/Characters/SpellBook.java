package main.java.Characters;

import main.java.Spells.Spell;

import java.util.HashSet;
import java.util.Set;

public class SpellBook {
    private Set<Spell> spellBook;

    public SpellBook() {
        this.spellBook = new HashSet<>();
    }

    public void addToSpellBook(Spell spell) {
        spellBook.add(spell);
    }
}
