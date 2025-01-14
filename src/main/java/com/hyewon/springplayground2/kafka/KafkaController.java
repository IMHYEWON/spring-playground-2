package com.hyewon.springplayground2.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message) {
        kafkaTemplate.send("hyewon-topic", message);
        log.info("String message sent at {}", System.currentTimeMillis());
    }
}

