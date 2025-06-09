package com.vueart.api.config.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${reservation.queue}")
    private String queue;

    @Value("${reservation.exchange}")
    private String exchange;

    @Value("${reservation.routingKey}")
    private String routingKey;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue reservationQueue() {
        return new Queue(queue, true); // durable = true
    }

    @Bean
    public Binding reservationBinding(Queue reservationQueue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(reservationQueue)
                .to(directExchange)
                .with(routingKey);
    }
}
