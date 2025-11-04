package main.java;

import main.java.Characters.Creature;
import main.java.GameProcesses.Game;
import main.java.GameProcesses.Plot.Quests.Quest;
import main.java.GameProcesses.Services.InvalidCommandException;
import main.java.GameProcesses.Services.PrintableInterfaces;
import main.java.GameProcesses.Services.StoryReader;
import main.java.Items.Loot;

public class Main implements PrintableInterfaces {


    private static void printHello () {
        System.out.println("""
                *********************************************************************************************************************************\
                
                Добро пожаловать в игру Adventure Text RPG. В этой игре управление осуществляется с помощью ввода текстовых команд\
               
                Чтобы посмотреть список команд введите в консоль - /help\
                
                Выбор сценария осуществляется вводом в консоль номера сценария\
                
                Для просмотра предметов в рюкзаке введите команду - /backpack. Для просмотра экипированных предметов команду - /equip\
                Для просмотра журнала квестов введите команду - /quest\
                
                Желаю вам приятной игры и да поможет вам...\
                
                *********************************************************************************************************************************""");
    }

    public static void main(String[] args) throws InvalidCommandException {

        printHello();
        StoryReader story = new StoryReader();
        Quest quest = new Quest();
        Loot loot = new Loot();

        /*Creature c = new Creature("Boar", 8, 5, 4, 5, 0, 10);
        System.out.println(c.getFromBackPackWithIndex(1).getName());*/


        Game game = new Game(story);
        game.startGame();
    }
}