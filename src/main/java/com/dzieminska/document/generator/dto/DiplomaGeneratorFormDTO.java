package com.dzieminska.document.generator.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class DiplomaGeneratorFormDTO {
	private final PupilDTO pupilDTO;
	private final List<String> conclusionParts;
	private final List<String> recommendationParts;
	private String lecture;
	private String teacher;
	private String date;
}
