package com.dzieminska.document.generator.dto;

import java.util.List;

public record DiplomaGeneratorFormDTO(PupilDTO pupilDTO, List<String> conclusionParts, List<String> recommendationParts) {
}
