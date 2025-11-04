package main.java.Items;

public class QuestItem extends Item{

    private final String FOR_QUEST;

    public QuestItem(String name, int cost, int weight, Types type, String FOR_QUEST) {
        super(name, cost, weight, type);
        this.FOR_QUEST = FOR_QUEST;
    }

    public String getFOR_QUEST() {
        return FOR_QUEST;
    }
}
