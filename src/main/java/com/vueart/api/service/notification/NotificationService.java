package com.vueart.api.service.notification;

import com.vueart.api.dto.response.notification.NotificationResponse;
import com.vueart.api.entity.Notification;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getNotifications(Long userId, boolean onlyUnread);

    void markAsRead(Long notificationId);

    void markAllAsRead(Long userId);
}
