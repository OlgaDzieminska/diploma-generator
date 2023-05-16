package com.dzieminska.document.generator;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.dzieminska.document.generator.dto.DiplomaGeneratorFormDTO;
import com.dzieminska.document.generator.dto.PupilDTO;

class PythonScriptRunnerTest {

	PythonScriptRunnerService service = new PythonScriptRunnerService();

	@Test
	void runPythonScript() throws Exception {
		// given
		DiplomaGeneratorFormDTO formDTO = new DiplomaGeneratorFormDTO(new PupilDTO("Michal", 3), List.of("c1", "c2"), List.of());

		// when
		service.run("src/test/resources/DiplomaGenerator.py", formDTO);

	}
}
