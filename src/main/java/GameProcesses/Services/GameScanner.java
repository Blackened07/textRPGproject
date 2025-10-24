package main.java.GameProcesses.Services;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface GameScanner {
    default String gameScanner(Scanner sc) throws GameExceptions {
        String s = "";
        while (true) {
            try {
                s = sc.nextLine();
                if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) break;
                else throw new GameExceptions("ERROR");
            } catch (GameExceptions exceptions) {
                System.out.println("Вы ввели некорректные данные " + exceptions.getMessage());
                ;
            }
        }
        return s;
    }
}
