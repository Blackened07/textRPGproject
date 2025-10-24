package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class BoyDialogue extends Events {
    Dialogue dialogue;
    private String currentEvent;
    private int k;
    private Events silverShire;

    public BoyDialogue(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue, Events silverShire) {
        super(eventName, LOCATION, story);
        this.dialogue = dialogue;
        this.silverShire = silverShire;

        textAndCommands = new String[][]{
                {phraseSetter(26, 1), phraseSetter(27, 1), phraseSetter(28, 1), phraseSetter(29, 1), phraseSetter(30, 1)},
                {phraseSetter(31, 1), phraseSetter(32, 1)},
                {phraseSetter(33, 2), phraseSetter(35, 1)},
                {phraseSetter(36, 1), phraseSetter(37, 1)},
                {phraseSetter(38, 2), phraseSetter(40, 1)}     // q complete
        };
                dialogue.setKeysToTextsMapAndSetKeysToKeyList(getEventName(), textAndCommands.length);
                dialogue.addTextsToListsInMap(textAndCommands);

    }

    public String getCurrentEvent() {return currentEvent;}
    @Override public void setCurrentEvent(String currentEvent) {this.currentEvent = currentEvent;}

    @Override
    public void startEvent(Organism player, Scanner sc) {
        setCurrentEvent(dialogue.getKey(getSTART_EVENT_NUMBER()));
        dialogue.printEventTextAndCommands(getSTART_EVENT_NUMBER());
        setEventActive(true);
        eventSwitcher(sc, player);
    }

    private void eventSwitcher(Scanner sc, Organism player) {
        int i;
        while (isEventActive()) {
            i = Integer.parseInt(gameScanner(sc));
            k = commandParser(i, getEventNumber(getCurrentEvent()), player, sc);
            if (getEventNumber(getCurrentEvent()) == 0 && k == 4) {
                setCurrentEvent(dialogue.getKey(getSTART_EVENT_NUMBER()));
                setEventActive(false);
                silverShire.startEvent(player, sc);
                break;
            }
            setCurrentEvent(dialogue.getKey(k));
            dialogue.printEventTextAndCommands(k);
            setK();
        }
    }

    @Override
    protected int commandParser(int i, int currentEvent, Organism player, Scanner sc) {
        if (currentEvent == getSTART_EVENT_NUMBER()) {
            return i;
        } else if (currentEvent == 1 && i == 1 || currentEvent == 3 && i == 1) {
            startEvent(player, sc);
        } else if (currentEvent == 2 && i == 1) {
            System.out.println("Quest accepted");
            startEvent(player, sc);
        } else if (currentEvent == 0 && i == 4) {
            return 0;
        }
        return -1;
    }

    public void setK() {this.k = 0;}
}
