package main.java.GameProcesses.Plot.Locations;

import java.util.Objects;

public class Events {
    private final String eventName; /// call event with his name
    private String plotText;
    private String choose;
    private Location location;

    public Events(String eventName, String plotText, String choose, Location location) {
        this.eventName = eventName;
        this.plotText = plotText;
        this.choose = choose;
        this.location = location;
    }

    public void setTextAndCommandsDependingOnThePlot() {

    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Events events = (Events) object;
        return Objects.equals(eventName, events.eventName) && Objects.equals(plotText, events.plotText) && Objects.equals(choose, events.choose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, plotText, choose);
    }
}
