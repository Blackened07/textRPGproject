package main.java.Items;

import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.RestorableItem.RestorableItem;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loot {
    private static Map<String, List<Item>> loot;
    private static Map<String, List<RestorableItem>> vendorsForRelease;
    private final String pathName2 = "resources/Loot/Loot.json";
    private final String pathName = "resources/Loot/Vendors.json";

    public Loot() {
        vendorsForRelease = StoryReader.gsonReaderForFoodVendors(pathName);
        loot = StoryReader.gsonReaderForLoot(pathName2);
    }

    private static Map<String, List<Item>> getLoot() {
        return loot;
    }

    public static List<Item> getLoot (String keyAsQCreatureName) {
        return loot.get(keyAsQCreatureName);
    }
    /*public static List<Item> getForVendor (String keyAsVendorName) {
        return vendorsForRelease.get(keyAsVendorName).stream()
                .map(Item.class::cast)
                .toList();
    }*/

    public static List<RestorableItem> getForVendor (String key) {
        return vendorsForRelease.get(key);
    }
}
