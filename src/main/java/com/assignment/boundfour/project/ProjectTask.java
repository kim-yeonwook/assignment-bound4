package com.assignment.boundfour.project;

import jakarta.persistence.*;

@Table(name = "project_task")
@Entity
public class ProjectTask {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne()
    private Project project;
}
