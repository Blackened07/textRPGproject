package main.java.GameProcesses.Quests;

import main.java.GameProcesses.Services.StoryReader;

import java.util.Map;

public class Quest {
    private static Map<String, String> questLog;
    private final String pathName = "resources/Quests/Quests.json";

    public Quest() {
        questLog = StoryReader.gsonReaderForQuests(pathName);
    }

    private static Map<String, String> getQuestLog() {
        return questLog;
    }

    public static String getQuest (String keyAsQuestName) {
        return getQuestLog().get(keyAsQuestName);
    }

}
