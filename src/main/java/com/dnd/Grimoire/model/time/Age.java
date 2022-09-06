package com.dnd.Grimoire.model.time;

public enum Age {
    PREARCANUM(1),
    POSTARCANUM(2);

    private final int age;

    Age(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
