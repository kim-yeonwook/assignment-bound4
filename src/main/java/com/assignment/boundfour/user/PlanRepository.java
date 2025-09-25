package com.assignment.boundfour.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p JOIN p.userList u WHERE u.username = :username")
    Optional<Plan> findByUserListUserName(@Param("username") String username);

}
