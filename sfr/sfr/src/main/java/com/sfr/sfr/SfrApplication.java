package com.sfr.sfr;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SfrApplication {
	public static void main(String[] args) {
		SpringApplication.run(SfrApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("recipe")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("recipes", "test");
			template.send("recipe", "Rezept 1");
			template.send("recipe", "Rezept 2");
			template.send("recipe", "Rezept 3");
			template.send("recipes", "Rezept 4");
			template.send("recipes", "Rezept 5");
		};
	}
}
