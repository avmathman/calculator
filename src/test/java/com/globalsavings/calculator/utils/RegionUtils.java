package com.globalsavings.calculator.utils;

import com.globalsavings.calculator.api.controllers.region.dto.RegionDto;
import com.globalsavings.calculator.domain.region.RegionModel;

public class RegionUtils {
    public RegionModel createDefaultRegionModel() {
        return this.createRegionModel("DE", "Germany", 0.15);
    }

    public RegionModel createRegionModel(String countryIso, String countryName, double vat) {
        RegionModel current = new RegionModel();

        current.setCountryIso(countryIso);
        current.setCountryName(countryName);
        current.setVAT(vat);

        return current;
    }

    public RegionDto createRegionDefaultDto() {
        return this.createRegionDto("DE", "Germany", 0.15);
    }

    public RegionDto createRegionDto(String countryIso, String countryName, double vat) {
        RegionDto current = new RegionDto();

        current.setCountryIso(countryIso);
        current.setCountryName(countryName);
        current.setVAT(vat);

        return current;
    }
}
