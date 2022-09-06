package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VisibilityType {
    DM("dm"),
    PARTY("party"),
    PLAYER("player");

    private final String key;

    VisibilityType(String key) {
        this.key = key;
    }

    @JsonCreator
    public static VisibilityType fromString(String key) {
        return key == null
                ? null
                : VisibilityType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return key;
    }
}
