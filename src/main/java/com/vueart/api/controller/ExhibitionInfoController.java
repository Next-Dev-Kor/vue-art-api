package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.exhibitioninfo.ExhibitionInfoRequest;
import com.vueart.api.service.exhibitioninfo.ExhibitionInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exhibition")
@Slf4j
@Tag(name = "Exhibition", description = "전시회 정보 관련 API")
public class ExhibitionInfoController {
    private final ExhibitionInfoService exhibitionInfoService;

    @Operation(
            summary = "전시회 정보 저장",
            description = "제목, 요약, 설명, 시작일, 종료일, 지역, 예약여부, 카테고리"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "전시회 정보가 등록되었습니다.",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못 되었습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/exhibition-info")
    public SuccessResponse createExhibitionInfo(Authentication authentication, @RequestBody ExhibitionInfoRequest req) {
        exhibitionInfoService.createExhibitionInfo(req);
        return new SuccessResponse(Code.ApiResponseCode.CREATED_EXHIBITION_INFO.getMessage());
    }

    @Operation(
            summary = "전시회 정보 수정",
            description = "제목, 요약, 설명, 시작일, 종료일, 지역, 예약여부, 카테고리"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "전시회 정보가 수정되었습니다.",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못 되었습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PutMapping("/exhibition-info/{id}")
    public SuccessResponse updateExhibitionInfo(Authentication authentication, @PathVariable Long id, @RequestBody ExhibitionInfoRequest req) {
        exhibitionInfoService.updateExhibitionInfo(id, req);
        return new SuccessResponse(Code.ApiResponseCode.UPDATED_EXHIBITION_INFO.getMessage());
    }
}
