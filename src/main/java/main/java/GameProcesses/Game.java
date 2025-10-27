package main.java.GameProcesses;

import main.java.Characters.*;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartGameEvent;
import main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage.SilverShireVillage;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.StoryReader;
import java.util.Scanner;

public class Game  {
    StoryReader story;
    Events startGameEvent;
    Events silverShire;
    Scanner sc;
    Organism player;
    private static final String PATH_NAME_START_GAME_EVENT = "story/StartEvent.json";
    private static final String PATH_NAME_SILVERSHIRE_EVENT = "story/SilverShireVillageEvent.json";

    public Game(StoryReader story) {
        this.story = story;
        //сделать метод имитации загрузки
        startGameEvent = new StartGameEvent("StartGame", Location.START_GAME, new Dialogue(PATH_NAME_START_GAME_EVENT));
        silverShire = new SilverShireVillage("SilverShireVillage", Location.SILVERSHIRE_VILLAGE, new Dialogue(PATH_NAME_SILVERSHIRE_EVENT));
    }

    public void startGame() throws InvalidCommandException {
        this.sc = new Scanner(System.in);
        this.player = startGameEvent.startGameEvent(sc);
        silverShire.startEvent(this.player, sc);
    }
}
