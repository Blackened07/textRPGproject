package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Services.*;

import java.util.*;

public abstract class Events implements GameScanner {
    private final String eventName;
    private final Location LOCATION;
    private String currentEvent;
    private boolean isEventActive = false;
    StoryReader story;
    Organism player;
    Dialogue dialogue;
    private final String START_EVENT = "StartEvent";
    private final String SWITCH_EVENT = "SwitchEvent";
    private final String START_DIALOGUE = "1 - Начать диалог";
    private final String END_DIALOGUE = "4 - Закончить диалог";
    private final String START_QUEST = "StartQuest";
    private final String QUEST_COMPLETE = "QuestComplete";
    private final String START_TRADE = "StartTrade";
    private final String END_DIALOGUE_WITH_THANKS = "Thanks";
    private final String PHRASE_1 = "Phrase1";
    private final String PHRASE_2 = "Phrase2";
    private final String EXIT_IN_EVENT = "Выйти";

    public Events(String eventName, Location LOCATION, StoryReader story) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.story = story;
    }
    /// WORK WITH VARS
    public String getSTART_EVENT() {return START_EVENT;}
    public String getSWITCH_EVENT() {return SWITCH_EVENT;}
    public String getSTART_DIALOGUE() {return START_DIALOGUE;}
    public String getEND_DIALOGUE() {return END_DIALOGUE;}
    public String getSTART_QUEST() {return START_QUEST;}
    public String getQUEST_COMPLETE() {return QUEST_COMPLETE;}
    public String getEXIT_IN_EVENT() {return EXIT_IN_EVENT;}
    public String getSTART_TRADE() {return START_TRADE;}
    public String getEND_DIALOGUE_WITH_THANKS() {return END_DIALOGUE_WITH_THANKS;}
    public String getPHRASE_1() {return PHRASE_1;}
    public String getPHRASE_2() {return PHRASE_2;}

    public boolean isEventActive() {return isEventActive;}
    public void setEventActive(boolean eventActive) {this.isEventActive = eventActive;}

    public void setCurrentEvent(String s){this.currentEvent = s;};
    public String getCurrentEvent() {return currentEvent;}

    public Dialogue getDialogue() {return dialogue;}

    /// METHODS
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {};
    public Organism startGameEvent(Scanner sc) throws InvalidCommandException {return player;}
    protected boolean checkCurrentEventAndCommandEqualsForDialogue(String event, int userInput) {
        return getCurrentEvent().equals(event) && dialogue.getTextFromMAp(getCurrentEvent(), userInput).equals(event);
    }
    public void printEventTextAndCommands(String key, Dialogue dialogue) {
        for (int i = 0; i < dialogue.getEventsTextsAndCommands().get(key).size(); i++) {
            System.out.println(dialogue.getEventsTextsAndCommands().get(key).get(i));
        }
    }
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {}
}
