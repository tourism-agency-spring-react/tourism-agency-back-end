package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.ServiceTypeEntity;
import com.tourismagency.tourism_agency.presentation.dto.ServiceTypeEntityDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "Spring")
public interface ServiceTypeEntityMapper extends Converter<ServiceTypeEntity, ServiceTypeEntityDTO> {

    @Override
    ServiceTypeEntityDTO convert(ServiceTypeEntity source);
}
