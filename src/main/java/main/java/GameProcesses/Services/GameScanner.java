package main.java.GameProcesses.Services;

import java.util.Scanner;
import java.util.regex.Pattern;

public interface GameScanner {

    String NON_DIGITS_PATTERN = "\\D";

    default int gameScanner(Scanner sc, int innerList) {

        int userCommand;
        while (true) {
            try {
                String userText = sc.nextLine();
                if (userText.equals("/buy")) return -25;
                if (userText.equals("/trade")) return -50;
                if (userText.equals("/exitTrade")) return 75;
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

}
