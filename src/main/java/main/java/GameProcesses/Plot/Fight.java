package main.java.GameProcesses.Plot;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.RandomNumberGenerator;

import java.util.Scanner;

public interface Fight extends PrintableInterfaces, RandomNumberGenerator, GameScanner {

    default void fight(Organism player, Organism creature, Scanner sc, Events event) throws InvalidCommandException {
        String userInput;
        boolean isPlayerTurn;
        print("На вас напал " + creature.getNAME() + "\nБой начался!");
        isPlayerTurn= chanceToAttackFirst(player);

        while (player.getCurrentHealth() > 0 && creature.getCurrentHealth() > 0) {
            if (isPlayerTurn) {
                userInput = fightScanner(sc);
                switch (userInput) {
                    case "/a" -> {
                        player.autoAttack(player, creature);
                        print("Вы нанесли " + player.getFullAttackPower() + " урона.\nТекущее здоровье противника " + creature.getCurrentHealth());
                    }
                    case "/backpack" -> player.useItemsFromBackPack(player, sc, event);
                }
            } else {
                creature.autoAttack(creature, player);
                print(creature.getNAME() + "нанёс " + creature.getFullAttackPower() + " урона.\n Ваше текущее здоровье " + player.getCurrentHealth());
            }
            isPlayerTurn = !isPlayerTurn;
        }
        if (player.getCurrentHealth() <=0) {print("Game Over");}
        else {player.addToBackPack(creature.getFromBackPackWithIndex((int) Math.random() * creature.getSize()));
        player.setGold(creaturesGold(creature.getLevel()));
        player.setExperience(50);
        player.checkExp();}
    }
    default boolean chanceToAttackFirst(Organism player) {
        if (player.getAgility() * 2 > whoStart()) return true;
        else return false;
    }
}
