package main.java.GameProcesses.Services;

import java.util.Scanner;
import java.util.regex.Pattern;

public interface GameScanner {

    String NON_DIGITS_PATTERN = "\\D";

    default int gameScanner(Scanner sc, int innerList) {

        int userCommand;
        while (true) {
            try {
                String userText = sc.nextLine().replaceAll(NON_DIGITS_PATTERN, "");
                if (userText.isEmpty()) throw new InvalidCommandException("Вы ввели: '" + userText + "'! Введите числовое значение!");
                userCommand = Integer.parseInt(userText.replaceAll(NON_DIGITS_PATTERN, ""));
                if (userCommand > innerList)
                    throw new InvalidCommandException("Вы ввели: '" + userCommand + "'! Некорректный номер команды!");
                return userCommand;
            } catch (InvalidCommandException exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }

    }
}
