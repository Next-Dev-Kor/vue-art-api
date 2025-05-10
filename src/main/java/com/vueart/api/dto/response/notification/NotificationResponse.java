package com.vueart.api.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        String message,
        boolean isRead,
        LocalDateTime createdAt
) {}
