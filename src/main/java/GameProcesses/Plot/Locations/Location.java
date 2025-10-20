package main.java.GameProcesses.Plot.Locations;

import main.java.Characters.Organism;
import main.java.Characters.Player;
import main.java.Items.Item;

import java.util.Map;
import java.util.Objects;

public class Location {
    private final String LOCATION_NAME;
    Events event;
    Organism player;
    Organism npc;
    Organism creatures;
    /// CHANCE TO FIND ITEM and SET THE WEATHER
    Item item;
    Weather weather;

    public Location(String LOCATION_NAME) {
        this.LOCATION_NAME = LOCATION_NAME;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Location location = (Location) object;
        return Objects.equals(LOCATION_NAME, location.LOCATION_NAME) && Objects.equals(event, location.event) && Objects.equals(player, location.player) && Objects.equals(npc, location.npc) && Objects.equals(creatures, location.creatures) && Objects.equals(item, location.item) && weather == location.weather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(LOCATION_NAME, event, player, npc, creatures, item, weather);
    }
}
