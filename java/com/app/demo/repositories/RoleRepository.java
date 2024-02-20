package com.app.demo.repositories;

import com.app.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByAuthority(String authority);
    Optional<Role> findByAuthority(String authority);
}
