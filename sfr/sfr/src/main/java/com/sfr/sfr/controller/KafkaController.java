package com.sfr.sfr.controller;

import com.sfr.sfr.model.RecipeModel;
import com.sfr.sfr.producer.KafkaProducer;
import com.sfr.sfr.schema.Recipe;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/")
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
