package com.sfr.sfr.stream;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Processor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    private StreamsBuilder streamsBuilder;

    @PostConstruct
    void buildPipeline() {

        KStream<String, String> messageStream = streamsBuilder.stream("recipe", Consumed.with(STRING_SERDE, STRING_SERDE));

        KTable<String, String> messageTable = messageStream.groupByKey(Grouped.with(STRING_SERDE, STRING_SERDE))
                .reduce((value1, value2) -> value2.toLowerCase(), Materialized.with(STRING_SERDE, STRING_SERDE));

        messageTable.toStream().to("recipe-table", Produced.with(STRING_SERDE, STRING_SERDE));
    }

}
