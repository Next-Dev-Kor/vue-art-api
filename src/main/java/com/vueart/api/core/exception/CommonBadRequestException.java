package com.vueart.api.core.exception;

import com.vueart.api.core.enums.Code.ErrorCode;
import lombok.Getter;

@Getter
public class CommonBadRequestException extends RuntimeException {
    private final String code;

    public CommonBadRequestException(ErrorCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }
}
