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

}
