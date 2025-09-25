package com.assignment.boundfour.project;

import com.assignment.boundfour.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Project {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "manager_id")
    private Long managerId;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectTask> taskList;

    public void setOwner(User user) {
        this.user = user;
    }

    public void modify(Long managerId, ProjectStatus status, LocalDate deadline) {
        this.managerId = managerId;
        this.status = status;
        this.deadline = deadline;
    }
}
