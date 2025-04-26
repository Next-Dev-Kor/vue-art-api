package com.vueart.api.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FailResponse {
    private int status;
    private String code;

    public FailResponse(int status, String code) {
        this.status = status;
        this.code = code;
    }
}