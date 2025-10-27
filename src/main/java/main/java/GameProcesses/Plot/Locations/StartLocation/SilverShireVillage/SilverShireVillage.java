package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.ConsoleCommands;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class SilverShireVillage extends Events {
    Dialogue dialogue;
    private String currentEvent;
    private final String START_BOY_DIALOGUE = "StartBoyDialogue";
    private final String START_TAVERN_MASTER_DIALOGUE = "StartTavernMasterDialogue";
    private final String START_SARAY_EVENT = "SarayEvent";
    private final String PATH_NAME_BOY_EVENT = "story/SilverShireVillageBoyDialogue.json";
    private final String PATH_NAME_TAVERN_EVENT = "story/SilverShireVillageTavernMasterDialogue.json";
    private Events boyDialogue;
    private Events tavernMasterDialogue;

    public SilverShireVillage(String eventName, Location LOCATION, Dialogue dialogue) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
    }

    @Override public String getCurrentEvent() {return currentEvent;}
    @Override public void setCurrentEvent(String currentEvent) {this.currentEvent = currentEvent;}

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        setEventActive(true);
        setBoyDialogue();
        setTavernMasterDialogue();
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput = 0;
        while (isEventActive()) {

            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()));

            if (checkCurrentEventAndCommandEqualsForDialogue(START_BOY_DIALOGUE,this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                setEventActive(false);
                boyDialogue.startEvent(player, sc);
                break;
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(START_TAVERN_MASTER_DIALOGUE, this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                setEventActive(false);
                tavernMasterDialogue.startEvent(player, sc);
                break;
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(START_SARAY_EVENT, this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSWITCH_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> {
                        System.out.println("North");

                    }
                    case 2 -> {
                        System.out.println("East");
                    }
                    case 3 -> {
                        System.out.println("West");
                    }
                    case 4 -> {startEvent(player, sc);}
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(START_BOY_DIALOGUE);
                    case 2 -> setCurrentEvent(START_TAVERN_MASTER_DIALOGUE);
                    case 3 -> setCurrentEvent(START_SARAY_EVENT);
                    case 4 -> setCurrentEvent(getSWITCH_EVENT());
                }
            }

            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }

    private void setBoyDialogue() {
        boyDialogue = new BoyDialogue("BoyDialogue", Location.SILVERSHIRE_VILLAGE, new Dialogue(PATH_NAME_BOY_EVENT), this);
    }
    private void setTavernMasterDialogue() {
        tavernMasterDialogue = new TavernMasterDialogue("TavernMasterDialogue", Location.SILVERSHIRE_VILLAGE, new Dialogue(PATH_NAME_TAVERN_EVENT), this);
    }


}
