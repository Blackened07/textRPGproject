package main.java.GameProcesses.Plot.Locations.StartLocation.Crossroads;

import main.java.Characters.Organism;
import main.java.GameProcesses.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartLocation.LakeSilver;
import main.java.GameProcesses.Plot.Locations.StartLocation.Mill;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class Crossroads extends Events {
    Dialogue dialogue;
    Events silverShire;
    private Events roadToWest;
    private Events roadToEast;
    private Events roadToNorth;
    private Events lake;
    private Events mill;
    private final String PATH_NAME_ROAD_TO_WEST_EVENT = "resources/RoadToWest.json";
    private final String PATH_NAME_ROAD_TO_EAST_EVENT = "resources/RoadToEast.json";
    private final String PATH_NAME_ROAD_TO_NORTH_EVENT = "resources/RoadToNorth.json";
    private final String PATH_NAME_LAKE = "resources/Lake.json";
    private final String PATH_NAME_MILL_EVENT = "resources/Mill.json";

    public Crossroads(String eventName, Location LOCATION, Dialogue dialogue, Events silverShire) {
        super(eventName, LOCATION);
        this.dialogue = dialogue;
        this.silverShire = silverShire;
    }

    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        setCurrentEvent(getSTART_EVENT());
        printEventTextAndCommands(getSTART_EVENT(), this.dialogue);
        setEventActive(true);

        setEastEventAtStartLocation();
        setNorthEventAtStartLocation();
        setWestEventAtStartLocation();
        setLake();
        setMill();

        setLinks();

        eventSwitcher(sc, player);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        int userInput;

        while (isEventActive()) {
            userInput = gameScanner(sc, dialogue.getInnerListSize(getCurrentEvent()), player, this);

            if(checkCurrentEventAndCommandEqualsForDialogue(getSTART_EVENT(), this)){
                switch (userInput) {
                    case 1 -> roadToNorth.startEvent(player, sc);
                    case 2 -> roadToEast.startEvent(player, sc);
                    case 3 -> roadToWest.startEvent(player, sc);
                    case 4 -> silverShire.startEvent(player, sc);
                }
            }
        }
    }

    private void setLake () {
        lake = new LakeSilver("LakeSilver", Location.LAKE_SILVER, new Dialogue(PATH_NAME_LAKE), mill, roadToNorth);
    }
    private void setMill () {
        mill = new Mill("Mill", Location.MILL, new Dialogue(PATH_NAME_MILL_EVENT), roadToWest, lake, roadToNorth);
    }
    private void setEastEventAtStartLocation () {
        roadToEast = new RoadToEast("RoadToEast", Location.CROSSROADS, new Dialogue(PATH_NAME_ROAD_TO_EAST_EVENT), this);
    }
    private void setNorthEventAtStartLocation () {
        roadToNorth = new RoadToNorth("RoadToNorth", Location.NORTH_FROM_CROSSROADS, new Dialogue(PATH_NAME_ROAD_TO_NORTH_EVENT), this, lake, mill);
    }
    private void setWestEventAtStartLocation () {
        roadToWest = new RoadToWest("RoadToWest", Location.WEST_FROM_CROSSROADS, new Dialogue(PATH_NAME_ROAD_TO_WEST_EVENT), this, mill);
    }
    private void setLinks () {
        for (int i = 0; i < 15; i ++) {
            setEastEventAtStartLocation();
            setNorthEventAtStartLocation();
            setWestEventAtStartLocation();

            setLake();
            setMill();
        }
    }
}
