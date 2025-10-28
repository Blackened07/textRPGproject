package main.java.GameProcesses.Plot.Locations.StartLocation;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Services.InvalidCommandException;

import java.util.Scanner;

public class TownGate extends Events {
    public TownGate(String eventName, Location LOCATION) {
        super(eventName, LOCATION);
    }

    @Override
    public void startEvent(Organism player, Scanner sc) throws InvalidCommandException {
        super.startEvent(player, sc);
    }

    @Override
    protected void eventSwitcher(Scanner sc, Organism player) throws InvalidCommandException {
        super.eventSwitcher(sc, player);
    }
}
