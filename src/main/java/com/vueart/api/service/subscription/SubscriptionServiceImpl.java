package com.vueart.api.service.subscription;

import com.vueart.api.dto.request.subcription.SubscribeRequest;
import com.vueart.api.entity.Subscription;
import com.vueart.api.entity.User;
import com.vueart.api.repository.subscription.SubscriptionRepository;
import com.vueart.api.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void subscribe(SubscribeRequest request) {

        User subscriber = userRepository.findByUserId(request.subscriberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User organizer = userRepository.findByUserId(request.organizerId())
                .orElseThrow(() -> new IllegalArgumentException("주최자를 찾을 수 없습니다."));

        if (subscriptionRepository.existsBySubscriberIdAndOrganizerId(subscriber.getId(), organizer.getId())) {
            throw new IllegalStateException("이미 구독 중입니다.");
        }
        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.setOrganizer(organizer);
        subscription.setSubscribedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }

    @Transactional
    @Override
    public void unsubscribe(String subscriberId, String organizerId) {

        User subscriber = userRepository.findByUserId(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        User organizer = userRepository.findByUserId(organizerId)
                .orElseThrow(() -> new IllegalArgumentException("주최자를 찾을 수 없습니다."));
        subscriptionRepository.deleteBySubscriberIdAndOrganizerId(subscriber.getId(), organizer.getId());
    }

    @Override
    public List<Subscription> getSubscriptions(String subscriberId) {

        User subscriber = userRepository.findByUserId(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return subscriptionRepository.findBySubscriberId(subscriber.getId());
    }
}
