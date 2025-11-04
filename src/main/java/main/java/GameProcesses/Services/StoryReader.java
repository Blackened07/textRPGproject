package main.java.GameProcesses.Services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.Items.Item;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryReader {
    static Gson textAndCommandReader;

    public StoryReader() {
        textAndCommandReader = new Gson();
    }

    public static Map<String, List<String>> gsonReader(String pathName) {
        try (FileReader reader = new FileReader(pathName)) {
            return textAndCommandReader.fromJson(reader, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> gsonReaderForQuests(String pathName) {
        try (FileReader reader = new FileReader(pathName)) {
            return textAndCommandReader.fromJson(reader, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, List<Item>> gsonReaderForLoot(String pathName) {
        try (FileReader reader = new FileReader(pathName)) {
            Type type = new TypeToken<Map<String, List<Item>>>() {
            }.getType();
            return textAndCommandReader.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
