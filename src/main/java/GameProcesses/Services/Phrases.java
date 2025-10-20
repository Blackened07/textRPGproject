package main.java.GameProcesses.Services;

public interface Phrases {
    default void print(String text) {
        System.out.println(text);
    }
}
