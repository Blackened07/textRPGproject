package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.GameExceptions;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class SilverShireVillage extends Events {
    Dialogue dialogue;
    private String currentEvent;
    private int k;
    private Events boyDialogue;
    private Events tavernMasterDialogue;
    private StoryReader story;

    public SilverShireVillage(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue) {
        super(eventName, LOCATION, story);
        this.story = story;
        this.dialogue = dialogue;
        textAndCommands = new String[][]{
                {phraseSetter(17, 3), phraseSetter(20, 1), phraseSetter(21, 1), phraseSetter(22, 1), phraseSetter(23, 1)},
                {phraseSetter(24, 1), getSTART_DIALOGUE()},
                {"StartTavernDialogue", "Tavern"},
                {"StartSarayEv", "Saray"},
                {"StartToCrossroads", "Cross"},
        };
        dialogue.setKeysToTextsMapAndSetKeysToKeyList(getEventName(), textAndCommands.length);
        dialogue.addTextsToListsInMap(textAndCommands);
    }

    @Override
    public void setCurrentEvent(String s) {
        this.currentEvent = s;
    }

    public String getCurrentEvent() {
        return currentEvent;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws GameExceptions {
        setCurrentEvent(dialogue.getKey(getSTART_EVENT_NUMBER()));
        dialogue.printEventTextAndCommands(getSTART_EVENT_NUMBER());
        setBoyDialogue(player, sc);
        setTavernMasterDialogue(player, sc);
        setEventActive(true);
        eventSwitcher(sc, getCurrentEvent(), player);
    }

    private void eventSwitcher(Scanner sc, String currentEvent, Organism player) throws GameExceptions {
        int i;
        while (isEventActive()) {
            i = Integer.parseInt(gameScanner(sc));
            k = commandParser(i, getEventNumber(currentEvent), player, sc);
            if (getEventNumber(getCurrentEvent()) == 1 && k == 1) {
                setCurrentEvent(dialogue.getKey(getSTART_EVENT_NUMBER()));
                setEventActive(false);
                boyDialogue.startEvent(player, sc);
                break;
            }
            setCurrentEvent(dialogue.getKey(k));
            dialogue.printEventTextAndCommands(k);
            setK();
        }
    }

    @Override
    protected int commandParser(int i, int currentEvent, Organism player, Scanner sc) {
        if (currentEvent == getSTART_EVENT_NUMBER()) return i;
        else return 1;
    }

    public void setK() {
        this.k = 0;
    }

    private void setBoyDialogue(Organism player, Scanner sc) {
        boyDialogue = new BoyDialogue("BoyDialogue", Location.SILVERSHIRE_VILLAGE, story, Dialogue.createDialogue(), this);

    }
    private void setTavernMasterDialogue(Organism player, Scanner sc) {
        tavernMasterDialogue = new TavernMasterDialogue("TavernMasterDialogue", Location.SILVERSHIRE_VILLAGE, story, Dialogue.createDialogue());
    }


}
