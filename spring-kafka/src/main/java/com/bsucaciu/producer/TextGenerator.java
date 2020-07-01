package com.bsucaciu.producer;

import com.bsucaciu.dto.RandomText;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TextGenerator {

    private ProducerService producerService;

    public TextGenerator(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Scheduled(fixedRate = 1000)
    public void generateRandomText() throws UnirestException {
        var paragraphs = new Random().nextInt(10) + 2;
        RandomText randomText = Unirest.get("https://www.randomtext.me/api/gibberish/p-" + paragraphs + "/5-30")
                .asObject(RandomText.class)
                .getBody();

        producerService.sendMessage(randomText);
    }
}
