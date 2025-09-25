package com.assignment.boundfour.project.dto;

import com.assignment.boundfour.project.Project;
import com.assignment.boundfour.project.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectSaveRequest(

        @NotEmpty(message = "필수 값.")
        String name,

        @NotNull(message = "필수 값.")
        Long managerId,

        @NotNull(message = "필수 값.")
        ProjectStatus status,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "필수 값.")
        LocalDate deadline
) {
    public Project toEntity() {
        return Project.builder()
                .name(name)
                .managerId(managerId)
                .status(status)
                .deadline(deadline)
                .build();
    }
}
