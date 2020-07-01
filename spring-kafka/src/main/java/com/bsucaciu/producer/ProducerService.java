package com.bsucaciu.producer;

import com.bsucaciu.config.AdminConfig;
import com.bsucaciu.dto.RandomText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class ProducerService {

    private KafkaTemplate<String, Object> template;
    private AdminConfig adminConfig;

    @Autowired
    public ProducerService(KafkaTemplate<String, Object> template, AdminConfig adminConfig) {
        this.template = template;
        this.adminConfig = adminConfig;
    }

    @Async
    public void sendMessage(RandomText randomText) {
        try {
            log.info("Sending a random text composed of " + randomText.getAmount() + " paragraphs to the \"" + adminConfig.getTopicName() + "\" topic");
            this.template.send(adminConfig.getTopicName(), "{}", randomText).get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Failed to send message", e);
        }
    }
}
