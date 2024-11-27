package com.app.controller;

import com.app.utils.tickers.TickersMetaData;
import com.app.kafka.service.producer.KafkaSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaSender kafkaSender;

    public KafkaController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping("/send")
    public String sendMessage() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yahoo-finance15.p.rapidapi.com/api/v2/markets/tickers?page=1&type=STOCKS"))
                .header("x-rapidapi-key", "c9e3c4a404mshd3abe577208e70dp18a17ajsnd7ad306ea743")
                .header("x-rapidapi-host", "yahoo-finance15.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        TickersMetaData quoteResponse = mapper.readValue(response.body(), TickersMetaData.class);

        kafkaSender.sendMessage(quoteResponse);
        return "Message was successfully sent to Kafka!";
    }
}