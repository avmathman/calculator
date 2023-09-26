package com.globalsavings.calculator.api.controllers.region;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalsavings.calculator.api.advices.ApiErrorResponse;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionService;
import com.globalsavings.calculator.utils.RegionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeleteRegionApiTest {

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
    public void deleteRegion_removeExistingItem_returnAddedRegion() throws Exception {

        //Assign
        RegionModel model = this.regionUtils.createDefaultRegionModel();
        this.regionService.addRegion(model);

        //Act
        mockMvc.perform(delete(REGON_API_URL+"/" + model.getCountryIso()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void deleteRegion_deleteNotExistingItem_throwsItemNotFoundException() throws Exception {

        //Assign
        String countryIso = "AA";
        String msg = String.format("Region with country ISO=%s does not exist to delete", countryIso);

        //Act
        String response = mockMvc.perform(delete(REGON_API_URL + "/" + countryIso))
            .andExpect(status().isNotFound())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getMessage()).isEqualTo(msg);

    }
}
