package main.java.GameProcesses.Services;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;

import java.util.Scanner;

public interface UseItemsFromBackPack extends PrintableInterfaces, GameScanner {

    default void useItemsFromBackPack(Organism player, Scanner sc, Events events) throws InvalidCommandException {

        int userInputForOperation;
        int userInput;

        while (true) {
            System.out.println(player.showItemsFromBackPack());
            userInputForOperation = gameScanner(sc, player.getSize(), player, events) - 1;

            if (userInputForOperation == -3) {
                print("Введите номер предмета");
                userInput = gameScanner(sc, player.getSize(), player, events) - 1;
                print("введите команду: /set - если оружие не экипировано, /change - если нужно заменить экипированное оружие, /heal, /drop");
                switch (player.getItemType(userInput)) {
                    case WEAPON -> {
                        userInputForOperation = gameScanner(sc, 3, player, events) - 1;
                        switch (userInputForOperation) {
                            case -5 -> player.setWeaponFromBackPackToInventory(userInput);
                            case -9 -> player.changeWeapon(userInput);
                            case -513 -> player.dropItem(userInput);
                        }
                    }
                    case FOOD -> {
                        userInputForOperation = gameScanner(sc, 2, player, events) - 1;
                        switch (userInputForOperation) {
                            case -7 -> player.useFood(userInput);
                            case -513 -> player.dropItem(userInput);
                        }
                    }
                    case POTION -> {
                        userInputForOperation = gameScanner(sc, 2, player, events) - 1;
                        switch (userInputForOperation) {
                            case -7 -> player.useFood(userInput);
                            case -513 -> player.dropItem(userInput);
                        }
                    }
                    case ARMOR -> {
                        userInputForOperation = gameScanner(sc, 2, player, events) - 1;
                        if (userInputForOperation == -513) {
                            player.dropItem(userInput);
                        }
                    }
                }
            }
            if (userInputForOperation == -11) events.startEvent(player, sc);

        }
    }
}
