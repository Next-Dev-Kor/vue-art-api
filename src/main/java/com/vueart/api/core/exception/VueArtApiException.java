package com.vueart.api.core.exception;

import com.vueart.api.core.enums.Code.ErrorCode;
import lombok.Getter;

@Getter
public class VueArtApiException extends RuntimeException {
    private static final long serialVersionUID = -2899447744777394044L;

    private final ErrorCode errorCode;

    public VueArtApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public VueArtApiException(String message) {
        super(message);
        errorCode = null;
    }

    public VueArtApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
