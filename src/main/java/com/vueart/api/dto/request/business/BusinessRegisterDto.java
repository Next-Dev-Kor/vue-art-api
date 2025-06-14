package com.vueart.api.dto.request.business;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BusinessRegisterDto {
    @Schema(description = "사업자 등록번호", example = "2208162517")
    private String b_no;
}
