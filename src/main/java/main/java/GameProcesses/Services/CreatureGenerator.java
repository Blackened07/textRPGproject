package main.java.GameProcesses.Services;

import main.java.Characters.Creature;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Plot.Locations.Location;

import java.util.Random;

public interface CreatureGenerator extends RandomNumberGenerator{


    default Creature getCreature (Location location) {
        switch (location) {
            case NORTH_FROM_CROSSROADS -> {
                if (randomToStartFight() % 2 == 0 ) {
                    return new Creature("Boar", 12, 9, 7, 1, 0, 10);
                } else return new Creature("Wolf", 11,10,9,1, 0, 10);
            }
        }
        return null;
    }
}
