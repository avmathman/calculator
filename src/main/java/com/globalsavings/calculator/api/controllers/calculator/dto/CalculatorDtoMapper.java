package com.globalsavings.calculator.api.controllers.calculator.dto;

import org.mapstruct.Mapper;

import com.globalsavings.calculator.api.controllers.dto.DtoMapper;
import com.globalsavings.calculator.domain.calculator.CalculatorModel;

/**
 * Mapper for converting RegionDto to RegionModel and vice versa.
 */
@Mapper(componentModel = "spring")
public interface CalculatorDtoMapper extends DtoMapper<CalculatorDto, CalculatorModel> {
    
    /**
     * Converts DTO to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    CalculatorModel dtoToModel(CalculatorDto dto);

    /**
     * Converts model to DTO.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    CalculatorDto modelToDto(CalculatorModel model);
}
