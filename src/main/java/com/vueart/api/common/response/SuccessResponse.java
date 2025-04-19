package com.vueart.api.common.response;

import com.vueart.api.core.enums.Code;
import lombok.Getter;

@Getter
public class SuccessResponse {
    private final int status;
    private final String code;
    private final String message;

    public SuccessResponse(String message) {
        this.status = 200;
        this.code = Code.ApiResponseCode.SUCCESS.getCode();
        this.message = message;
    }
}
