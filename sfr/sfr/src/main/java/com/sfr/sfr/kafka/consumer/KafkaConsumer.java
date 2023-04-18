package com.sfr.sfr.kafka.consumer;

import com.sfr.sfr.kafka.schema.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, Recipe> record) {
        log.info(String.format("Consumed message -> %s , %s", record.key(), record.value()));
    }

}
