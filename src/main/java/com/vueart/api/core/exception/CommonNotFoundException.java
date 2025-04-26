package com.vueart.api.core.exception;

import com.vueart.api.core.enums.Code.ErrorCode;
import lombok.Getter;

@Getter
public class CommonNotFoundException extends RuntimeException {

    private final String code;

    /**
     * 메시지만 받는 경우. 이 경우, 코드는 E404로 고정
     *
     * @param message 응답 메시지
     */
    public CommonNotFoundException(String message) {
        super(message);
        this.code = ErrorCode.COMMON_NOT_FOUND_EXCEPTION.getCode();
    }

    /**
     * ResponseCode를 받는 경우. 이 경우 메시지와 코드는 ResponseCode 내 메시지 / 코드로 설정
     *
     * @param responseCode ResponseCode Enum
     */
    public CommonNotFoundException(ErrorCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

}
