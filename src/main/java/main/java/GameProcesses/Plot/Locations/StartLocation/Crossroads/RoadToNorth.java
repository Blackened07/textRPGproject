package main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads;

import main.java.Characters.Organism;
import main.java.GameProcesses.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class RoadToNorth extends Events {

    Dialogue dialogue;
    Events crossroads;
    Events lake;
    Events mill;


    private final String PATHWAY = "Pathway";

    public RoadToNorth(String eventName, Location LOCATION, Dialogue dialogue, Events crossroads, Events lake, Events mill) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.crossroads = crossroads;
        this.lake = lake;
        this.mill = mill;
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
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()), player, this);

            if (checkCurrentEventAndCommandEqualsForDialogue(getWAY(), this)) {
                switch (userInput) {
                    case 1 -> System.out.println("Forest");
                    case 2 -> System.out.println("East");
                    case 3 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getCHECK(), this)) {
                //add ring to world map;
                switch (userInput) {
                    case 1 -> System.out.println("Ring + same event");
                    case 2 -> {
                        startEvent(player, sc);
                        System.out.println("No ring + same Event");
                    }
                    //fight with bandits
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(PATHWAY, this)) {
                //random fight and random (- stats)
                switch (userInput) {
                    case 1 -> {lake.startEvent(player, sc);}
                    case 2 -> mill.startEvent(player, sc);
                    case 3 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(getWAY());
                    case 2 -> {
                        setCurrentEvent(getCHECK());
                        System.out.println("Вероятность нападения зверя или бандита. Осмотр телеги - ring");
                    }
                    case 3 -> setCurrentEvent(PATHWAY);
                    case 4 -> crossroads.startEvent(player, sc);
                }
            }
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }
}
