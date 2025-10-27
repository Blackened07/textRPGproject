package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class BoyDialogue extends Events {
    Dialogue dialogue;
    private Events silverShire;

    public BoyDialogue(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue, Events silverShire) {
        super(eventName, LOCATION, story);
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

            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_1(), userInput)) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_QUEST(), userInput)) {
                System.out.println("Quest Accepted");
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_2(), userInput)) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSWITCH_EVENT(), userInput)) {
                silverShire.startEvent(player, sc);
                break;
            }
            setCurrentEvent(dialogue.getCurrentEventKey(userInput));
            printEventTextAndCommands(dialogue.getCurrentEventKey(userInput), this.dialogue);
        }
    }

}
