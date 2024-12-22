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

    public <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainerFactory(
            String groupId, Class<T> valueType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId, valueType));
        return factory;
    }

    public <T> DefaultKafkaConsumerFactory<String, T> consumerFactory(
            String groupId, Class<T> valueType) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(valueType));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TickersLastOpp> kafkaListenerLastOppContainerFactory() {
        return createKafkaListenerContainerFactory("group_last_opp", TickersLastOpp.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TickersMetadata> metadataTickerKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory("group_metadata", TickersMetadata.class);
    }
}
