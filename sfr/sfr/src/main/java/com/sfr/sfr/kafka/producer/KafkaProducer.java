package com.sfr.sfr.kafka.producer;


import com.sfr.sfr.kafka.schema.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, Recipe> producer;
    private final String topicName = "recipe";

    public KafkaProducer(KafkaTemplate<String, Recipe> producer) {
        this.producer = producer;
    }

    public void send(Recipe recipe) {
        producer.send(topicName, String.valueOf(recipe.getId()), recipe)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        log.error("Failed to produce to kafka", exception);
                    } else {
                        log.info("Produced record to topic {} partition {} @ offset {}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
        producer.flush();
    }

}

