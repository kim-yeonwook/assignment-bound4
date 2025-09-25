package com.assignment.boundfour.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "\"user\"")
@Entity
public class User {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
