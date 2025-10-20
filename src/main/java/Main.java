package main.java;

import main.java.GameProcesses.Game;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.GameMap;
import main.java.GameProcesses.Plot.Locations.Location;
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
        Location startGame = new Location("StartGame");
        Events startGameChooseWeapon = new Events("startGameChooseWeapon", story.phrasesBuilder(0, 3), story.phrasesBuilder(3, 3), startGame);
        Events startGameChooseSpecAxe = new Events("startGameChooseSpecAxe", story.phrasesBuilder(6, 1), story.phrasesBuilder(7, 2), startGame);
        Events startGameChooseSpecStuff = new Events("startGameChooseSpecAxe", story.phrasesBuilder(9, 1), story.phrasesBuilder(10, 2), startGame);
        Events startGameChooseSpecDagger = new Events("startGameChooseSpecDagger", story.phrasesBuilder(12, 1), story.phrasesBuilder(13, 2), startGame);
        GameMap.setLocationToGameMap(startGame, startGameChooseWeapon);
        GameMap.setLocationToGameMap(startGame, startGameChooseSpecAxe);
        GameMap.setLocationToGameMap(startGame, startGameChooseSpecStuff);
        GameMap.setLocationToGameMap(startGame, startGameChooseSpecDagger);
        // new locations, events, npc for location with items
        Game game = new Game(story);
        game.startGame();
    }
}