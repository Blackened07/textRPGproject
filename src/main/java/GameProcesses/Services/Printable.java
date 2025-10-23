package main.java.GameProcesses.Services;

public interface Printable {
    default void print(String text){System.out.println(text);}
    default void printEventWithoutCommands(int index) {}
    default void printEventTextAndCommands(int index){};

}
