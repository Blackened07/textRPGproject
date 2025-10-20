package main.java;

import main.java.GameProcesses.Game;

import main.java.GameProcesses.Services.StoryReader;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        File dir = new File("story");
        File file = new File("story/startPhrases.txt");
        try {
            dir.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StoryReader story = new StoryReader(file);
        story.TextReader();

        Game game = new Game(story);
        game.startGame();
    }
}