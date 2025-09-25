package com.assignment.boundfour.project.dto;

import com.assignment.boundfour.project.Project;
import com.assignment.boundfour.project.ProjectStatus;

import java.time.LocalDate;

public record ProjectInfoResponse(
        Long id,
        String name,
        Long managerId,
        ProjectStatus status,
        LocalDate deadline
) {

    public static ProjectInfoResponse fromEntity(Project project) {
        return new ProjectInfoResponse(
                project.getId(),
                project.getName(),
                project.getManagerId(),
                project.getStatus(),
                project.getDeadline()
        );
    }
}
