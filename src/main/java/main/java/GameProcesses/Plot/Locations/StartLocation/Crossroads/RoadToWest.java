package main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartLocation.Mill;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class RoadToWest extends Events {

    Events crossroads;
    Dialogue dialogue;
    Events mill;

    private final String PATH_NAME_MILL_EVENT = "story/Mill.json";

    public RoadToWest(String eventName, Location LOCATION, Dialogue dialogue, Events crossroads, Events mill) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.crossroads = crossroads;

        this.mill = mill;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        setEventActive(true);
        /*setMillEvent();*/
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;

        while (isEventActive()) {
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()));
            switch (userInput) {
                case 1 -> System.out.println("Город - городские ворота");
                case 2 -> System.out.println("Река Ивент перейти реку");
                case 3 -> mill.startEvent(player, sc);
                case 4 -> crossroads.startEvent(player, sc);
            }
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }

   /* private void setMillEvent () {
        mill = new Mill("Mill", Location.MILL, new Dialogue(PATH_NAME_MILL_EVENT), this, lake, roadToNorth);
    }*/
}
