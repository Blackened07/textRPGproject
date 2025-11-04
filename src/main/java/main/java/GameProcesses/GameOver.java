package main.java.GameProcesses;

import main.java.GameProcesses.Services.InvalidCommandException;

public class GameOver {
    private static boolean isGameOver;
    static Game game;
    public GameOver(Game game) {
        this.game = game;
        isGameOver = true;
    }

    public static void setIsGameOver(boolean isGameOver) {
        GameOver.isGameOver = isGameOver;
    }

    public static void setGame () throws InvalidCommandException {
        setIsGameOver(false);
        check();
    }
    private static void check() throws InvalidCommandException {
        if (!isGameOver) game.startGame();
    }

}
