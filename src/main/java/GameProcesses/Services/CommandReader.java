package main.java.GameProcesses.Services;

import main.java.GameProcesses.Plot.Locations.Location;

import java.util.Scanner;

public interface CommandReader extends GameScanner {
    default int commandReader(Scanner sc) {
        int choose = Integer.parseInt(gameScanner(sc));
        switch (choose) {
            case 1 -> {
                return 1;
            }
            case 2 -> {
                return 2;
            }
            case 3 -> {
                return 3;
            }
            case 4 -> {
                return 4;
            }
        }
        return -1;
    }

    default int commandParser(int i, String currentEvent) {


        if (currentEvent.equals("Start")) {
            if (i == 1) return 1;
            else if (i == 2) return 2;
            else if (i == 3) return 3;
            else if (i == 4) return 4;
            else return 0;
        } else if (currentEvent.equals("1")) {
            if (i == 1) return 5;
            else if (i == 2) return 6;
            else if (i == 3) return 7;
            else if (i == 4) return 8;
            else return 0;
        } else if (currentEvent.equals("2")) {

        }
        return -1;
    }

}
