package main.java.GameProcesses.Plot;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.StoryReader;

import java.util.*;

public class Dialogue implements PrintableInterfaces {
    private Map<String, List<String>> eventsTextsAndCommands;
    private List<String> keysCurrentEvent;

    public Dialogue(String pathName) {
        eventsTextsAndCommands = StoryReader.gsonReader(pathName);
        keysCurrentEvent = eventsTextsAndCommands.keySet()
                .stream()
                .toList();
    }

    public Map<String, List<String>> getEventsTextsAndCommands() {return eventsTextsAndCommands;}
    public int getInnerListSize(String key) {
        return eventsTextsAndCommands.get(key).size();
    }

}
