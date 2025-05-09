package com.vueart.api.repository.notification;

import com.vueart.api.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // 특정 사용자 전체 알림 조회
    List<Notification> findByUser_UserId(String userId);

    // 읽지 않은 알림만 조회
    List<Notification> findByUser_UserIdAndIsReadFalse(String userId);
}
