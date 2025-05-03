package com.vueart.api.controller;

import com.vueart.api.dto.response.notification.NotificationResponse;
import com.vueart.api.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getNotifications(@RequestParam String userId,
                                                       @RequestParam(defaultValue = "false") boolean onlyUnread) {
        return notificationService.getNotifications(userId, onlyUnread);
    }

    @PatchMapping("/{id}/read")
    public void markAsRead(@PathVariable String id) {
        notificationService.markAsRead(id);
    }

    @PatchMapping("/read-all")
    public void markAllAsRead(@RequestParam String userId) {
        notificationService.markAllAsRead(userId);
    }
}