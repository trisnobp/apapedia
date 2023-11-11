package com.apapedia.user.repository;

import com.apapedia.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface UserDb extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String name); // Derived Query
    Optional<User> findByEmail(String email); // Derived Query
    Optional<User> findByPassword(String password); // Derived Query
}
