package com.dnd.Grimoire.model.time;

public enum Season {
    SPRING(1, "Spring"),
    SUMMER(2, "Summer"),
    FALL(3, "Fall"),
    WINTER(4, "Winter");

    private final int weekDay;

    private final String name;

    Season(int weekDay, String name) {
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
