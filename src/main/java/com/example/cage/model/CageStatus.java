package com.example.cage.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CageStatus {
    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static CageStatus fromString(String status) {
        return CageStatus.valueOf(status.toUpperCase());
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}
