package com.vueart.api.service.notification;

import com.vueart.api.dto.response.notification.NotificationResponse;
import com.vueart.api.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    public List<NotificationResponse> getNotifications(String userId, boolean onlyUnread){
        System.out.println(userId);
        return List.of();
    }

    @Override
    public void markAsRead(String id) {
        System.out.println(id);
    }

    @Override
    public void markAllAsRead(String userId) {
        System.out.println(userId);
    }
}
