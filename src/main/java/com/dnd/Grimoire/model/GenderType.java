package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderType {
    MALE("male"),
    FEMALE("female");

    private final String key;

    GenderType(String key) {
        this.key = key;
    }

    @JsonCreator
    public static GenderType fromString(String key) {
        return key == null
                ? null
                : GenderType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return key;
    }
}
