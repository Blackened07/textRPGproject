package main.java.GameProcesses.Services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StoryReader implements Phrases{

    File file;
    StringBuilder stringBuilder;
    List<String> lines;

    public StoryReader(File file) {
        this.file = file;
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
        Phrases.super.print(text);
    }

}
