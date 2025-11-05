package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Quests.ActiveQuests;
import main.java.GameProcesses.Services.ConsoleCommands;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.Items.Item;
import main.java.Items.Types;

import java.util.Scanner;

public class BoyDialogue extends Events {
    Dialogue dialogue;
    private Events silverShire;
    ActiveQuests elfFigure;

    public BoyDialogue(String eventName, Location LOCATION, Dialogue dialogue, Events silverShire, ActiveQuests elfFigure) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.silverShire = silverShire;
        this.elfFigure = elfFigure;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setEventActive(true);
        if (player.getQuestObjectiveIsComplete(elfFigure.getQuestName())) setCurrentEvent(getQUEST_COMPLETE());
        else setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        eventSwitcher(sc, player);
    }
    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput = 0;
        while (isEventActive()) {

            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()), player, this);

            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_1(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_QUEST(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                if(player.findQuestInJournal(elfFigure)){
                    player.addQuestToJournal(elfFigure);
                }
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getPHRASE_2(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getQUEST_COMPLETE(), this) && userInput == ConsoleCommands.DIGIT_COMMANDS[0]) {
                player.removeFromBackPackByName("Elf Figure");
                print("Quest Complete");
                player.addToBackPack(new Item("MillChestKey", 5, 0, Types.KEY));
                player.removeQuestFromJournal("ElfFigure");
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(getPHRASE_1());
                    case 2 -> {
                        if(player.findItemWithName("Elf Figure")) {
                            setCurrentEvent(getQUEST_COMPLETE());
                        } else setCurrentEvent(getSTART_QUEST());
                    }
                    case 3 -> setCurrentEvent(getPHRASE_2());
                    case 4 -> silverShire.startEvent(player, sc);
                }
            }
            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }

}
