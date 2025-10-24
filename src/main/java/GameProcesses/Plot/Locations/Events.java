package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Services.*;

import java.util.*;

public abstract class Events implements GameScanner, Printable {
    private final String eventName;
    /// call event with his name
    private final Location LOCATION;
    private final int START_EVENT_NUMBER = 0;
    private final String START_DIALOGUE = phraseSetter(25, 1);
    private final String END_DIALOGUE = phraseSetter(30, 1);
    private final String TRADE = phraseSetter(48, 1);
    private final String THANKS = phraseSetter(56, 1);
    private boolean isEventActive = false;
    StoryReader story;
    Organism player;
    Dialogue dialogue;
    public String[][] textAndCommands;

    public Events(String eventName, Location LOCATION, StoryReader story) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.story = story;
        this.dialogue = new Dialogue();
    }
    /// WORK WITH VARS
    public int getSTART_EVENT_NUMBER() {return START_EVENT_NUMBER;}
    public String getSTART_DIALOGUE() {return START_DIALOGUE;}
    public boolean isEventActive() {return isEventActive;}
    public void setEventActive(boolean eventActive) {this.isEventActive = eventActive;}
    public String getEventName() {return eventName;}
    /// METHODS
    protected String phraseSetter (int textIndex, int commandIndex) {return story.phrasesBuilder(textIndex, commandIndex);}
    public void startEvent(Organism player, Scanner sc) throws GameExceptions {};
    public void setCurrentEvent(String s){};
    protected int getEventNumber(String s) {return Integer.parseInt(s.replaceAll("\\D+", ""));}
    public Organism startGameEvent(Scanner sc) throws GameExceptions {return player;}
    protected int commandParser(int i, int currentEvent, Organism player, Scanner sc) throws GameExceptions {return 0;}
}
