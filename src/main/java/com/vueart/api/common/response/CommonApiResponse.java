package com.vueart.api.common.response;

import com.vueart.api.core.enums.Code.ApiResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonApiResponse {
    private int status;
    private Object data;
    private String message;

    public CommonApiResponse(int httpCode, Object data, String code) {
        this.status = httpCode;
        this.data = data;
        this.message = ApiResponseCode.getCode(code);
    }
}
