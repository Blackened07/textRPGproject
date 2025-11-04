package main.java.Characters;

import main.java.GameProcesses.Services.PrintableInterfaces;

public class ExperienceCalculator {

    private static int riseCoefficient = 100;
    private static int expRise = 200;
    private static int expNeeded = 200;

    private static void setExpNeed() {expNeeded += expRise;}
    private static void setRiseCoefficient() {riseCoefficient += 100;}
    private static void setExpRise() {expRise += riseCoefficient;}

    private static void levelUp() {
        setExpNeed();
        setExpRise();
        setRiseCoefficient();
        System.out.println("DING!!!");
    }
    public static boolean checkExp (int currentExp) {
        if (currentExp == expNeeded) {
            levelUp();
            return true;
        }
        return false;
    }

}
