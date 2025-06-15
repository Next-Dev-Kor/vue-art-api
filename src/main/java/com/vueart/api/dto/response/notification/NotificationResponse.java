package com.vueart.api.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NotificationResponse{
    Long id;
    String message;
    boolean isRead;
    LocalDateTime createdAt;
}

