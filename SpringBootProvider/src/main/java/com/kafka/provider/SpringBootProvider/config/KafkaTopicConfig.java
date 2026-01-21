package com.kafka.provider.SpringBootProvider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configuration = new HashMap<>();
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //delete (Se borra el mensaje luego de cierto tiempo determinado) , compact(Mantiene el mas actual)
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //Tiempo que el mensaje se retiene
        configuration.put(TopicConfig.SEGMENT_MS_CONFIG, "1073741824"); //Tiempo max del segmento
        configuration.put(TopicConfig.RETENTION_MS_CONFIG,"10000012"); //Tamaño maximo de cada mensaje


        return TopicBuilder.name("kabrodeMrd.Topic")
                .partitions(2)
                .replicas(1)
                .configs(configuration)
                .build();

    }

}
