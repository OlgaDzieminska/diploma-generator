package com.dzieminska.document.generator;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dzieminska.document.generator.dto.PupilDTO;

@SpringBootApplication
public class DocumentGeneratorApplication {

	private final GeneratorAvailableOptionsService generatorAvailableOptionsService = new GeneratorAvailableOptionsService();

	public static void main(String[] args) {
		SpringApplication.run(DocumentGeneratorApplication.class, args);
	}

	@Bean
	public List<String> recommendationOptions() throws IOException {
		return generatorAvailableOptionsService.provideReccomendationOptions();
	}

	@Bean
	public List<String> conclusionOptions() throws IOException {
		return generatorAvailableOptionsService.provideConclusionOptions();
	}

	@Bean
	public List<PupilDTO> pupilOptions() throws IOException {
		return generatorAvailableOptionsService.providePupilsOptions();
	}
}
