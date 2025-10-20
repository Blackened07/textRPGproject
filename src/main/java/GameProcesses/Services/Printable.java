package main.java.GameProcesses.Services;

import main.java.Characters.Organism;
import main.java.GameProcesses.Plot.Locations.Events;

public interface Printable {
    default void print(String text) {
        System.out.println(text);
    }
    default void printEvent(int index) {}
    default void features (Organism player) {
        player.setCurrentHealth(player.getHealthMaxValue());
        player.setCurrentMana(player.getManaMaxValue());
        System.out.println("Ваши характеристикки: \nИмя: " + player.getNAME() + "\nУровень: " + player.getLevel() + "\nЗдоровье : " + player.getHealthMaxValue()
                + "\nМана: " + player.getManaMaxValue() + "\nСила: " + player.getStrength() + "\nВыносливость: " + player.getStamina() + "\nЛовкость: " + player.getAgility()
                + "\nИнтеллект: " + player.getIntellect() + "\nСила Атаки: " + player.getAttackPower() + "\nСкорость Атаки: " + player.getAttackSpeed()
                + "\nУклонение: " + player.getEvasion() + "\nСила Заклинаний: " + player.getSpellPower() + "\nСопротивление магии: " + player.getSpellResistance());
    }
}
