package com.vueart.api.service.notification;

import com.vueart.api.entity.Notification;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications(Long userId, boolean onlyUnread);

    void markAsRead(Long notificationId);

    void markAllAsRead(Long userId);
}
