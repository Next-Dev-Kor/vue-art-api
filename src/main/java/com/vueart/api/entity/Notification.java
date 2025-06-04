package com.vueart.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String message;
    private boolean isRead = false;

    private LocalDateTime createdAt;
    private LocalDateTime readAt;

    public void markAsRead() {
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }

    // 기본 생성자 (JPA 필수)
    protected Notification() {
    }

//    // 생성자 or 빌더 패턴으로 객체 생성
//    public Notification(User user, String message) {
//        this.user = user;
//        this.message = message;
//        this.createdAt = LocalDateTime.now();
//    }
}