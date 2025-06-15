package com.vueart.api.controller;

import com.vueart.api.common.response.CommonApiResponse;
import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.response.notification.NotificationResponse;
import com.vueart.api.service.notification.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@Tag(name="Notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public CommonApiResponse<List<NotificationResponse>> getNotifications(@RequestParam Long userId,
                                               @RequestParam(defaultValue = "false") boolean onlyUnread) {
        List<NotificationResponse> notificationResponse = notificationService.getNotifications(userId, onlyUnread);
        return new CommonApiResponse<>(HttpStatus.OK.value(), Code.ApiResponseCode.SUCCESS.getCode(), notificationResponse);
    }

    @PatchMapping("/{id}/read")
    public SuccessResponse markAsRead(@PathVariable("id") Long notificationId) {
        notificationService.markAsRead(notificationId);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @PatchMapping("/read-all")
    public SuccessResponse markAllAsRead(@RequestParam Long userId) {
        notificationService.markAllAsRead(userId);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }
}