package com.globalsavings.calculator.domain.region;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The model of the application region.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegionModel {

    /**
     * The country ISO.
     */
    private String countryIso;

    /**
     * The country VAT (value added tax) value.
     */
    private double VAT;

    /**
     * The country name in full.
     */
    private String countryName;
}
