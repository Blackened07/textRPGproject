package main.java.Characters;

import main.java.GameProcesses.Plot.Quests.ActiveQuests;

import java.util.*;

public class QuestJournal {
    private Set<ActiveQuests> questLog;

    public QuestJournal() {
        this.questLog = new HashSet<>();
    }
    public void addQuestToJournal (ActiveQuests quest) {
        questLog.add(quest);
    }

    public String ShowAllQuests () {
        StringBuilder sb = new StringBuilder();

        questLog.forEach(e -> sb.append(" * ").
                append(e.getQuestName()).append(" - ").
                append(e.getQuestText()).append(" - ").
                append(e.getQuestObjectiveCounter()).append("/").
                append(e.getQuestObjectiveFull()));
        return sb.toString();
    }
    public boolean getQuestObjective (String name) {
        for (ActiveQuests q : questLog) {
            if (q.getQuestName().equals(name)) {
                return q.isComplete();
            }
        }
        return false;
    }
    public ActiveQuests getQuest (String name) {
        for (ActiveQuests q : questLog) {
            if (q.getQuestName().equals(name)) {
                return q;
            }
        }
        return null;
    }
}
