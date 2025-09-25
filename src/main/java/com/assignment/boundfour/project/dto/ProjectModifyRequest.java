package com.assignment.boundfour.project.dto;

import com.assignment.boundfour.project.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectModifyRequest(

        @NotNull(message = "필수 값.")
        ProjectStatus status,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "필수 값.")
        LocalDate deadline,

        @NotNull(message = "필수 값.")
        Long managerId
) {
}
