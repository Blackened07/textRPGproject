package main.java.GameProcesses.Plot.Quests;

public class ActiveQuests {
    private final String questName;
    private final String questText;
    private final String questObjective;
    private int questObjectiveCounter;
    private int questObjectiveFull;
    private boolean IsComplete;

    public ActiveQuests(String questName, String questText, int questObjectiveFull, String questObjective) {
        this.questName = questName;
        this.questText = questText;
        this.questObjectiveFull = questObjectiveFull;
        this.questObjective = questObjective;
        this.IsComplete = false;
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

    private void setComplete() {
        System.out.println("FINISH");
        this.IsComplete = true;
    }

    public int getQuestObjectiveCounter() {
        return questObjectiveCounter;
    }

    public int getQuestObjectiveFull() {
        return questObjectiveFull;
    }

    public void setQuestObjectiveCounter(int counter) {
        System.out.println("In active setter");
        this.questObjectiveCounter += counter;
        checkQuestObjective();
    }

    public String getQuestObjective() {
        return questObjective;
    }

    public void checkQuestObjective () {
        if (getQuestObjectiveCounter() == getQuestObjectiveFull()) {
            System.out.println("In complete setter");
            setComplete();
        }

    }
}
