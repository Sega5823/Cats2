package org.example;

import entity.Cat;
import entity.CatDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
//
//    @Bean
//    public ProducerFactory<String, Cat> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Cat> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

//    @Bean
//    public ProducerFactory<String, ArrayList<CatDTO>> producerFactoryForArray() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, ArrayList<CatDTO>> kafkaTemplateForArray() {
//        return new KafkaTemplate<>(producerFactoryForArray());
//    }

    @Bean
    public ProducerFactory<String, Integer> producerFactoryInteger() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Integer> kafkaTemplateInteger() {
        return new KafkaTemplate<>(producerFactoryInteger());
    }

    @Bean
    public ProducerFactory<String, List<Integer>> producerFactoryIntList() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, List<Integer>> kafkaTemplateIntList() {
        return new KafkaTemplate<>(producerFactoryIntList());
    }

}

