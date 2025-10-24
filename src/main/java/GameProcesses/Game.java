package main.java.GameProcesses;

import main.java.Characters.*;
import main.java.GameProcesses.Plot.Dialogue;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;
import main.java.GameProcesses.Plot.Locations.StartGameEvent;
import main.java.GameProcesses.Plot.Locations.StartLocation.SilverShireVillage.SilverShireVillage;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.StoryReader;

import java.util.Scanner;

public class Game implements GameScanner {
    StoryReader story;
    Events startGameEvent;
    Events silvershire;
    Scanner sc;
    Organism player;

    public Game(StoryReader story) {
        this.story = story;
        //сделать метод имитации загрузки
        startGameEvent = new StartGameEvent("StartGame", Location.START_GAME, story, Dialogue.createDialogue());
        silvershire = new SilverShireVillage("StartVillage", Location.SILVERSHIRE_VILLAGE, story, Dialogue.createDialogue());
    }// new locations, events, npc for location with items

    public void startGame () {
        this.sc = new Scanner(System.in);
        this.player = startGameEvent.startGameEvent(sc);
        //choose the way
        silvershire.startEvent(this.player, sc);
    }
}
