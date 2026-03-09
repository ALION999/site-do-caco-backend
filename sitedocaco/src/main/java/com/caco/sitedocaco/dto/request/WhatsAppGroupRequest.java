package com.caco.sitedocaco.dto.request;

import com.caco.sitedocaco.entity.enums.CourseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record WhatsAppGroupRequest(
        @NotNull CourseType course,
        @Positive int entryYear,
        @NotBlank String whatsappLink
) {}

