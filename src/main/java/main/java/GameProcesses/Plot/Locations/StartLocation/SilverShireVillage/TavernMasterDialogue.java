package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.ConsoleCommands;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Food.Food;
import main.java.Items.Types;

import java.util.Scanner;

public class TavernMasterDialogue extends Events {

    Dialogue dialogue;
    Events silverShire;
    private String currentEvent;

    public TavernMasterDialogue(String eventName, Location LOCATION, Dialogue dialogue, Events silverShire) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.silverShire = silverShire;
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
        int userInput = 0;

        while (isEventActive()) {
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()));

            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_2(), this)) {
                switch (userInput) {
                    case 1 ->{
                        player.addToBackPack(new Food("ApplePie", 2, 1, Types.FOOD, 16.7f));
                        startEvent(player, sc);
                    }
                    case 2 -> startEvent(player, sc);
                }
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_TRADE(), this)) {
                System.out.println("Trade Interface");
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
                    case 2 -> setCurrentEvent(getPHRASE_2());
                    case 3 -> System.out.println("Trade Interface");
                    case 4 -> silverShire.startEvent(player, sc);
                }
            }
            System.out.println(getCurrentEvent() + " " + userInput);
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);

        }
    }
}
