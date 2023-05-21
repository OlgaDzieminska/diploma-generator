package com.dzieminska.document.generator;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;

import com.dzieminska.document.generator.dto.PupilDTO;

public class GeneratorAvailableOptionsService {

	public List<PupilDTO> providePupilsOptions() throws IOException {
		List<PupilDTO> pupilDTOList = new ArrayList<>();
		Reader reader = Files.newBufferedReader(ResourceUtils.getFile("classpath:AvailableOptions/pupil.csv").toPath(), StandardCharsets.UTF_8);
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build());

		for (CSVRecord csvRecord : csvParser) {
			String name = csvRecord.get(0);
			String classNumber = csvRecord.get(1);
			pupilDTOList.add(new PupilDTO(name, classNumber));
		}
		return pupilDTOList;
	}

	public List<String> provideReccomendationOptions() throws IOException {
		return Files.readAllLines(ResourceUtils.getFile("classpath:AvailableOptions/recommendation.txt").toPath(), StandardCharsets.UTF_8);
	}

	public List<String> provideConclusionOptions() throws IOException {
		return Files.readAllLines(ResourceUtils.getFile("classpath:AvailableOptions/conclusion.txt").toPath(), StandardCharsets.UTF_8);
	}
}
