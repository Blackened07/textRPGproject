package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Services.*;

import java.util.*;

public class Events implements Printable, GameScanner {
    private final String eventName;
    private final Location LOCATION;
    private boolean isEventActive = false;
    StoryReader story;
    Organism player;
    Dialogue dialogue;
    private final int START_EVENT_NUMBER = 0;
    private final String START_DIALOGUE;
    private final String END_DIALOGUE;
    private final String TRADE;
    private final String THANKS;
    public String[][] textAndCommands;
    private Map<String, List<String>> eventTextsAndCommands = new HashMap<>();

    public Events(String eventName, Location LOCATION, StoryReader story) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.story = story;
        this.dialogue = new Dialogue();
        this.START_DIALOGUE = phraseSetter(25, 1);
        this.END_DIALOGUE = phraseSetter(30, 1);
        this.TRADE = phraseSetter(48, 1);
        this.THANKS = phraseSetter(56, 1);
    }
    /// WORK WITH VARS
    public int getSTART_EVENT_NUMBER() {return START_EVENT_NUMBER;}
    public String getSTART_DIALOGUE() {return START_DIALOGUE;}
    public String getEND_DIALOGUE() {return END_DIALOGUE;}
    public String getTRADE() {return TRADE;}
    public String getTHANKS() {return THANKS;}
    public boolean isEventActive() {return isEventActive;}
    public void setEventActive(boolean eventActive) {this.isEventActive = eventActive;}
    public String getEventName() {return eventName;}

    /// METHODS
    protected String phraseSetter (int textIndex, int commandIndex) {return story.phrasesBuilder(textIndex, commandIndex);}
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {};
    public void setCurrentEvent(String s){};
    protected int getEventNumber(String s) {return Integer.parseInt(s.replaceAll(String.valueOf(PATTERN), ""));}
    public Organism startGameEvent(Scanner sc) throws InvalidCommandException {return player;}
    protected int commandParser(int i, int currentEvent, Organism player, Scanner sc) throws InvalidCommandException {return 0;}
}
