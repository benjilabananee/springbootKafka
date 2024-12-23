package com.app.controller;

import com.app.DTO.TickerLastOppAggregatedDTO;
import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.kafka.service.producer.KafkaSender;
import com.app.kafka.utils.schema.tickersMetaData.TickersMetadata;
import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.service.TickerLastOppService;
import com.app.mongo.service.TickersCommonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final TickersCommonService tickersCommonService;
    private final TickerLastOppService tickerLastOppService;
    private static final String HEADER_API_KEY = "x-rapidapi-key";
    private static final String HEADER_API_HOST = "x-rapidapi-host";
    private final KafkaSender kafkaSender;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @Value("${rapidapi.tickers.lastopp.base.url}")
    private String tickersLastOppApiUrl;

    @Value("${rapidapi.tickers.metadata.base.url}")
    private String tickerMetaDataApiUrl;

    public KafkaController(TickersCommonService tickersCommonService, TickerLastOppService tickerLastOppService, KafkaSender kafkaSender) {
        this.tickersCommonService = tickersCommonService;
        this.tickerLastOppService = tickerLastOppService;
        this.kafkaSender = kafkaSender;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/sendTickersLastOpp")
    public String sendLastOppMessage(@RequestParam int pageNumber) {
        try {
            String url = tickersLastOppApiUrl + pageNumber;
            String responseBody = sendHttpRequest(url);

            TickersLastOpp tickersLastOpp = objectMapper.readValue(responseBody, TickersLastOpp.class);
            kafkaSender.sendTickerLastOppMessage(tickersLastOpp);

            logger.info("TickersLastOpp message successfully sent to Kafka.");
            return "Message was successfully sent to Kafka!";
        } catch (Exception e) {
            logger.error("Failed to send TickersLastOpp message to Kafka.", e);
            return "Failed to send message to Kafka: " + e.getMessage();
        }
    }

    @PostMapping("/sendMetadataMessage")
    public String sendMetadataMessage() {
        try {
            String responseBody = sendHttpRequest(tickerMetaDataApiUrl);

            TickersMetadata tickersMetadata = objectMapper.readValue(responseBody, TickersMetadata.class);
            kafkaSender.sendTickerMetaDataOppMessage(tickersMetadata);

            logger.info("TickersMetadata message successfully sent to Kafka.");
            return "Message was successfully sent to Kafka!";
        } catch (Exception e) {
            logger.error("Failed to send TickersMetadata message to Kafka.", e);
            return "Failed to send message to Kafka: " + e.getMessage();
        }
    }
    private String sendHttpRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(HEADER_API_KEY, rapidApiKey)
                .header(HEADER_API_HOST, rapidApiHost)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Failed request with status code: " + response.statusCode());
        }
        return response.body();
    }

    @GetMapping("/tickers-with-info")
    public List<TickerLastOppAggregatedDTO> getTickersWithInfo() {
        return tickersCommonService.getTickersWithInfo();
    }

    @GetMapping("/tickers")
    public List<TickersLastOppMongo> getTickersByLastSale(@RequestParam Double minLastSale) {
        return tickerLastOppService.getTickersByLastSaleGreaterThanEqual(minLastSale);
    }
}
