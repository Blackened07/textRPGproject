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

    default int chooseLvl (int ... lvl) {
        return (int) (Math.random() * lvl.length);
    }
    default int chooseNumByValue(int listCapacity) {
        return (int) (Math.random() * listCapacity);
    }

}
