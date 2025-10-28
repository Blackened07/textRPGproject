package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.ConsoleCommands;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Food.Food;
import main.java.Items.Item;
import main.java.Items.Types;

import java.util.Scanner;

public class TavernMasterDialogue extends Events {

    private Dialogue dialogue;
    private Events silverShire;
    private Organism tavernMaster;
    private boolean checkPie;
    private final String AFTER_PIE_EVENT = "Phrase2 - 2";

    public TavernMasterDialogue(String eventName, Location LOCATION, Dialogue dialogue, Events silverShire, Organism tavernMaster) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.silverShire = silverShire;
        this.tavernMaster = tavernMaster;
    }

    public void setCheckPie(boolean checkPie) {this.checkPie = checkPie;}

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
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()));

            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_2(), this)) {
                switch (userInput) {
                    case 1 ->{
                        player.addToBackPack(tavernMaster.getFromBackPack( "ApplePie"));
                        setCheckPie(true);
                        startEvent(player, sc);
                    }
                    case 2 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_5(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_6(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0] ) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(AFTER_PIE_EVENT, this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_4(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_3(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(getPHRASE_4());
                    case 2 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_1(),this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                setCurrentEvent(getPHRASE_3());
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(getPHRASE_1());
                    case 2 -> {
                        if (checkPie) {
                            setCurrentEvent(AFTER_PIE_EVENT);
                        }
                        else setCurrentEvent(getPHRASE_2());
                    }
                    case 3 -> {setCurrentEvent(getPHRASE_5());}
                    case 4 -> {setCurrentEvent(getPHRASE_6());}
                    case 5 -> {
                        trade(player, tavernMaster, sc, this);
                    }
                    case 6 -> silverShire.startEvent(player, sc);
                }
            }
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }
}
