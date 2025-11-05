package main.java.Characters;

import main.java.GameProcesses.Quests.ActiveQuests;

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
                append(e.getQuestObjective()).append(" - ").
                append(e.getQuestObjectiveCounter()).append("/").
                append(e.getQuestObjectiveFull()).append("\n"));
        return sb.toString();
    }
    public boolean getQuestObjective(String name) {
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
    public boolean getQuestObjectiveName(String name) {
        for (ActiveQuests q : questLog) {
            if (q.getQuestObjective().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public boolean findQuestInJournal (ActiveQuests aq) {
        for (ActiveQuests q : questLog) {
            if (q.getQuestName().equals(aq.getQuestName())) {
                return false;
            }
        }
        return true;
    }
    public void setQuestObjectiveCounter(String name) {
        for (ActiveQuests q : questLog) {
            if (q.getQuestObjective().equals(name)) {
                System.out.println("In journal objective Setter");
                q.setQuestObjectiveCounter(1);
            }
        }
    }

    public boolean isItemIsQuestObjective (String name) {
        for (ActiveQuests q : questLog) {
            if(q.getQuestObjective().equals(name)) {
                return getQuestObjective(q.getQuestName());
            }
        }
        return false;
    }

    public void removeQuest (String name) {
        for (ActiveQuests q : questLog) {
            if(q.getQuestName().equals(name)) {
               questLog.remove(q);
            }
        }
    }
}
