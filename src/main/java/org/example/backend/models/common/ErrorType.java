package org.example.backend.models.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ErrorType {

    UNKNOWN_TYPE("unknown"),
    ERROR_TYPE("error");

    @JsonValue
    private final String value;

    ErrorType(String value) {
        this.value = value;
    }

}
