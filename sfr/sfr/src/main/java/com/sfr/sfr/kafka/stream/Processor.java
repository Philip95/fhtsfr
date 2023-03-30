package com.sfr.sfr.kafka.stream;

import com.sfr.sfr.kafka.RecipeSerdes;
import com.sfr.sfr.kafka.schema.Recipe;
import com.sfr.sfr.recipe.model.AverageRating;
import jakarta.annotation.PostConstruct;
//import org.springframework.cloud.stream.messaging.Processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class Processor {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Integer> INTEGER_SERDE = Serdes.Integer();

    @Autowired
    private StreamsBuilder streamsBuilder;

    /*
    @PostConstruct
    void buildPipeline() {

        KStream<String, String> messageStream = streamsBuilder.stream("recipe", Consumed.with(STRING_SERDE, STRING_SERDE));

        KTable<String, String> messageTable = messageStream.groupByKey(Grouped.with(STRING_SERDE, STRING_SERDE))
                .reduce((value1, value2) -> value2.toLowerCase(), Materialized.with(STRING_SERDE, STRING_SERDE));

        messageTable.toStream().to("recipe-table", Produced.with(STRING_SERDE, STRING_SERDE));
    }
    */

    @Autowired
    void buildRightPipeline() {

        KStream<String, Recipe> messageStream = streamsBuilder
                .stream("recipe", Consumed.with(STRING_SERDE, RecipeSerdes.serdes()))
                .peek((key, value) -> log.info("key: " + key + " value: " + value));

        KTable<String, AverageRating> messageTable = messageStream
                .groupByKey(Grouped.with(STRING_SERDE, RecipeSerdes.serdes()))
                .aggregate(Processor::getAggregator, (key, value, aggregate) -> {
                    addRating(aggregate, value);
                    return aggregate;
                }, Materialized.as("recipe-table"));


        //messageTable.toStream().to("recipe-table", Produced.with(STRING_SERDE, STRING_SERDE));

    }

    private static void setNewAverageRating(AverageRating aggregate) {
        double sum = aggregate.getAverageRatingList().stream().mapToDouble(Double::doubleValue).sum();
        double averageRating = sum / aggregate.getAverageRatingList().size();
        aggregate.setAverageRating(averageRating);
    }

    private static void addRating(AverageRating aggregate, Recipe value) {
        List<Double> averagePrice = aggregate.getAverageRatingList();
        averagePrice.add(value.getEvaluation().doubleValue());
        aggregate.setAverageRatingList(averagePrice);
    }

    private static AverageRating getAggregator() {
        AverageRating averageRating = new AverageRating();
        averageRating.setAverageRatingList(new ArrayList<>());
        return averageRating;
    }


}
