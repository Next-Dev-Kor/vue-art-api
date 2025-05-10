package com.vueart.api.service.notification;

import com.vueart.api.core.enums.Code;
import com.vueart.api.core.exception.VueArtApiException;
import com.vueart.api.entity.User;
import com.vueart.api.repository.category.CategoryRepository;
import com.vueart.api.repository.notification.NotificationRepository;
import com.vueart.api.entity.Notification;
import com.vueart.api.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Notification> getNotifications(Long userId, boolean onlyUnread) {

        User user = userRepository.findById(userId).orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_USER_ID));
        if (onlyUnread) {
            return notificationRepository.findByUser_UserIdAndIsReadFalse(userId);
        }
        return notificationRepository.findByUser_UserId(userId);
    }


    @Transactional
    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_NOTIFICATION));
        notification.markAsRead();
    }


    @Override
    @Transactional
    public void markAllAsRead(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new VueArtApiException(Code.ErrorCode.NOT_FOUND_USER_ID);
        }

        List<Notification> unreadList = notificationRepository.findByUser_UserIdAndIsReadFalse(userId);
        unreadList.forEach(Notification::markAsRead);
    }
}
