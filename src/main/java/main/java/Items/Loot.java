package main.java.Items;

import main.java.GameProcesses.Services.StoryReader;

import java.util.List;
import java.util.Map;

public class Loot {
    private static Map<String, List<Item>> loot;
    private final String pathName = "resources/Loot/Loot.json";

    public Loot() {
        loot = StoryReader.gsonReaderForLoot(pathName);
    }

    private static Map<String, List<Item>> getLoot() {
        return loot;
    }

    public static List<Item> getLoot (String keyAsQCreatureName) {
        return loot.get(keyAsQCreatureName);
    }
}
