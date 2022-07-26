package com.github.gustavoflor.springkafkaretrier;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagePublisher {
    private final StreamBridge streamBridge;

    public void send(final Payload message) {
        streamBridge.send("Event-out-0", message);
    }
}
