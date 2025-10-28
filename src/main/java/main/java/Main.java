package main.java;

import main.java.GameProcesses.Game;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;

public class Main {

    public static void main(String[] args) throws InvalidCommandException {

        StoryReader story = new StoryReader();

        Game game = new Game(story);
        game.startGame();
    }
}