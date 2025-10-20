package main.java.GameProcesses.Services;

import java.util.Scanner;

public interface GameScanner {
    default String gameScanner(Scanner sc) {
        return sc.nextLine();
    }
}
