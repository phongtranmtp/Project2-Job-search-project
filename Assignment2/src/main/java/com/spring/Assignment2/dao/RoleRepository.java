package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}