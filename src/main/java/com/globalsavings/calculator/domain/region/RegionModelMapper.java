package com.globalsavings.calculator.domain.region;

import org.mapstruct.Mapper;

import com.globalsavings.calculator.api.controllers.region.dto.RegionDto;
import com.globalsavings.calculator.domain.ModelMapper;

/**
 * Mapper for converting RegionModel to RegionDto and vice versa.
 */
@Mapper(componentModel = "spring")
public interface RegionModelMapper extends ModelMapper<RegionModel, RegionDto> {
    
}
