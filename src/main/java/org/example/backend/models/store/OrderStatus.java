package org.example.backend.models.store;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderStatus {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    @JsonValue
    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
