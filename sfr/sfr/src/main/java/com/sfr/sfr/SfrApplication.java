package com.sfr.sfr;

import com.sfr.sfr.kafka.stream.RecipeTopology;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
public class SfrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfrApplication.class, args);
	}

	/**
	 *
	 * Create new Topic
	 *
	 */
	@Value("${topic.name}")
	private String topicName;

	@Value("${topic.partitions-num}")
	private Integer partitions;

	@Value("${topic.replication-factor}")
	private short replicationFactor;

	@Bean
	public NewTopic recipeTopic() {
		return new NewTopic(topicName, partitions, replicationFactor);
	}


	@Bean
	KStream<String, Long> countAgg(final StreamsBuilder builder) {

		KStream<String, Long>countAgg = RecipeTopology.getRecipekStream(builder);

		countAgg.print(Printed.<String, Long>toSysOut().withLabel("Running count"));
		return countAgg;
	}

}
