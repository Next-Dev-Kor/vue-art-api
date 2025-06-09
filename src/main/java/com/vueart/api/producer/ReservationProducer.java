package com.vueart.api.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vueart.api.config.rabbitmq.RabbitConfig;
import com.vueart.api.dto.messaging.ReservationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${reservation.exchange}")
    private String exchange;

    @Value("${reservation.routingKey}")
    private String routingKey;
    public void sendReservationMessage(ReservationMessage msg) {

        try {
            String jsonMsg = objectMapper.writeValueAsString(msg);
            rabbitTemplate.convertAndSend(exchange, routingKey, jsonMsg);
        } catch (JsonProcessingException e) {
            // 예외 처리
            System.out.println("JsonProcessingException: " + e.getMessage());
        }

    }
}
