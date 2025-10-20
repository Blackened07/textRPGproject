package main.java.GameProcesses.Plot.Locations;

public enum Weather {
    SUN, /// +3 stam
    RAIN, /// -2 str
    FOG, /// -4 ag
    WIND, ///  -2 int
    SUN_AND_WIND, /// +2 stam, -1int
    RAIN_AND_WIND, /// -2 str, -2 int
    RAIN_AND_FOG, /// -1 str, -3 ag
}
