package com.globalsavings.calculator.api.controllers.region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalsavings.calculator.api.advices.ApiErrorResponse;
import com.globalsavings.calculator.api.controllers.region.dto.RegionDto;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.utils.RegionUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetRegionApiTest {

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
    public void getRegion_getByCountyIso_returnExistingRegion() throws Exception {

        //Assign
        RegionModel model = this.regionUtils.createDefaultRegionModel();
        this.regionService.addRegion(model);

        //Act
        String response = mockMvc.perform(get(REGON_API_URL+"/" + model.getCountryIso()))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        RegionDto found = objectMapper.readValue(response, RegionDto.class);

        //Assert
        assertThat(found.getCountryIso()).isEqualTo(model.getCountryIso());
        assertThat(found.getCountryName()).isEqualTo(model.getCountryName());
        assertThat(found.getVAT()).isEqualTo(model.getVAT());
    }

    @Test
    public void getRegion_deleteNotExistingItem_throwsItemNotFoundException() throws Exception {

        //Assign
        String searchingCountryIso = "AAA";
        String msg = String.format("Region with country ISO=%s does not exist!", searchingCountryIso);
        RegionModel model = this.regionUtils.createDefaultRegionModel();
        this.regionService.addRegion(model);

        //Act
        String response = mockMvc.perform(get(REGON_API_URL+"/" + searchingCountryIso))
            .andExpect(status().isNotFound())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getMessage()).isEqualTo(msg);

    }

    @Test
    public void getAllRegions_retrieveRegions_returnExistingRegions() throws Exception {

        //Assign
        RegionModel model1 = this.regionUtils.createRegionModel("DE", "Germany", 0.15);
        RegionModel model2 = this.regionUtils.createRegionModel("FR", "France", 0.20);
        this.regionService.addRegion(model1);
        this.regionService.addRegion(model2);

        //Act
        String response = mockMvc.perform(get(REGON_API_URL+"/all"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        List<RegionDto> list = objectMapper.readValue(response, new TypeReference<List<RegionDto>>(){});

        //Assert
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0).getCountryIso()).isEqualTo(model1.getCountryIso());
        assertThat(list.get(0).getCountryName()).isEqualTo(model1.getCountryName());
        assertThat(list.get(0).getVAT()).isEqualTo(model1.getVAT());
        assertThat(list.get(1).getCountryIso()).isEqualTo(model2.getCountryIso());
        assertThat(list.get(1).getCountryName()).isEqualTo(model2.getCountryName());
        assertThat(list.get(1).getVAT()).isEqualTo(model2.getVAT());
    }
}
