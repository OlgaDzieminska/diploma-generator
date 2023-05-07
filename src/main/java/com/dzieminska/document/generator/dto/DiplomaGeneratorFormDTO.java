package com.dzieminska.document.generator.dto;

import java.util.List;

public record DiplomaGeneratorFormDTO(String pupilName, List<String> conclusionParts, List<String> recommendationParts) {
}
