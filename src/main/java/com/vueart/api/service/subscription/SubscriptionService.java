package com.vueart.api.service.subscription;

import com.vueart.api.dto.request.subcription.SubscribeRequest;
import com.vueart.api.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SubscriptionService {
    void subscribe(SubscribeRequest request);

    @Transactional
    void unsubscribe(String subscriberId, String organizerId);

    List<Subscription> getSubscriptions(String subscriberId);
}
