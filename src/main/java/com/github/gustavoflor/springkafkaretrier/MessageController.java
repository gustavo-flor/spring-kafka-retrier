package com.github.gustavoflor.springkafkaretrier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private static final String RETRIES_TO_ACCEPT_HEADER_NAME = "X-Retries-To-Accept";

    private final MessagePublisher messagePublisher;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestHeader(RETRIES_TO_ACCEPT_HEADER_NAME) int retriesToAccept) {
        final var payload = new Payload(UUID.randomUUID().toString(), retriesToAccept);
        messagePublisher.send(payload);
        log.info("Event sent with payload = {}", payload);
    }
}
