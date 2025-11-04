package main.java.Characters;

import main.java.GameProcesses.Services.PrintableInterfaces;

public class ExperienceCalculator {

    private static int riseCoefficient = 100;
    private static int expRise = 200;
    private static int expNeeded = 200;


    private static void setExpNeed() {expNeeded += expRise;}
    private static void setRiseCoefficient() {riseCoefficient += 100;}
    private static void setExpRise() {expRise += riseCoefficient;}

    public static int getExpNeeded() {
        return expNeeded;
    }

    private static void levelUp() {
        setExpNeed();
        setExpRise();
        setRiseCoefficient();
        System.out.println("DING!!!");
    }
    public static void checkExp (int currentExp, int expGained, Organism player) {
        int whenExpGainedMoreThenNeeded;
        if (currentExp + expGained == expNeeded) {
            player.setExperience(expGained);
            levelUp();
            player.setLevel();
            player.setExpWhenLevelUp();
        } else if (currentExp + expGained > expNeeded) {
            whenExpGainedMoreThenNeeded = expGained - (expNeeded - currentExp);
            levelUp();
            player.setLevel();
            player.setExpWhenLevelUp();
            player.setExperience(whenExpGainedMoreThenNeeded);
        } else player.setExperience(expGained);
    }


}
