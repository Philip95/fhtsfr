package com.sfr.sfr.kafka.controller;

import com.sfr.sfr.kafka.producer.KafkaProducer;
import com.sfr.sfr.recipe.model.RecipeModel;
import com.sfr.sfr.kafka.schema.Recipe;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
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

}
