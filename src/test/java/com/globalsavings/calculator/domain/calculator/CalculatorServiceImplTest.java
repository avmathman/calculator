package com.globalsavings.calculator.domain.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.globalsavings.calculator.domain.exception.ItemNotFoundException;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.domain.region.RegionServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorServiceImplTest {

    RegionService regionService;
    CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        this.regionService = new RegionServiceImpl();
        this.calculatorService = new CalculatorServiceImpl();
    }

    
    @Test
    public void getNetPrice_validCountryIso_calculatesNetPrice() {
        this.regionService.addRegion(this.createRegion("FR", "France", 0.1));
        CalculatorModel result = calculatorService.getNetPrice(100, "FR");

        assertThat(result).isNotNull();
        assertThat(result.getCountryIso()).isEqualTo("FR");
        assertThat(result.getNetPrice()).isEqualTo(90);

    }

    @Test
    public void getNetPrice_invalidCountryIso_throwsItemNotFoundException() {
        this.regionService.addRegion(this.createRegion("FR", "France", 0.1));

        Assertions.assertThrowsExactly(ItemNotFoundException.class, () -> {
            calculatorService.getNetPrice(100, "Unknown");
        });
    }

    private RegionModel createRegion(String iso, String name, double vat) {
        RegionModel regionModel = new RegionModel();
        regionModel.setCountryIso(iso);
        regionModel.setCountryName(name);
        regionModel.setVAT(vat);

        return regionModel;
    }
}
