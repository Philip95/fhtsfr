package com.sfr.sfr.kafka.stream;

import com.sfr.sfr.kafka.RecipeSerdes;

import com.sfr.sfr.kafka.schema.Recipe;
import com.sfr.sfr.recipe.model.AverageRating;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class RecipeTopology {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Integer> INTEGER_SERDE = Serdes.Integer();
    private static final Serde<Recipe> RECIPE_SERDE = RecipeSerdes.serdes();

    public static KStream<String, Long> getRecipekStream(StreamsBuilder streamsBuilder) {
        return streamsBuilder.stream("recipe", Consumed.with(STRING_SERDE, RECIPE_SERDE))
                .groupBy((key, value) -> key)
                .count()
                .toStream();
    }

    private static AverageRating getAggregator() {
        AverageRating averageRating = new AverageRating();
        averageRating.setRatings(new ArrayList<>());
        return averageRating;
    }
}
