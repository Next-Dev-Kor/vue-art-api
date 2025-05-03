package com.vueart.api.dto.request.subcription;

import lombok.Getter;
import lombok.Setter;

public record SubscribeRequest (
    String subscriberId,
    String organizerId
){}

