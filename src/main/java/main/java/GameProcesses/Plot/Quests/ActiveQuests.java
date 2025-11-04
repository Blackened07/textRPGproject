package main.java.GameProcesses.Plot.Quests;

public class ActiveQuests {
    private final String questName;
    private final String questText;
    private int questObjectiveCounter;
    private int questObjectiveFull;
    private boolean IsComplete;

    public ActiveQuests(String questName, String questText, int questObjectiveFull) {
        this.questName = questName;
        this.questText = questText;
        this.IsComplete = false;
        this.questObjectiveFull = questObjectiveFull;
        this.questObjectiveCounter = 0;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestText() {
        return questText;
    }

    public boolean isComplete() {
        return IsComplete;
    }

    private void setComplete(boolean complete) {
        IsComplete = complete;
    }

    public int getQuestObjectiveCounter() {
        return questObjectiveCounter;
    }

    public int getQuestObjectiveFull() {
        return questObjectiveFull;
    }

    public void setQuestObjectiveCounter(int questObjectiveCounter) {
        if (getQuestObjectiveCounter() < getQuestObjectiveFull()) this.questObjectiveCounter += questObjectiveCounter;
        else if (getQuestObjectiveCounter() == getQuestObjectiveFull()) setComplete(true);
    }
}
