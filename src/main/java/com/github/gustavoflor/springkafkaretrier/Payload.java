package com.github.gustavoflor.springkafkaretrier;

public record Payload(String key, int retriesToAccept) {
}
