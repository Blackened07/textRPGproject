package main.java.GameProcesses.Services;

import main.java.Characters.Organism;
import main.java.GameProcesses.Quests.ActiveQuests;

public interface PrintableInterfaces {
    default void features (Organism player) {
        System.out.println("Ваши характеристикки: \nИмя: " + player.getNAME() + "\nУровень: " + player.getLevel() + "\nМаксимальное Здоровье : " + player.getHealthMaxValue()
                + "\nТекущее здоровье: " + player.getCurrentHealth()
                + "\nМана: " + player.getManaMaxValue() + "\nСила: " + player.getFullStrength() +
                "\nВыносливость: " + player.getFullStamina() + "\nЛовкость: " + player.getFullAgility()
                + "\nИнтеллект: " + player.getFullIntellect() + "\nСила Атаки: " + player.getAttackPower() +
                "\nСкорость Атаки: " + player.getAttackSpeed() + "\nУклонение: " + player.getEvasion() +
                "\nСила Заклинаний: " + player.getSpellPower() + "\nСопротивление магии: " + player.getSpellResistance()
        + "\nМаксимальный вес носимых предметов: " + player.maxWeight() + "; Вес экипировки и рюкзака: " + player.sumOfEquippedWeightAndBackPackWeight()
        + "\nУровень: " +  player.getLevel() + "; Текущий опыт: " + player.getCurrentExperience() + "; Уровень: " + player.getExpNeedToLevelUp());
    }

    default void print (String text) {
        System.out.println("Game message: " + text);
    }

    default void printQuestObjectiveIs (String name, ActiveQuests quest) {
        System.out.println("Вы получили: " + name + " " + quest.getQuestObjectiveCounter() + "/" + quest.getQuestObjectiveFull());
    }
    default void printQuestComplete () {
        System.out.println("Квест завершён");
    }
}
