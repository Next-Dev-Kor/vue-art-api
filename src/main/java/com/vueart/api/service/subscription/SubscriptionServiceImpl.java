package com.vueart.api.service.subscription;

import com.vueart.api.core.enums.Code;
import com.vueart.api.core.exception.VueArtApiException;
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
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_SUBSCRIBER_ID));
        User organizer = userRepository.findByUserId(request.organizerId())
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_ORGANIZER_ID));

        boolean exists = subscriptionRepository.existsBySubscriberIdAndOrganizerId(subscriber.getId(), organizer.getId());
        if (!exists) {
            throw new VueArtApiException(Code.ErrorCode.ALREADY_REGISTERED_SUBSCRIPTION);
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
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_SUBSCRIBER_ID));
        User organizer = userRepository.findByUserId(organizerId)
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_ORGANIZER_ID));

        boolean exists = subscriptionRepository.existsBySubscriberIdAndOrganizerId(subscriber.getId(), organizer.getId());
        if (!exists) {
            throw new VueArtApiException(Code.ErrorCode.NOT_FOUND_SUBSCRIPTION);
        }

        subscriptionRepository.deleteBySubscriberIdAndOrganizerId(subscriber.getId(), organizer.getId());
    }

    @Override
    public List<Subscription> getSubscriptions(String subscriberId) {

        User subscriber = userRepository.findByUserId(subscriberId)
                .orElseThrow(() -> new VueArtApiException(Code.ErrorCode.NOT_FOUND_SUBSCRIBER_ID));
        return subscriptionRepository.findBySubscriberId(subscriber.getId());
    }
}
