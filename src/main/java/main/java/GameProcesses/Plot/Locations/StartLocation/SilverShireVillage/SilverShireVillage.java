package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class SilverShireVillage extends Events {
    Dialogue dialogue;

    private final String START_BOY_DIALOGUE = "StartBoyDialogue";
    private final String START_TAVERN_MASTER_DIALOGUE = "StartTavernMasterDialogue";
    private final String START_SARAY_EVENT = "SarayEvent";
    private final String PATH_NAME_BOY_EVENT = "story/SilverShireVillageBoyDialogue.json";
    private final String PATH_NAME_TAVERN_EVENT = "story/SilverShireVillageTavernMasterDialogue.json";
    private Events boyDialogue;
    private Events tavernMasterDialogue;
    private StoryReader story;

    public SilverShireVillage(String eventName, Location LOCATION, StoryReader story, Dialogue dialogue) {
        super(eventName, LOCATION, story);
        this.story = story;
        this.dialogue = dialogue;
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        setEventActive(true);
        setBoyDialogue(player, sc);
        setTavernMasterDialogue(player, sc);
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput = 0;
        while (isEventActive()) {

            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()));
            if (checkCurrentEventAndCommandEqualsForDialogue(START_BOY_DIALOGUE, userInput)) {
                setEventActive(false);
                boyDialogue.startEvent(player, sc);
                break;
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(START_TAVERN_MASTER_DIALOGUE, userInput)) {
                setEventActive(false);
                tavernMasterDialogue.startEvent(player, sc);
                break;
            }
            if(checkCurrentEventAndCommandEqualsForDialogue(START_SARAY_EVENT, userInput)) {
                startEvent(player, sc);
            }
            if (checkCurrentEventAndCommandEqualsForDialogue(getSWITCH_EVENT(), userInput)) {
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
            setCurrentEvent(dialogue.getCurrentEventKey(userInput));
            printEventTextAndCommands(dialogue.getCurrentEventKey(userInput), this.dialogue);
        }
    }

    private void setBoyDialogue(Organism player, Scanner sc) {
        boyDialogue = new BoyDialogue("BoyDialogue", Location.SILVERSHIRE_VILLAGE, story, new Dialogue(PATH_NAME_BOY_EVENT), this);
    }
    private void setTavernMasterDialogue(Organism player, Scanner sc) {
        tavernMasterDialogue = new TavernMasterDialogue("TavernMasterDialogue", Location.SILVERSHIRE_VILLAGE, story, new Dialogue(PATH_NAME_TAVERN_EVENT));
    }


}
