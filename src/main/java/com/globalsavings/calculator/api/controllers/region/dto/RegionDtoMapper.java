package com.globalsavings.calculator.api.controllers.region.dto;

import org.mapstruct.Mapper;

import com.globalsavings.calculator.api.controllers.dto.DtoMapper;
import com.globalsavings.calculator.domain.region.RegionModel;

/**
 * Mapper for converting RegionDto to RegionModel and vice versa.
 */
@Mapper(componentModel = "spring")
public interface RegionDtoMapper extends DtoMapper<RegionDto, RegionModel> {

    /**
     * Converts DTO to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    RegionModel dtoToModel(RegionDto dto);

    /**
     * Converts model to DTO.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    RegionDto modelToDto(RegionModel model);    
}
