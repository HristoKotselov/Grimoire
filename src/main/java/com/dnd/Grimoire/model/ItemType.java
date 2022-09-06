package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemType {
    WEAPON("weapon"),
    ARMOR("armor"),
    WAND("wand"),
    STAFF("staff"),
    ROD("rod"),
    RING("ring"),
    SCROLL("scroll"),
    POTION("potion"),
    PENDANT("pendant"),
    TRINKET("trinket");

    private final String key;

    ItemType(String key) {
        this.key = key;
    }

    @JsonCreator
    public static ItemType fromString(String key) {
        return key == null
                ? null
                : ItemType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return key;
    }
}
