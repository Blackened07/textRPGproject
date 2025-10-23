package main.java.GameProcesses.Plot.Locations.StartLocation;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Arrays;
import java.util.Scanner;

public class StartVillage extends Events {
    Dialogue dialogue;
    private String currentEvent;

    public StartVillage(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue) {
        super(eventName, LOCATION, story);
        this.dialogue = dialogue;
        textAndCommands = new String[][]{{phraseSetter(17, 3), phraseSetter(20, 1), phraseSetter(21, 1), phraseSetter(22,1), phraseSetter(23,1)},
                {phraseSetter(24, 1), phraseSetter(25, 1)},
                {"StartTavernDialogue", "Tavern"},
                {"StartSarayEv", "Saray"},
                {"StartToCrossroads", "Cross"},
                {phraseSetter(26, 1), phraseSetter(27, 1), phraseSetter(28, 1), phraseSetter(29, 1), phraseSetter(30, 1)},
                {phraseSetter(31, 1), phraseSetter(32, 1)},
                {phraseSetter(33, 2), phraseSetter(35, 1)},
                {phraseSetter(36, 1), phraseSetter(37, 1)},
                {phraseSetter(38, 2), phraseSetter(40, 1)} //q complete
        };
        dialogue.setKeysToTextsMapAndSetKeysToKeyList(getEventName(), 10);
        dialogue.addTextsToListInMap(textAndCommands.length, textAndCommands);

    }

    @Override public String getEventName() {return super.getEventName();}

    @Override public void setCurrentEvent(String s) {currentEvent = s;}
    public String getCurrentEvent() {return currentEvent;}

    @Override
    public void startEvent(Organism player, Scanner sc) {
        setCurrentEvent(dialogue.getKey(0));
        dialogue.printEventTextAndCommands(0);
        eventSwitcher(sc, getCurrentEvent());
    }

    private void eventSwitcher(Scanner sc, String currentEvent) {
        int i = Integer.parseInt(gameScanner(sc));
        switch (i) {
            case 1 -> {
                commandParser(i, currentEvent);
                startNextEvent(i);
                eventSwitcher(sc, getCurrentEvent());
            }
            case 2 -> {
                commandParser(i, currentEvent);
                startNextEvent(i);
                eventSwitcher(sc, getCurrentEvent());
            }
            case 3 -> {
                commandParser(i, currentEvent);
                startNextEvent(i);
                eventSwitcher(sc, getCurrentEvent());
            }
            case 4 -> {
                commandParser(i, currentEvent);
                startNextEvent(i);
                eventSwitcher(sc, getCurrentEvent());
            }
        }
    }

    private void startNextEvent(int index) {
        dialogue.printEventTextAndCommands(index);
        /*setCurrentEvent(index);*/
    }


}
