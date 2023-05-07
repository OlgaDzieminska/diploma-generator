package com.dzieminska.document.generator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PythonScriptRunnerTest {

	PythonScriptRunner service = new PythonScriptRunner();

	@Test
	void runPythonScript() throws Exception {
		// given

		// when
		String result = service.run("src/test/resources/DiplomaGenerator.py");

		// then
		assertThat(result).isEqualTo("test.document.docx");
	}
}
