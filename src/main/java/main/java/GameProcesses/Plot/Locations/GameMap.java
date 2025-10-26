package main.java.GameProcesses.Plot.Locations;

import java.util.*;

public class GameMap {
    private static Location location;
    private static Events event;

    private static LinkedHashMap<Location, Events> gameMap;

    public static void setLocationToGameMap(Location location, Events event) {
        gameMap.put(location, event);
    }
    public static void invokeEvent (Location location) {
        gameMap.get(location);
    }
}
