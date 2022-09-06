package com.dnd.Grimoire.model.time;

public enum Month {
    DEEPWINTER(1, "Deepwinter"),
    JAGFROST(2, "Jagfrost"),
    GLOAMING(3, "Gloaming"),
    RAINTIME(4, "Raintime"),
    MELLOWING(5, "Mellowing"),
    OUTBLOOM(6, "Outbloom"),
    SUMMERTIME(7, "Summertime"),
    HIGHSUN(8, "Highsun"),
    LESSUN(9, "Lessun"),
    LEAFFALL(10, "Leaffall"),
    SHIVERNIGHT(11, "Shivernight"),
    WINTERTIDE(12, "Wintertide");

    private final int month;

    private final String name;

    Month(int month, String name) {
        this.month = month;
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }
}
