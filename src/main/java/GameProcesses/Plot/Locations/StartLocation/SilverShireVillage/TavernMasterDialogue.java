package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.StoryReader;

public class TavernMasterDialogue extends Events {

    Dialogue dialogue;
    private String currentEvent;
    private int k;
    private StoryReader story;

    public TavernMasterDialogue(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue) {
        super(eventName, LOCATION, story);
        this.dialogue = dialogue;
    }
}
