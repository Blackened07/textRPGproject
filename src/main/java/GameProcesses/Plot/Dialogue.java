package main.java.GameProcesses.Plot;

import main.java.Characters.Organism;
import main.java.GameProcesses.Services.Printable;
import main.java.GameProcesses.Services.PrintableInterfaces;

import java.util.*;

public class Dialogue implements Printable, PrintableInterfaces {
    public List<String> keys;
    private Map<String, List<String>> eventsTextsAndCommands;

    public Dialogue() {
        keys = new ArrayList<>();
        eventsTextsAndCommands = new HashMap<>();
    }

    public static Dialogue createDialogue() {
        return new Dialogue();
    }

    /// SET BLOCK
    public void setKeysToTextsMapAndSetKeysToKeyList(String keys, int numberOfKeys) {
        for (int i = 0; i < numberOfKeys; i++) {
            eventsTextsAndCommands.put(keys + i, new ArrayList<>());
        }
        this.keys.addAll(getKeys());
    }
    public String getKey(int index) {
        return keys.get(index);
    }
    public void addTextsToListsInMap(String[][] text) {
        for (int i = 0; i < text.length; i++) {
            for (int k = 0; k < text[i].length; k++) {
                eventsTextsAndCommands.get(getKey(i)).add(text[i][k]);
            }
        }
    }
    public List<String> getKeys() {
        return eventsTextsAndCommands.keySet()
                .stream()
                .sorted()
                .toList();
    }
    public int getInnerListSize(int index) {
        return eventsTextsAndCommands.get(getKey(index)).size();
    }

    /// PRINT BLOCK
    @Override
    public void printEventWithoutCommands(int index) {System.out.println(eventsTextsAndCommands.get(getKey(index)).getFirst());}
    @Override
    public void printEventTextAndCommands(int index) {
        for (int i = 0; i < getInnerListSize(index); i++) {
            System.out.println(eventsTextsAndCommands.get(getKey(index)).get(i));
        }
    }
    @Override
    public void features(Organism player) {
        PrintableInterfaces.super.features(player);
    }
}
