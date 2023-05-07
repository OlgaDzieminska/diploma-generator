package com.dzieminska.document.generator;

import java.lang.ProcessBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PythonScriptRunner {

	public String run(String scriptPath) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath(scriptPath));
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		String documentFileName = readProcessOutput(process.getInputStream());
		process.waitFor();
		return documentFileName;
	}

	private String readProcessOutput(InputStream inputStream) throws IOException {
		try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
			return output.readLine();
		}
	}

	private String resolvePythonScriptPath(String filePath) {
		File file = new File(filePath);
		return file.getAbsolutePath();
	}
}
