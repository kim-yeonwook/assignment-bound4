package com.assignment.boundfour.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT count(p.id) from Project p inner join p.user u where u.username = :username")
    long countByUserUsername(String username);
}
