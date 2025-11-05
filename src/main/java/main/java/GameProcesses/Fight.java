package main.java.GameProcesses;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;
import main.java.GameProcesses.Services.GameScanner;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.RandomNumberGenerator;

import java.util.Scanner;

public interface Fight extends PrintableInterfaces, RandomNumberGenerator, GameScanner {

    default void fight(Organism player, Organism creature, Scanner sc, Events event, String questItemName) throws InvalidCommandException {
        String userInput;
        boolean isPlayerTurn;
        print("На вас напал " + creature.getNAME() + "\nБой начался!");
        isPlayerTurn = chanceToAttackFirst(player);

        while (player.getCurrentHealth() > 0 && creature.getCurrentHealth() > 0) {
            if (isPlayerTurn) {
                userInput = fightScanner(sc);
                switch (userInput) {
                    case "/a" -> {
                        player.autoAttack(player, creature);
                        print("Вы нанесли " + Math.floor(player.getFullAttackPower()) + " урона.\nТекущее здоровье противника " + Math.floor(creature.getCurrentHealth()));
                    }
                    case "/backpack" -> player.useItemsFromBackPack(player, sc, event);
                }
            } else {
                creature.autoAttack(creature, player);
                print(creature.getNAME() + "нанёс " + Math.floor(creature.getFullAttackPower()) + " урона.\n Ваше текущее здоровье " + Math.floor(player.getCurrentHealth()));
            }
            isPlayerTurn = !isPlayerTurn;
        }
        if (player.getCurrentHealth() <= 0) {
            print("Game Over");
            GameOver.setGame();
        } else if (creature.getCurrentHealth() <= 0) {
            print("Fight is over");
            if ( player.getQuestObjectiveName(creature.findQuestItemInCreaturesBackPack()) && !player.getQuestObjectiveIsComplete(creature.findQuestItemInCreaturesBackPack())) {
                player.addToBackPack(creature.addQuestItemToPlayerFromCreature());
            }
            player.addToBackPack(creature.getFromBackPackWithIndex((int) (Math.random() * creature.getSize() - 1)));
            player.setGold((int) (Math.random() * creature.getGold()));
            player.checkExp(45);
        }
    }

    default boolean chanceToAttackFirst(Organism player) {
        if (player.getBaseAgility() * 2 > whoStart()) return true;
        else return false;
    }
}
