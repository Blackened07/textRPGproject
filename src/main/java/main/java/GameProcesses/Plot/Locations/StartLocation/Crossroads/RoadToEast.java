package main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class RoadToEast extends Events {
    private Events crossroads;

    public RoadToEast(String eventName, Location LOCATION, Dialogue dialogue, Events crossroads) {
        super(eventName, LOCATION, dialogue);
        this.crossroads = crossroads;
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
                case 1 -> System.out.println("Иди дальше - развилка север - хайлвл лесболото, восток - болото + кладбище, юг - путь к побережью мидл лвл");
                case 2 -> System.out.println("Загадка-квест");
                case 3 -> System.out.println("Загадка-квест на хайлвл");
                case 4 -> crossroads.startEvent(player, sc);
            }
        }
    }
}
