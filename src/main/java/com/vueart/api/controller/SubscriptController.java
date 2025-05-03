package com.vueart.api.controller;

import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.subcription.SubscribeRequest;
import com.vueart.api.entity.Subscription;
import com.vueart.api.service.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@Tag(name="Subscription")
public class SubscriptController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public SuccessResponse subscribe(@RequestBody SubscribeRequest request) {
        subscriptionService.subscribe(request);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping
    public SuccessResponse unsubscribe(@RequestParam String subscriberId, @RequestParam String organizerId) {
        subscriptionService.unsubscribe(subscriberId, organizerId);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @GetMapping
    public List<Subscription> getSubscriptions(@RequestParam String subscriberId) {
        return subscriptionService.getSubscriptions(subscriberId);
    }
}
