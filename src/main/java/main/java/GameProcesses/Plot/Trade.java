package main.java.GameProcesses.Plot;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.PrintableInterfaces;

import java.util.Scanner;

public interface Trade extends PrintableInterfaces, GameScanner {

    default void trade(Organism player, Organism npc, Scanner sc, Events events) throws InvalidCommandException {
        int userInputForOperation;
        int userInput;
        boolean tradeActive = false;

        while (!tradeActive) {

            System.out.println(npc.showItemsFromBackPack() + "\n\n" + player.showItemsFromBackPack());

            userInputForOperation = gameScanner(sc, npc.getSize()) - 1;

            if (userInputForOperation == -26) {
                userInput = gameScanner(sc, npc.getSize()) - 1;
                for (int i = 0; i < npc.getSize(); i++) {
                    if (userInput == i) {
                        if (player.getGold() > npc.getFromBackPackWithIndex(i).getCost()) {
                            player.addToBackPack(npc.getFromBackPackWithIndex(i));
                            player.setGoldAfterTrade(-(npc.getFromBackPackWithIndex(i).getCost()));
                            npc.setGold(npc.getFromBackPackWithIndex(i).getCost());
                            npc.removeFromBAckPack(i);
                        }
                        break;
                    }
                }
            }
            if (userInputForOperation == -51) {
                userInput = gameScanner(sc, npc.getSize()) - 1;
                for (int i = 0; i < npc.getSize(); i++) {
                    if (userInput == i) {
                        if (npc.getGold() > player.getFromBackPackWithIndex(i).getCost()) {
                            npc.addToBackPack(player.getFromBackPackWithIndex(i));
                            npc.setGoldAfterTrade(-(player.getFromBackPackWithIndex(i).getCost()));
                            player.setGold(player.getFromBackPackWithIndex(i).getCost());
                            player.removeFromBAckPack(i);
                            break;
                        }
                    }
                }
            }
            if (userInputForOperation == -76) events.startEvent(player, sc);
        }
    }

}
