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
                .sorted()
                .toList();
    }

    public Map<String, List<String>> getEventsTextsAndCommands() {return eventsTextsAndCommands;}
    public String getCurrentEventKey(int index) {
        return keysCurrentEvent.get(index);
    }
    public String getTextFromMAp(String key, int index) {
        return getEventsTextsAndCommands().get(key).get(index);
    }
    public int getInnerListSize(String key) {
        return eventsTextsAndCommands.get(key).size();
    }

}
