package main.java.GameProcesses.Plot.Locations.StartLocation;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class LakeSilver extends Events {

    Dialogue dialogue;
    Events mill;
    Events roadToNorth;

    public LakeSilver(String eventName, Location LOCATION, Dialogue dialogue, Events mill, Events roadToNorth) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.mill = mill;
        this.roadToNorth = roadToNorth;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        setEventActive(true);
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;

        while (isEventActive()) {
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()), player);

            switch (userInput) {
                case 1 -> mill.startEvent(player, sc);
                case 2 -> roadToNorth.startEvent(player, sc);
            }

            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }
}
