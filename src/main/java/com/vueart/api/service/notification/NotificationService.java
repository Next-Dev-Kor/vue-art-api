package com.vueart.api.service.notification;

import com.vueart.api.dto.response.notification.NotificationResponse;
import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getNotifications(String userId, boolean onlyUnread);
    void markAsRead(String id);
    void markAllAsRead(String userId);
}
