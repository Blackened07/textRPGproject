package main.java.GameProcesses.Services;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;

import java.util.Scanner;

public interface GameScanner {
    int BUY = -25;
    int TRADE = -50;
    int EXIT_TRADE = -75;


    String NON_DIGITS_PATTERN = "\\D";

    default int gameScanner(Scanner sc, int innerList, Organism player, Events events) {

        int userCommand;
        while (true) {
            try {
                String userText = sc.nextLine();

                if (userText.equals("/backpack")) player.useItemsFromBackPack(player, sc, events);
                if (userText.equals("/stats")) player.features(player);
                if (userText.equals("/equip")) player.showItemsFromEquipment();
                if (userText.equals("/quest")) player.showQuestJournal();
                if (userText.equals("/buy")) return BUY;
                if (userText.equals("/trade")) return TRADE;
                if (userText.equals("/exitTrade")) return EXIT_TRADE;
                if (userText.equals("/closeBP")) return -10;
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
    default String fightScanner(Scanner sc) {


        return sc.nextLine();
    }

}
