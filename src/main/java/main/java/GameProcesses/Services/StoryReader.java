package main.java.GameProcesses.Services;

import com.google.gson.Gson;
import main.java.GameProcesses.Plot.Dialogue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryReader implements Printable {
    File dir;
    File file;
    StringBuilder stringBuilder;
    List<String> lines;
    static Gson textAndCommandReader;
    private static final String pathNameStartGameEvent = "story/StartEvent.json";

    public StoryReader() {
        this.dir = new File("story");
        this.file = new File("story/startPhrases.txt");
        try {
            dir.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textAndCommandReader = new Gson();
    }

    public static Map<String, List<String>> gsonReader() {
        try(FileReader reader = new FileReader(pathNameStartGameEvent)) {
            return textAndCommandReader.fromJson(reader, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void TextReader () {
        try {
           lines = Files.readAllLines(Paths.get("story/startPhrases.txt"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    public String phrasesBuilder (int firstLineNimber, int linesCount) {
        stringBuilder = new StringBuilder();
        for (int i = firstLineNimber; i < firstLineNimber + linesCount; i++) {
            if (i != (firstLineNimber + linesCount) - 1) {
            stringBuilder.append(lines.get(i)).append("\n");
            } else stringBuilder.append(lines.get(i));
        }
       return stringBuilder.toString();
    }
    @Override public void print(String text) {
        Printable.super.print(text);
    }

}
