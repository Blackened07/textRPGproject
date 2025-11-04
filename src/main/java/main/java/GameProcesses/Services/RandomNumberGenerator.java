package main.java.GameProcesses.Services;

import java.util.Random;

public interface RandomNumberGenerator {

    default int randomToStartFight () {
        return (int) (Math.random() * 100);
    };

    default int whoStart () {
        Random random = new Random();
        return random.nextInt(50);
    }

    default int creaturesGold (int lvl) {
        Random random = new Random();
        switch (lvl) {
            case 1 -> {
                return random.nextInt(5);
            }
            case 2 -> {
                return random.nextInt(7);
            }
            case 3 -> {
                return random.nextInt(9);
            }
            case 4 -> {
                return random.nextInt(12);
            }
            case 5 -> {
                return random.nextInt(16);
            }
        }
        return 0;
    }
}
