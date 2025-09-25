package com.assignment.boundfour.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u, p FROM User u join fetch u.plan p WHERE u.username = :username")
    Optional<User> findByUsername(String username);
}
