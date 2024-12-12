package com.app.kafka.config;

import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.kafka.utils.schema.tickersMetaData.TickersMetadata;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TickersLastOpp> kafkaListenerLastOppContainerFactory() {
        return createKafkaListenerLastOppContainerFactory("group_id");
    }


    public ConcurrentKafkaListenerContainerFactory<String, TickersLastOpp> createKafkaListenerLastOppContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, TickersLastOpp> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerLastOppFactory(groupId));
        return factory;
    }


    public DefaultKafkaConsumerFactory<String, TickersLastOpp> consumerLastOppFactory(String groupId) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(TickersLastOpp.class));
    }

    //////////////////////////////////second topic config//////////////////////////////////////////////
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TickersMetadata> metadataTickerKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory("group_id"); // Set a default groupId
    }

    public ConcurrentKafkaListenerContainerFactory<String, TickersMetadata> createKafkaListenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, TickersMetadata> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(metadataConsumerFactory(groupId));
        return factory;
    }

    public DefaultKafkaConsumerFactory<String, TickersMetadata> metadataConsumerFactory(String groupId) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(TickersMetadata.class));
    }
}
