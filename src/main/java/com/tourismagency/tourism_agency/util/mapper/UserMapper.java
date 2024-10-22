package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<UserEntity, UserDTO> {

    List<UserDTO> convert(List<UserEntity> userEntities);

    UserDTO converter(UserEntity userEntity);

}
