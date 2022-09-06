package com.dnd.Grimoire.model.time;

public enum Weekday {
    IRUNE(1, "Irune"),
    AGRASIOR(2, "Agrasior"),
    VRAJAMAMR(3, "Vrajamar"),
    EZAHL(4, "Ezahl"),
    RYLIN(5, "Rylin"),
    UPHASTREA(6, "Uhastrea"),
    KHILENOR(7, "Khilenor");

    private final int weekDay;

    private final String name;

    Weekday(int weekDay, String name) {
        this.weekDay = weekDay;
        this.name = name;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public String getName() {
        return name;
    }
}
