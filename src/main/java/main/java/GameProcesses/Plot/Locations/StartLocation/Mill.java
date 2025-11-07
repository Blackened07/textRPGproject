package main.java.GameProcesses.Plot.Locations.StartLocation;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Dialogue;
import main.java.GameProcesses.Fight;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.CreatureGenerator;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.RandomNumberGenerator;

import java.util.Scanner;

import static main.java.GameProcesses.Plot.Locations.Location.NORTH_FROM_CROSSROADS;

public class Mill extends Events implements RandomNumberGenerator, Fight {

    Events roadToWest;
    Events fatDialogue;
    Events lake;
    Events roadToNorth;
    CreatureGenerator cg;

    private final String FAT_DIALOGUE = "FatDialogue";
    private final String MILL_AROUND = "MillAround";
    private final String FIELDS = "Fields";

    public Mill(String eventName, Location LOCATION, Dialogue dialogue, Events roadToWest, Events lake, Events roadToNorth) {
        super(eventName, LOCATION, dialogue);
        this.roadToWest = roadToWest;
        this.lake = lake;
        this.roadToNorth = roadToNorth;
        cg = new CreatureGenerator();
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        fight(player, cg.getCreature(NORTH_FROM_CROSSROADS), sc, this);
        printEventTextAndCommands(getSTART_EVENT(), getDialogue());
        setEventActive(true);
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;

        while (isEventActive()) {
            userInput = gameScanner(sc, getDialogue().getInnerListSize(getCurrentEvent()), player, this);

            if(checkCurrentEventAndCommandEqualsForDialogue(FAT_DIALOGUE, this)) {
                fatDialogue.startEvent(player, sc);
                setCurrentEvent(getSTART_EVENT());
                break;
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(MILL_AROUND, this)) {
                switch (userInput) {
                    case 1 -> {
                        if (player.findItemWithName("MillChestKey")) {
                            print("Open Chest");
                        } else print("заперто");
                    }
                    case 2 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(FIELDS, this)) {
                switch (userInput) {
                    case 1 -> {
                        lake.startEvent(player, sc);
                    }
                    case 2 -> {
                        fight(player, cg.getCreature(NORTH_FROM_CROSSROADS,  2, 3, 4), sc, this);
                        roadToNorth.startEvent(player, sc);
                    }
                    case 3 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> {setCurrentEvent(FAT_DIALOGUE);}
                    case 2 -> {setCurrentEvent(MILL_AROUND);}
                    case 3 -> {
                        fight(player, cg.getCreature(NORTH_FROM_CROSSROADS, 1, 2, 3), sc, this);
                        setCurrentEvent(FIELDS);}
                    case 4 -> {roadToWest.startEvent(player, sc);}
                }
            }
            printEventTextAndCommands(getCurrentEvent(), getDialogue());
        }
    }
    private void setFatDialogue(){}

}
