package com.dzieminska.document.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.dzieminska.document.generator.dto.DiplomaGeneratorFormDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PythonScriptRunnerService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public File run(String scriptPath, DiplomaGeneratorFormDTO formDTO) throws Exception {
		String json = objectMapper.writeValueAsString(formDTO);
		json = JSONObject.escape(json);
		String outputFileName = createOutputFileName();
		ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath(scriptPath), json, outputFileName);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		int returnCode = process.waitFor();

		if (returnCode != 0) {
			String processOutput = readProcessOutput(process.getInputStream());
			throw new IllegalStateException("Could not generate document. Error in python script:" + processOutput);
		}
		return new File(Paths.get("").toAbsolutePath() + "/" + outputFileName);
	}

	private String readProcessOutput(InputStream inputStream) throws IOException {
		try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
			return output.lines().collect(Collectors.joining("\n"));
		}
	}

	private String resolvePythonScriptPath(String filePath) throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:" + filePath).getAbsolutePath();
	}

	private String createOutputFileName() {
		String id = UUID.randomUUID().toString();
		return "diploma-" + id + ".docx";
	}
}
