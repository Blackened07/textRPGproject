package main.java.GameProcesses.Services;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;

import java.util.Scanner;

public interface GameScanner {

    String NON_DIGITS_PATTERN = "\\D";

    default int gameScanner(Scanner sc, int innerList, Organism player, Events events) {

        int userCommand;
        while (true) {
            try {
                String userText = sc.nextLine();
                if(gameCommands(player, userText, sc, events ) == 1024) throw new InvalidCommandException("Вы ввели игровую команду");
                if(gameCommands(player, userText, sc, events) == 333) throw new InvalidCommandException("Вы ввели игровую команду");
                if (userText.equals("/buy")) return -25;
                if (userText.equals("/trade")) return -50;
                if (userText.equals("/exitTrade")) return -75;
                if (userText.equals("/exitBP")) return -10;
                if (userText.equals("/use")) return -2;
                if (userText.equals("/set")) return -4;
                if (userText.equals("/change")) return -8;
                if (userText.equals("/heal")) return -6;
                if (userText.equals("/drop")) return -512;
                userText = userText.replaceAll(NON_DIGITS_PATTERN, "");
                if (userText.isEmpty()) throw new InvalidCommandException("Вы ввели: '" + userText + "'! Введите числовое значение!");
                userCommand = Integer.parseInt(userText.replaceAll(NON_DIGITS_PATTERN, ""));
                if (userCommand > innerList || userCommand == 0)
                    throw new InvalidCommandException("Вы ввели: '" + userCommand + "'! Некорректный номер команды!");
                return userCommand;
            } catch (InvalidCommandException exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }

    default int gameCommands(Organism player, String userText, Scanner sc, Events events) throws InvalidCommandException {
        switch (userText) {
            case "/backpack" -> {
                player.useItemsFromBackPack(player, sc, events);
                return 1024;
            }
            case "/stats" ->{
                player.features(player);
                return 333;
            }
        }
        return 8192;
    }
}
