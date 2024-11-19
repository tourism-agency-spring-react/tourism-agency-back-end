package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.enums.RoleEnum;
import com.tourismagency.tourism_agency.persistense.model.RoleEntity;

public interface IRoleService {

    RoleEntity createRole(RoleEnum roleEnum);
    RoleEntity getRole(RoleEnum roleEnum);
}
