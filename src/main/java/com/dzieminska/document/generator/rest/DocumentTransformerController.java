package com.dzieminska.document.generator.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzieminska.document.generator.PythonScriptRunnerService;
import com.dzieminska.document.generator.dto.DiplomaGeneratorFormDTO;
import com.dzieminska.document.generator.dto.PupilDTO;

@RestController
@RequestMapping("/diplomaGenerator")
public class DocumentTransformerController {
	private final PythonScriptRunnerService pythonScriptRunnerService;

	private final List<String> recommendationOptions;
	private final List<String> conclusionOptions;
	private final List<PupilDTO> pupilOptions;

	public DocumentTransformerController(PythonScriptRunnerService pythonScriptRunnerService,
			@Qualifier("recommendationOptions") List<String> recommendationOptions, @Qualifier("conclusionOptions") List<String> conclusionOptions,
			List<PupilDTO> pupilOptions) {
		this.pythonScriptRunnerService = pythonScriptRunnerService;
		this.recommendationOptions = recommendationOptions;
		this.conclusionOptions = conclusionOptions;
		this.pupilOptions = pupilOptions;
	}

	@PostMapping(value = "/generate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] generateFile(@RequestBody DiplomaGeneratorFormDTO diplomaGeneratorFormDTO) throws Exception {
		File outputFile = pythonScriptRunnerService.run("DiplomaGenerator.py", diplomaGeneratorFormDTO);
		return FileUtils.readFileToByteArray(outputFile);
	}

	@GetMapping(value = "/recommendationOptions")
	public List<String> provideRecommendationOptions() {
		return recommendationOptions;
	}

	@GetMapping(value = "/conclusionOptions")
	public List<String> provideConclusionOptions() throws IOException {
		return conclusionOptions;
	}

	@GetMapping(value = "/pupilsOptions")
	public List<PupilDTO> providePupilOptions() throws IOException {
		return pupilOptions;
	}
}
