package com.vueart.api.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(
        @NotNull @Valid
        Long userId,

        @NotNull(message = "이전 비밀번호는 필수입니다.")
        String currentPassword,

        @NotNull(message = "새 비밀번호는 필수입니다.")
        String newPassword,

        @NotNull(message = "새 비밀번호 확인은 필수입니다.")
        String confirmPassword
) {
}
