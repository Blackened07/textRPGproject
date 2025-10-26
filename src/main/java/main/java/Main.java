package main.java;

import com.google.gson.Gson;
import main.java.GameProcesses.Game;

import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidCommandException {


        StoryReader story = new StoryReader();
        story.TextReader();

        Game game = new Game(story);
        game.startGame();
    }
}