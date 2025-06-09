package com.vueart.api.consumer;

import com.vueart.api.dto.messaging.ReservationMessage;
import com.vueart.api.entity.Reservation;
import com.vueart.api.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConsumer {
    private final ReservationService reservationService;

    @RabbitListener(queues = "reservation.queue")
    public void consumeReservation(ReservationMessage msg) {
        reservationService.processReservation(msg.getUserId(), msg.getTicketId(), msg.getQuantity());
    }
}
