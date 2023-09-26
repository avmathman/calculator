package com.globalsavings.calculator.api.controllers.calculator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for calculated result.
 */
@Getter
@Setter
@NoArgsConstructor
public class CalculatorDto {

    /**
     * The gross price.
     */
    private double grossPrice;

    /**
     * The net price.
     */
    private double netPrice;

    /**
     * The country ISO.
     */
    private String countryIso;
}
