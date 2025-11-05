package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Trade;
import main.java.GameProcesses.Services.*;
import main.java.GameProcesses.Services.UseItemsFromBackPack;

import java.util.*;

public abstract class Events implements GameScanner, PrintableInterfaces, Trade, UseItemsFromBackPack {
    private final String eventName;
    private final Location LOCATION;
    private String currentEvent;
    private boolean isEventActive = false;
    private Organism player;
    private Dialogue dialogue;
    private final String START_EVENT = "StartEvent";
    private final String START_QUEST = "StartQuest";
    private final String QUEST_COMPLETE = "QuestComplete";
    private final String PHRASE_1 = "Phrase1";
    private final String PHRASE_2 = "Phrase2";
    private final String PHRASE_3 = "Phrase3";
    private final String PHRASE_4 = "Phrase4";
    private final String PHRASE_5 = "Phrase5";
    private final String PHRASE_6 = "Phrase6";
    private final String CHECK = "Check";
    private final String WAY = "Way";
    private final String FIGHT = "Fight";

    public Events(String eventName, Location LOCATION, Dialogue dialogue) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.dialogue = dialogue;
    }

    public Dialogue getDialogue() {return dialogue;}

    /// WORK WITH VARS
    protected String getSTART_EVENT() {return START_EVENT;}
    protected String getSTART_QUEST() {return START_QUEST;}
    protected String getQUEST_COMPLETE() {return QUEST_COMPLETE;}
    protected String getPHRASE_1() {return PHRASE_1;}
    protected String getPHRASE_2() {return PHRASE_2;}
    protected String getPHRASE_3() {return PHRASE_3;}
    protected String getPHRASE_4() {return PHRASE_4;}
    protected String getPHRASE_5() {return PHRASE_5;}
    protected String getPHRASE_6() {return PHRASE_6;}
    protected String getCHECK() {return CHECK;}
    protected String getWAY() {return WAY;}

    protected boolean isEventActive() {return isEventActive;}
    protected void setEventActive(boolean eventActive) {this.isEventActive = eventActive;}

    protected void setCurrentEvent(String s){this.currentEvent = s;};
    protected String getCurrentEvent() {return currentEvent;}


    /// METHODS
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {};
    public Organism startGameEvent(Scanner sc) throws InvalidCommandException {return player;}
    protected boolean checkCurrentEventAndCommandEqualsForDialogue(String event, Events events) {
        return events.getCurrentEvent().equals(event);
    }
    protected void printEventTextAndCommands(String key, Dialogue dialogue) {
        for (int i = 0; i < dialogue.getEventsTextsAndCommands().get(key).size(); i++) {
            System.out.println(dialogue.getEventsTextsAndCommands().get(key).get(i));
        }
    }
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {}
}
