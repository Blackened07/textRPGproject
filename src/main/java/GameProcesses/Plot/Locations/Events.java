package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.Printable;
import main.java.GameProcesses.Services.StoryReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Events implements Printable, GameScanner {
    private final String eventName;
    /// call event with his name
    private final Location LOCATION;
    StoryReader story;
    Organism player;

    private List<String> eventsTexts = new ArrayList<>();;

    public Events(String eventName, Location LOCATION, StoryReader story ) {
        this.eventName = eventName;
        this.LOCATION = LOCATION;
        this.story = story;
    }

    protected void setEventsTexts(String s) {
        eventsTexts.add(s);
    }
    public String getEventsText(int index) {
        return eventsTexts.get(index);
    }

    public abstract void printEvent(int index);

    public Organism startEvent (Scanner sc) {return player;}
}
