package com.vueart.api.service.notification;

import com.vueart.api.repository.notification.NotificationRepository;
import com.vueart.api.entity.Notification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotifications(String userId, boolean onlyUnread) {
        if (onlyUnread) {
            return notificationRepository.findByUser_UserIdAndIsReadFalse(userId);
        }
        return notificationRepository.findByUser_UserId(userId);
    }


    @Transactional
    @Override
    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다."));
        notification.markAsRead();
    }


    @Override
    @Transactional
    public void markAllAsRead(String userId) {
        List<Notification> unreadList = notificationRepository.findByUser_UserIdAndIsReadFalse(userId);
        unreadList.forEach(Notification::markAsRead);
    }
}
