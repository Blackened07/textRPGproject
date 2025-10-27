package main.java.GameProcesses.Services;

import com.google.gson.Gson;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryReader {
    static Gson textAndCommandReader;

    public StoryReader() {
        textAndCommandReader = new Gson();
    }

    public static Map<String, List<String>> gsonReader(String pathName) {
        try(FileReader reader = new FileReader(pathName)) {
            return textAndCommandReader.fromJson(reader, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
