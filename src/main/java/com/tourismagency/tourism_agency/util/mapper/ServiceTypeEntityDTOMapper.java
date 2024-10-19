package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.persistense.model.ServiceTypeEntity;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.presentation.dto.ServiceTypeEntityDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ServiceTypeEntityDTOMapper extends Converter<ServiceTypeEntityDTO, ServiceTypeEntity>  {

    @Override
    ServiceTypeEntity convert(ServiceTypeEntityDTO source);

}
