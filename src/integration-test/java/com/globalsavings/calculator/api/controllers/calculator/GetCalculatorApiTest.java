package com.globalsavings.calculator.api.controllers.calculator;

import com.globalsavings.calculator.annotations.CalculatorIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalsavings.calculator.api.controllers.calculator.dto.CalculatorDto;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.utils.RegionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.RoundingMode;

@CalculatorIntegrationTest
public class GetCalculatorApiTest {

    private static final String CALCULATOR_API_URL = "/api/calculator";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegionService regionService;

    private RegionUtils regionUtils;

    @BeforeEach
    public void setUp() {
        if(this.regionUtils == null) {
            this.regionUtils = new RegionUtils();
        }

        this.regionService.clearRegions();
    }

    @Test
    public void getRegion_calculatesNetPrice_returnCalculatorDto() throws Exception {

        //Assign
        double grossPrice = 100;
        String countryIso = "DE";
        double vat = 0.15;

        BigDecimal result = BigDecimal
            .valueOf(1)
            .subtract(BigDecimal.valueOf(vat))
            .multiply(BigDecimal.valueOf(grossPrice))
            .setScale(2, RoundingMode.HALF_UP);
        
        double expected = result.doubleValue();

        RegionModel model = this.regionUtils.createRegionModel(countryIso, "Germany", vat);

        regionService.addRegion(model);

        //Act
        String response = mockMvc.perform(get(CALCULATOR_API_URL + "/" + grossPrice + "/" + countryIso))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        CalculatorDto calculatorDto = objectMapper.readValue(response, CalculatorDto.class);

        //Assert
        assertThat(calculatorDto.getNetPrice()).isEqualTo(expected);
        assertThat(calculatorDto.getCountryIso()).isEqualTo(countryIso);
        assertThat(calculatorDto.getGrossPrice()).isEqualTo(grossPrice);
    }
    
}
