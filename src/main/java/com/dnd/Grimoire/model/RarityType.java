package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RarityType {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    VERY_RARE("very_rare"),
    LEGENDARY("legendary");

    private final String key;

    RarityType(String key) {
        this.key = key;
    }

    @JsonCreator
    public static RarityType fromString(String key) {
        return key == null
                ? null
                : RarityType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return key;
    }
}
