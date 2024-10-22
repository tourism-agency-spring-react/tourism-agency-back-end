package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.enums.RoleEnum;
import com.tourismagency.tourism_agency.persistense.model.RoleEntity;
import com.tourismagency.tourism_agency.persistense.repository.IRoleRepository;
import com.tourismagency.tourism_agency.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity createRole(RoleEnum roleEnum) {
        return roleRepository.save(RoleEntity.builder().roleName(roleEnum).build());
    }

    @Override
    public RoleEntity getRole(RoleEnum roleEnum) {
        return roleRepository.findByRoleName(roleEnum).orElseGet(() -> createRole(roleEnum));
    }
}
