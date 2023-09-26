package com.globalsavings.calculator.api.controllers.region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.utils.RegionUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddRegionApiTest {

    private static final String REGON_API_URL = "/api/region";

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
    public void addRegion_addNewRegion_returnAddedRegion() throws Exception {

        //Assign
        RegionDto dto = this.regionUtils.createRegionDefaultDto();

        //Act
        String response = mockMvc.perform(post(REGON_API_URL)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        RegionDto createdDto = objectMapper.readValue(response, RegionDto.class);

        //Assert
        assertThat(createdDto.getCountryIso()).isEqualTo(dto.getCountryIso());
        assertThat(createdDto.getCountryName()).isEqualTo(dto.getCountryName());
        assertThat(createdDto.getVAT()).isEqualTo(dto.getVAT());
    }

    @Test
    public void addRegion_duplicateCountryIso_throwsDuplicateItemException() throws Exception {

        //Assign
        RegionDto dto = this.regionUtils.createRegionDefaultDto();
        RegionModel model = this.regionUtils.createDefaultRegionModel();
        String msg = String.format("Region with country ISO=%s exists!", dto.getCountryIso());
        this.regionService.addRegion(model);
        
        //Act
        String response = mockMvc.perform(post(REGON_API_URL)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isConflict())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getStatus().value()).isEqualTo(409);
        assertThat(error.getMessage()).isEqualTo(msg);

    }
}
