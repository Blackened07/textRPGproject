package main.java.GameProcesses.Services;

import main.java.Characters.Organism;

import java.util.Scanner;

public interface UseItemsFromBackPack extends PrintableInterfaces, GameScanner {

    default void useItemsFromBackPack (Organism player, Scanner sc) {

        int userInputForOperation;
        int userInput;

        while (true) {
            userInputForOperation = gameScanner(sc, player.getSize(), player) - 1;

            if (userInputForOperation == -2) {
                userInput = gameScanner(sc, player.getSize(), player) - 1;

                switch (player.getItemType(userInput)) {
                    case WEAPON -> {player.setWeaponFromBackPackToInventory(userInput);}
                    case FOOD -> {}
                    case POTION -> {}
                    case ARMOR -> {}
                }
            }
        }
    }
}
