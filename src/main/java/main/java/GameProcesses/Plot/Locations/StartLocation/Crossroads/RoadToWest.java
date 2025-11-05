package main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class RoadToWest extends Events {
    private Events crossroads;
    private Events mill;

    public RoadToWest(String eventName, Location LOCATION, Dialogue dialogue, Events crossroads, Events mill) {
        super(eventName, LOCATION, dialogue);
        this.crossroads = crossroads;
        this.mill = mill;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), getDialogue());
        setEventActive(true);
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;

        while (isEventActive()) {
            userInput = gameScanner(sc, getDialogue().getInnerListSize(getCurrentEvent()), player, this);
            switch (userInput) {
                case 1 -> System.out.println("Город - городские ворота");
                case 2 -> System.out.println("Река Ивент перейти реку");
                case 3 -> mill.startEvent(player, sc);
                case 4 -> crossroads.startEvent(player, sc);
            }
            printEventTextAndCommands(getCurrentEvent(), getDialogue());
        }
    }

}
