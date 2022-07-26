package com.github.gustavoflor.springkafkaretrier;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Slf4j
@Component("Event")
@NoArgsConstructor
public class MessageSubscriber implements Consumer<Message<Payload>> {
    private static final String ATTEMPTS_HEADER_NAME = "deliveryAttempt";

    @Override
    public void accept(final Message<Payload> message) {
        final var payload = message.getPayload();
        final var attempts = getAttempts(message);
        log.info("Received message with payload = {}", payload);
        if (attempts >= payload.retriesToAccept()) {
            log.info("Accepted message with payload = {}", payload);
        } else {
            throw new RuntimeException();
        }
    }

    private int getAttempts(Message<?> message) {
        return Optional.ofNullable(message.getHeaders().get(ATTEMPTS_HEADER_NAME, AtomicInteger.class))
            .orElse(new AtomicInteger())
            .get();
    }
}
