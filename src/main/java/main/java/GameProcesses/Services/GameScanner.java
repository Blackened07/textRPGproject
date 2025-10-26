package main.java.GameProcesses.Services;

import java.util.Scanner;
import java.util.regex.Pattern;

public interface GameScanner {
    String ONE = "1";
    String TWO = "2";
    String THREE = "3";
    String FOUR = "4";
    Pattern PATTERN = Pattern.compile("\\D");

    default int gameScanner(Scanner sc, int innerList) {
        String[] commandsNumbers = {ONE, TWO, THREE, FOUR};
        int userCommand = 0;
        while (true) {
            try {
                String userText = sc.nextLine();
                for (String commands : commandsNumbers) {
                    if (userText.equals(commands)) {
                        userCommand = Integer.parseInt(userText);
                        break;
                    } else throw new InvalidCommandException("Вы ввели: '" + userText + "'! Некорректные данные!");
                }
                if (userCommand < innerList) break;
                else throw new InvalidCommandException("Вы ввели: '" + userText + "'! Некорректный номер команды!");
            } catch (InvalidCommandException exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
        return userCommand;
    }
}
