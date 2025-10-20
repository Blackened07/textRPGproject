package main.java.GameProcesses;

import main.java.Characters.*;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartGameEvent;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Weapon.Weapon;

import java.util.Scanner;

public class Game implements GameScanner {
    StoryReader story;
    Events startGameEvent;
    Scanner sc;
    Organism player;

    public Game(StoryReader story) {
        this.story = story;
        startGameEvent = new StartGameEvent("StartGame", Location.START_GAME, story);
    }// new locations, events, npc for location with items

    public void startGame () throws InterruptedException {
        this.sc = new Scanner(System.in);
        this.player = startGameEvent.startEvent(sc);

    }

    @Override
    public String gameScanner(Scanner sc) {
        return GameScanner.super.gameScanner(sc);
    }
}
