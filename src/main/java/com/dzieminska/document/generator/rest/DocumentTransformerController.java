package com.dzieminska.document.generator.rest;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;

@RestController
@RequestMapping("/diploma")
public class DocumentTransformerController {

	@GetMapping(value = "/get-file",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] getFile() throws IOException{
		InputStream in=getClass().getResourceAsStream("/DiplomaGenerator.py");
		return IOUtils.toByteArray(in);
	}
}
