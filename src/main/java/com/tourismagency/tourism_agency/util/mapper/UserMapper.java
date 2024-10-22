package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;

import java.util.List;

public class UserMapper {

    public static UserDTO entityToDto(UserEntity userEntity) {
        return UserDTO
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .build();
    }

    public static List<UserDTO> entityToDto(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(UserMapper::entityToDto)
                .toList();
    }

    public static UserEntity dtoToEntity(LoginRequestDTO loginRequestDTO) {
        return UserEntity
                .builder()
                .email(loginRequestDTO.email())
                .password(loginRequestDTO.password())
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }
}
