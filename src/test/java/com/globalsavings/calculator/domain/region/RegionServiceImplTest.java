package com.globalsavings.calculator.domain.region;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.globalsavings.calculator.domain.exception.ItemNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class RegionServiceImplTest {
    
    private RegionService service;

    @BeforeEach
    public void setUp() {
        this.service = new RegionServiceImpl();
        this.service.clearRegions();
    }

    @Test
    public void addRegion_addRegionModel_returnsRegionModel() {

        //Assign
        RegionModel created = this.createRegion("DE", "Germany", 0.10);

        //Act
        this.service.addRegion(created);

        //Assert
        assertThat(created).isNotNull();
        assertThat(created.getCountryIso()).isEqualTo("DE");
        assertThat(created.getCountryName()).isEqualTo("Germany");
        assertThat(created.getVAT()).isEqualTo(0.10);
    }

    @Test
    public void updateRegion_updatesRegionModel_returnsRegionModel() {

        //Assign
        RegionModel created = this.createRegion("DE", "Germany", 0.10);
        RegionModel updating = this.createRegion("DE", "Germany", 0.50);

        this.service.addRegion(created);

        //Act
        service.updateRegion(updating);
        RegionModel updated = this.service.getRegion("DE");

        //Assert
        assertThat(updated).isNotNull();
        assertThat(updated.getCountryIso()).isEqualTo("DE");
        assertThat(updated.getCountryName()).isEqualTo("Germany");
        assertThat(updated.getVAT()).isEqualTo(0.50);
    }

    @Test
    public void deleteRegion_removesRegionInMap_throwsItemNotFoundException() {
        
        //Assign
        RegionModel created = this.createRegion("DE", "Germany", 0.10);

        this.service.addRegion(created);

        //Act
        service.deleteRegion("DE");

        Assertions.assertThrowsExactly(ItemNotFoundException.class, () -> {
            this.service.getRegion("DE");
        });
    }

    @Test
    public void getRegion_retrievesRegionModel_returnsRegionModel() {
        
        //Assign
        RegionModel created = this.createRegion("DE", "Germany", 0.10);
        this.service.addRegion(created);

        //Act
        RegionModel retrieved = service.getRegion("DE");

        //Assert
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getCountryIso()).isEqualTo("DE");
        assertThat(retrieved.getCountryName()).isEqualTo("Germany");
        assertThat(retrieved.getVAT()).isEqualTo(0.10);
    }

    @Test
    public void getAllRegions_retrievesListOfRegionModel_returnsListRegionModel() {
        
        //Assign
        RegionModel first = this.createRegion("DE", "Germany", 0.10);
        RegionModel second = this.createRegion("FR", "France", 0.20);
        this.service.addRegion(first);
        this.service.addRegion(second);

        //Act
        List<RegionModel> regions = service.getAllRegions();

        //Assert
        assertThat(regions.size()).isEqualTo(2);
        assertThat(regions.get(0).getCountryIso()).isEqualTo(first.getCountryIso());
        assertThat(regions.get(0).getCountryName()).isEqualTo(first.getCountryName());
        assertThat(regions.get(0).getVAT()).isEqualTo(first.getVAT());
        assertThat(regions.get(1).getCountryIso()).isEqualTo(second.getCountryIso());
        assertThat(regions.get(1).getCountryName()).isEqualTo(second.getCountryName());
        assertThat(regions.get(1).getVAT()).isEqualTo(second.getVAT());
    }

    private RegionModel createRegion(String iso, String name, double vat) {
        RegionModel regionModel = new RegionModel();
        regionModel.setCountryIso(iso);
        regionModel.setCountryName(name);
        regionModel.setVAT(vat);

        return regionModel;
    }

}
