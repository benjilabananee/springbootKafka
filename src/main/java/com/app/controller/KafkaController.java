package com.app.controller;

import com.app.utils.tickers.TickersMetaData;
import com.app.kafka.service.producer.KafkaSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final KafkaSender kafkaSender;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @Value("${rapidapi.tickers.base.url}")
    private String tickersApiUrl;

    public KafkaController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam int pageNumber) {
        try {
            HttpResponse<String> response = fetchTickerMetaData(pageNumber);
            TickersMetaData tickersMetaData = parseResponse(response.body());

            kafkaSender.sendMessage(tickersMetaData);

            return "Message was successfully sent to Kafka!";

        } catch (Exception e) {
            return "Failed to send message to Kafka: " + e.getMessage();
        }
    }

    private HttpResponse<String> fetchTickerMetaData(int pageNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tickersApiUrl + pageNumber))
                .header("x-rapidapi-key", rapidApiKey)
                .header("x-rapidapi-host", rapidApiHost)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private TickersMetaData parseResponse(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, TickersMetaData.class);
    }
}
