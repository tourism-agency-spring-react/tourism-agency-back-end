package com.tourismagency.tourism_agency.persistense.repository;

import com.tourismagency.tourism_agency.enums.RoleEnum;
import com.tourismagency.tourism_agency.persistense.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(RoleEnum roleName);
}
