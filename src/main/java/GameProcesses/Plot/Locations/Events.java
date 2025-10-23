package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Services.*;

import java.util.*;

public abstract class Events implements GameScanner, CommandReader, Printable {
    private final String eventName;
    /// call event with his name
    private final Location LOCATION;
    StoryReader story;
    Organism player;
    Dialogue dialogue;
    public String[][] textAndCommands;

    public Events(String eventName, Location LOCATION, StoryReader story ) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.story = story;
        this.dialogue = new Dialogue();
    }
    protected String phraseSetter (int textIndex,int commandIndex) {return story.phrasesBuilder(textIndex, commandIndex);}
    public Organism startGameEvent(Scanner sc) {return player;}
    public void startEvent(Organism player, Scanner sc) {};
    public void setCurrentEvent(String s){};
    public String getEventName() {return eventName;}
}
