package com.ufr.tlib.repository;

import com.ufr.tlib.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String name);
}
