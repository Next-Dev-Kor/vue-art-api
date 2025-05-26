package com.vueart.api.dto.request.exhibitioninfo;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExhibitionInfoRequest(
        @NotNull(message = "title은 필수입니다.")
        String title,
        @NotNull(message = "summary는 필수입니다.")
        String summary,
        @NotNull(message = "description는 필수입니다.")
        String description,
        @NotNull(message = "startDate는 필수입니다.")
        LocalDate startDate,
        @NotNull(message = "startDate는 필수입니다.")
        LocalDate endDate,
        @NotNull(message = "location은 필수입니다.")
        String location,
        @NotNull(message = "isPresale은 필수입니다.")
        Boolean isPresale,
        @NotNull(message = "categoryId는 필수입니다.")
        Long categoryId
) {
}
