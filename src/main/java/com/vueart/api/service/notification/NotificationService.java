package com.vueart.api.service.notification;

import com.vueart.api.dto.response.category.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    public void markAsRead(String id) {
    }

    public List<NotificationResponse> getNotifications(String userId, boolean onlyUnread) {
        return List.of();
    }

    public void markAllAsRead(String userId) {
    }
}
