package main.java.GameProcesses.Services;

import main.java.Characters.Creature;
import main.java.Characters.GameClass;
import main.java.Characters.StatsCalculator;
import main.java.GameProcesses.Plot.Locations.Location;

import java.util.List;
import java.util.Map;


public class CreatureGenerator implements RandomNumberGenerator, StatsCalculator {
    private final String PATH = "resources/Organism/Organism.json";
    private Map<Location, Map<GameClass, List<String>>> creatureNames;
    private List<GameClass> classes;
    private List<Integer> creatureStats;

    public CreatureGenerator() {
        this.creatureNames = StoryReader.gsonReaderForOrganismNamesByLocations(PATH);
    }

    public Creature getCreature (Location location, int... lvl) {
        GameClass gameClass = getCreatureClassByLocation(location);
        creatureStats = calculateStatsForNewOrganism(gameClass, lvl);
        return new Creature(getCreatureNamesByClass(gameClass, location), creatureStats.get(STR_INDEX), creatureStats.get(STAM_INDEX), creatureStats.get(AGI_INDEX), creatureStats.get(INT_INDEX), creatureStats.get(LEVEL_INDEX), getGoldValue(creatureStats.get(LEVEL_INDEX)), gameClass, creatureStats.get(EXP_INDEX), location);
        }


    private String getCreatureNamesByClass(GameClass gameClass, Location location) {
        return creatureNames.get(location).get(gameClass).get(chooseNumByValue(creatureNames.get(location).get(gameClass).size()));
    }
    private GameClass getCreatureClassByLocation(Location location) {
        classes = creatureNames.get(location).keySet().stream().toList();
        return classes.get(chooseNumByValue(classes.size()));
    }
    private int getGoldValue (int lvl) {
        return chooseNumByValue(lvl * 10);
    }



}
