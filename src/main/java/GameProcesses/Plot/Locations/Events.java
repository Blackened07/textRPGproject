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
    public boolean isEventActive() {return isEventActive;}
    public void setEventActive(boolean eventActive) {this.isEventActive = eventActive;}
    public String getEventName() {return eventName;}
    /// METHODS
    protected String phraseSetter (int textIndex, int commandIndex) {return story.phrasesBuilder(textIndex, commandIndex);}
    public void startEvent(Organism player, Scanner sc) {};
    public void setCurrentEvent(String s){};
    protected int getEventNumber(String s) {return Integer.parseInt(s.replaceAll("\\D+", ""));}
    public Organism startGameEvent(Scanner sc) {return player;}
    protected int commandParser(int i, int currentEvent, Organism player, Scanner sc) {return 0;}
}
