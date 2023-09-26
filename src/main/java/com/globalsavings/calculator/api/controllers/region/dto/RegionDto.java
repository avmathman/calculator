package com.globalsavings.calculator.api.controllers.region.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for region.
 */
@Getter
@Setter
@NoArgsConstructor
public class RegionDto {

    /**
     * The country ISO.
     */
    @ApiModelProperty(value = "The country ISO", example = "DE")
    private String countryIso;

    /**
     * The country VAT (value added tax) value.
     */
    @ApiModelProperty(value = "The country VAT", example = "0.19")
    private Double VAT;

    /**
     * The country name in full.
     */
    @ApiModelProperty(value = "The country name", example = "Germany")
    private String countryName;
}
