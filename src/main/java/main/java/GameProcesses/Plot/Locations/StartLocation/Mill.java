package main.java.GameProcesses.Plot.Locations.StartLocation;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class Mill extends Events {

    Dialogue dialogue;
    Events roadToWest;
    Events fatDialogue;
    Events lake;
    Events roadToNorth;

    private final String FAT_DIALOGUE = "FatDialogue";
    private final String MILL_AROUND = "MillAround";
    private final String FIELDS = "Fields";

    public Mill(String eventName, Location LOCATION, Dialogue dialogue, Events roadToWest, Events lake, Events roadToNorth) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.roadToWest = roadToWest;
        this.lake = lake;
        this.roadToNorth = roadToNorth;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        //StartEventWithRandomFight
        print("На вас напал кабан и в битве с ним вы одержали победу");
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
                    case 2 -> roadToNorth.startEvent(player, sc);
                    case 3 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> {setCurrentEvent(FAT_DIALOGUE);}
                    case 2 -> {setCurrentEvent(MILL_AROUND);}
                    case 3 -> {setCurrentEvent(FIELDS);}
                    case 4 -> {roadToWest.startEvent(player, sc);}
                }
            }
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }
    private void setFatDialogue(){}

}
