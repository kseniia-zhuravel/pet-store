package org.example.backend.factories.common;

import org.example.backend.models.common.Error;
import org.example.backend.models.common.ErrorMessage;

import static org.example.backend.models.common.ErrorMessage.SOMETHING_BAD_HAPPENED_ERROR;
import static org.example.backend.models.common.ErrorType.ERROR_TYPE;
import static org.example.backend.models.common.ErrorType.UNKNOWN_TYPE;

public class ErrorFactory {

    public static Error error500() {
        return Error.builder()
                .code(500)
                .type(UNKNOWN_TYPE.getValue())
                .message(SOMETHING_BAD_HAPPENED_ERROR.getValue())
                .build();
    }

    public static Error error404(ErrorMessage message) {
        return Error.builder()
                .code(1)
                .type(ERROR_TYPE.getValue())
                .message(message.getValue())
                .build();
    }

    public static Error error404WithTypeUnknown(ErrorMessage message) {
        return Error.builder()
                .code(404)
                .type(UNKNOWN_TYPE.getValue())
                .message(message.getValue())
                .build();
    }

}
