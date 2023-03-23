package com.sfr.sfr.kafka.stream;

import com.sfr.sfr.model.AverageRating;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.apache.kafka.streams.kstream.Consumed.as;


@Component
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

    @PostConstruct
    void buildRightPipeline() {

        KStream<String, Integer> messageStream = streamsBuilder
                .stream("recipe", Consumed.with(STRING_SERDE, INTEGER_SERDE));
        /*
        KTable<String, String> messageTable = messageStream
                .groupByKey(Grouped.with(STRING_SERDE, INTEGER_SERDE))
                .aggregate(Processor::getAggregator, (key, value, aggregate) -> {
                    //addRating(aggregate, value);
                    //aggregate += value;
                    //return aggregate;
                    return aggregate;
                }, Materialized.as("recipe-table")
                        .withValueSerde(Serdes.Integer()));


        messageTable.toStream().to("recipe-table", Produced.with(STRING_SERDE, STRING_SERDE));
        */
    }

    private static void setNewAverageRating(AverageRating aggregate) {
        double sum = aggregate.getAverageRatingList().stream().mapToDouble(Double::doubleValue).sum();
        double averageRating = sum / aggregate.getAverageRatingList().size();
        aggregate.setAverageRating(averageRating);
    }

    private static void addRating(AverageRating aggregate, Integer value) {
        List<Double> averagePrice = aggregate.getAverageRatingList();
        averagePrice.add(value.doubleValue());
        aggregate.setAverageRatingList(averagePrice);
    }

    private static AverageRating getAggregator() {
        AverageRating averageRating = new AverageRating();
        averageRating.setAverageRatingList(new ArrayList<>());
        return averageRating;
    }


}
