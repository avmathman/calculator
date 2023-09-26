package com.globalsavings.calculator.domain.calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Model with available fields for calculated result.
 */
@Getter
@Setter
@NoArgsConstructor
public class CalculatorModel {

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
