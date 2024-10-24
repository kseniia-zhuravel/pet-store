package org.example.backend.models.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ErrorMessage {

    SOMETHING_BAD_HAPPENED_ERROR("something bad happened"),
    ORDER_NOT_FOUND_ERROR("Order not found"),
    ORDER_NOT_FOUND_FOR_DELETE_ERROR("Order Not Found"),
    NUMBER_FORMAT_EXCEPTION_ERROR("java.lang.NumberFormatException: For input string: \"null\"");

    @JsonValue
    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

}
