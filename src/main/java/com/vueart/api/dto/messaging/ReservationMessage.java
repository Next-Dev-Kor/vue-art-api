package com.vueart.api.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationMessage {
    private Long userId;
    private Long ticketId;
    private int quantity;
}
