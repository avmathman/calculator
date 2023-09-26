package com.globalsavings.calculator.domain.calculator;

/**
 * Provides methods for working with calculations.
 */
public interface CalculatorService {

    /**
     * Generates net price.
     *
     * @param grossPrice - The gross price.
     * @param countryIso - The country ISO.
     * @return The {@link CalculatorModel}.
     */
    CalculatorModel getNetPrice(double grossPrice, String countryIso);
}
