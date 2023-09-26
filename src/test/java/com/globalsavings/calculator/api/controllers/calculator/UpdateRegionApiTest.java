package com.globalsavings.calculator.api.controllers.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalsavings.calculator.api.advices.ApiErrorResponse;
import com.globalsavings.calculator.api.controllers.region.dto.RegionDto;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionModelMapper;
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.utils.RegionUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UpdateRegionApiTest {

    private static final String REGON_API_URL = "/api/region";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionModelMapper modelMapper;

    private RegionUtils regionUtils;

    @BeforeEach
    public void setUp() {
        if(this.regionUtils == null) {
            this.regionUtils = new RegionUtils();
        }

        this.regionService.clearRegions();
    }

    @Test
    public void updateRegion_updateRegion_returnUpdatedRegion() throws Exception {

        //Assign
        RegionModel created = this.regionService.addRegion(this.regionUtils.createDefaultRegionModel());
        created.setVAT(0);
        RegionDto updating = modelMapper.modelToEntity(created);

        //Act
        String response = mockMvc.perform(put(REGON_API_URL)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(updating)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        RegionDto updated = objectMapper.readValue(response, RegionDto.class);

        //Assert
        assertThat(updated.getCountryIso()).isEqualTo(updating.getCountryIso());
        assertThat(updated.getCountryName()).isEqualTo(updating.getCountryName());
        assertThat(updated.getVAT()).isEqualTo(updating.getVAT());
    }

    @Test
    public void updateRegion_notExistingCountryIso_throwsItemNotFoundException() throws Exception {

        //Assign
        String countryIso = "ANY";
        String msg = String.format("Region with country ISO=%s does not exist!", countryIso);
        RegionModel created = this.regionService.addRegion(this.regionUtils.createDefaultRegionModel());
        created.setCountryIso(countryIso);;
        RegionDto updating = modelMapper.modelToEntity(created);
        
        //Act
        String response = mockMvc.perform(put(REGON_API_URL)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(updating)))
            .andExpect(status().isNotFound())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getMessage()).isEqualTo(msg);

    }
}
