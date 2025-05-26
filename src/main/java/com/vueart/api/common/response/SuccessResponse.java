package com.vueart.api.common.response;

import com.vueart.api.core.enums.Code;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "성공 응답 객체")
public class SuccessResponse {
    @Schema(description = "HTTP 상태 코드", example = "200")
    private final int status;

    @Schema(description = "응답 코드", example = "SUCCESS")
    private final String code;

    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.")
    private final String message;

    public SuccessResponse(String message) {
        this.status = 200;
        this.code = Code.ApiResponseCode.SUCCESS.getCode();
        this.message = message;
    }
}
