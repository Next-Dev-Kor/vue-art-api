package com.vueart.api.repository.subscription;

import com.vueart.api.entity.Subscription;
import com.vueart.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsBySubscriberIdAndOrganizerId(Long subscriberId, Long organizerId);
    List<Subscription> findBySubscriberId(Long subscriberId);
    void deleteBySubscriberIdAndOrganizerId(Long subscriberId, Long organizerId);
}
