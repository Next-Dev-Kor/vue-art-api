package com.vueart.api.service.notification;

import com.vueart.api.entity.Notification;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications(String userId, boolean onlyUnread);

    void markAsRead(Long id);

    void markAllAsRead(String userId);
}
