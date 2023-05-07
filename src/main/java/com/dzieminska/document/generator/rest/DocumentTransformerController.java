package com.dzieminska.document.generator.rest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diploma")
public class DocumentTransformerController {

	@GetMapping(value = "/generate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] generateFile() throws IOException {
		InputStream in = getClass().getResourceAsStream("/DiplomaGenerator.py");
		return IOUtils.toByteArray(in);
	}
}
