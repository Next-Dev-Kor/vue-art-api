package com.vueart.api.controller;

import com.vueart.api.common.response.CommonApiResponse;
import com.vueart.api.common.response.SuccessResponse;
import com.vueart.api.core.enums.Code;
import com.vueart.api.dto.request.subcription.SubscribeRequest;
import com.vueart.api.dto.response.subscription.SubscriptionResponse;
import com.vueart.api.entity.Subscription;
import com.vueart.api.service.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@Tag(name="Subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public SuccessResponse subscribe(@RequestBody SubscribeRequest request) {
        subscriptionService.subscribe(request);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping
    public SuccessResponse unsubscribe(@RequestParam Long subscriberId, @RequestParam Long organizerId) {
        subscriptionService.unsubscribe(subscriberId, organizerId);
        return new SuccessResponse(Code.ApiResponseCode.SUCCESS.getMessage());
    }

    @GetMapping
    public CommonApiResponse<List<SubscriptionResponse>> getSubscriptions(@RequestParam Long subscriberId) {
        List<SubscriptionResponse> subscriptionResponse = subscriptionService.getSubscriptions(subscriberId);
        return new CommonApiResponse<>(HttpStatus.OK.value(), Code.ApiResponseCode.SUCCESS.getCode(), subscriptionResponse);
    }
}
