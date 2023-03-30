package com.sfr.sfr.kafka.controller;

import com.sfr.sfr.kafka.producer.KafkaProducer;
import com.sfr.sfr.recipe.model.RecipeModel;
import com.sfr.sfr.kafka.schema.Recipe;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/recipe/{recipe}")
    public ResponseEntity getAccountBalance(@PathVariable String recipe) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        ReadOnlyKeyValueStore<String, Long> balances = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("balance", QueryableStoreTypes.keyValueStore())
        );
        ResponseEntity response;
        if(balances.get(recipe)==null) {
            response = ResponseEntity.notFound().build();
        } else {
            response = ResponseEntity.ok(balances.get(recipe));
        }
        return response;
    }

    @PostMapping("/addMessage")
    public void send(@RequestBody RecipeModel model) {
        Recipe recipe = Recipe.newBuilder()
                .setId(model.getId())
                .setName(model.getName())
                .setDescription(model.getDescription())
                .setIngredients(model.getIngredients())
                .setInstructions(model.getInstructions())
                .setEvaluation(model.getEvaluation())
                .setPrepationTime(model.getPreparationTime())
                .setCategory(model.getCategory())
                .build();

        kafkaProducer.send(recipe);
    }

    @GetMapping("/topology")
    public ResponseEntity<String> getTopology() {
        return ResponseEntity.ok(factoryBean.getTopology().describe().toString());
    }

}
