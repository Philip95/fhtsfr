package com.sfr.sfr.kafka;

import com.sfr.sfr.kafka.schema.Recipe;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class RecipeSerdes extends Serdes.WrapperSerde<Recipe> {

    public RecipeSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Recipe.class));
    }

    public static Serde<Recipe> serdes() {
        JsonSerializer<Recipe> serializer = new JsonSerializer<>();
        JsonDeserializer<Recipe> deserializer = new JsonDeserializer<>(Recipe.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
