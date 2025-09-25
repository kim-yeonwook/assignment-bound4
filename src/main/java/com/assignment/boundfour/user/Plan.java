package com.assignment.boundfour.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Plan {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer limit = 1;

    @OneToMany(mappedBy = "plan")
    private List<User> userList;
}
