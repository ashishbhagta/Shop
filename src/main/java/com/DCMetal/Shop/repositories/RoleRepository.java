package com.DCMetal.Shop.repositories;

import com.DCMetal.Shop.model.AppRole;
import com.DCMetal.Shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByRoleName(AppRole appRole);
}