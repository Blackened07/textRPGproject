package main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage;

import main.java.Characters.NPC;
import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads.Crossroads;
import main.java.GameProcesses.Plot.Quests.ActiveQuests;
import main.java.GameProcesses.Plot.Quests.Quest;
import main.java.GameProcesses.Services.ConsoleCommands;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.Items.Food.Food;
import main.java.Items.Item;
import main.java.Items.Types;

import java.util.Scanner;

public class SilverShireVillage extends Events {
    Dialogue dialogue;
    private String currentEvent;
    private Organism boy;
    private Organism tavernMaster;
    private final String START_BOY_DIALOGUE = "StartBoyDialogue";
    private final String START_TAVERN_MASTER_DIALOGUE = "StartTavernMasterDialogue";
    private final String START_SARAY_EVENT = "SarayEvent";
    private final String PATH_NAME_BOY_EVENT = "resources/SilverShireVillageBoyDialogue.json";
    private final String PATH_NAME_TAVERN_EVENT = "resources/SilverShireVillageTavernMasterDialogue.json";
    private final String PATH_NAME_CROSSROADS_EVENT = "resources/Crossroads.json";
    private Events boyDialogue;
    private Events tavernMasterDialogue;
    private Events crossroads;

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
        setCrossroadsEvent();
        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;
        while (isEventActive()) {

            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()), player, this);

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
            if (checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)) {
                switch (userInput) {
                    case 1 -> setCurrentEvent(START_BOY_DIALOGUE);
                    case 2 -> setCurrentEvent(START_TAVERN_MASTER_DIALOGUE);
                    case 3 -> setCurrentEvent(START_SARAY_EVENT);
                    case 4 -> crossroads.startEvent(player, sc);
                }
            }

            printEventTextAndCommands(getCurrentEvent(), this.dialogue);
        }
    }

    private void setBoyDialogue() {
        boyDialogue = new BoyDialogue("BoyDialogue", Location.SILVERSHIRE_VILLAGE, new Dialogue(PATH_NAME_BOY_EVENT), this, new ActiveQuests("ElfFigure", Quest.getQuest("ElfFigure"), 1, "Elf Figure"));
    }
    private void setTavernMasterDialogue() {
        tavernMaster = new NPC("Sednon", 5,5,5,5,150, 500);
        tavernMaster.addToBackPack(new Item("Health Potion", 10, 1, Types.POTION));
        tavernMaster.addToBackPack(new Food("ApplePie", 2, 1, Types.FOOD, 6.7f));
        tavernMaster.addToBackPack(new Food("Beef", 3, 1, Types.FOOD, 11.3f));
        tavernMasterDialogue = new TavernMasterDialogue("TavernMasterDialogue", Location.SILVERSHIRE_VILLAGE, new Dialogue(PATH_NAME_TAVERN_EVENT), this, tavernMaster);
    }
    private void setCrossroadsEvent(){
        crossroads = new Crossroads("Crossroads", Location.CROSSROADS, new Dialogue(PATH_NAME_CROSSROADS_EVENT), this);
    }

}
