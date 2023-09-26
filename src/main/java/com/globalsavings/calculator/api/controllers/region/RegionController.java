package com.globalsavings.calculator.api.controllers.region;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globalsavings.calculator.api.CalculatorApiLocations;
import com.globalsavings.calculator.api.controllers.region.dto.RegionDto;
import com.globalsavings.calculator.api.controllers.region.dto.RegionDtoMapper;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Region REST API controller with representing methods.
 */
@Api(tags = {"Region"})
@RestController
@RequestMapping(
    path = "${calculator.api.prefix:}" + CalculatorApiLocations.REGION,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class RegionController {
    
    private final RegionService regionService;
    private final RegionDtoMapper regionDtoMapper;

    /**
     * Initializes a new {@link RegionController} instance.
     * 
     * @param regionService - {@link RegionService} instance.
     * @param regionDtoMapper - {@link RegionDtoMapper} instance.
     */
    public RegionController(
        RegionService regionService,
        RegionDtoMapper regionDtoMapper
    ) {
        this.regionService = regionService;
        this.regionDtoMapper = regionDtoMapper;
    }

    /**
     * REST API method to add a new region.
     *
     * @param region - The region to create.
     * @return The added region instance.
     */
    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegionDto> addRegion(
            @ApiParam(value = "Country information in JSON", required = true) @RequestBody RegionDto region) {

        final RegionModel regionModel = this.regionService.addRegion(this.regionDtoMapper.dtoToModel(region));
        final RegionDto addedRegion = this.regionDtoMapper.modelToDto(regionModel);

        return new ResponseEntity<>(addedRegion, HttpStatus.CREATED);
    }

    /**
     * REST API method to modify a region.
     *
     * @param region - The region to be modified.
     * @return The modified region instance.
     */
    @ApiOperation(value = "Modify a country information")
    @RequestMapping(
            path = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegionDto> updateRegion(
            @ApiParam(value = "The region JSON", required = true) @RequestBody RegionDto region) {

        final RegionModel regionModel = this.regionService.updateRegion(this.regionDtoMapper.dtoToModel(region));
        final RegionDto updatedUser = this.regionDtoMapper.modelToDto(regionModel);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * REST API method to delete the specified country information.
     *
     * @param countryIso The country ISO.
     */
    @ApiOperation(value = "Delete specific country information by country ISO")
    @RequestMapping(
            path = "/{countryIso}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRegion(
            @ApiParam(name = "countryIso", value = "The country ISO", required = true) @PathVariable String countryIso) {
        
        this.regionService.deleteRegion(countryIso);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * REST API method to retrieve region by it's ID.
     *
     * @param countryIso - The country ISO.
     * @return the region instance.
     */
    @ApiOperation(value = "Get specific country information by country ISO")
    @GetMapping("/{countryIso}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegionDto> getRegion(
            @ApiParam(name = "countryIso", value = "The region country ISO", required = true) @PathVariable String countryIso) {

        RegionModel regionModel = this.regionService.getRegion(countryIso);

        final RegionDto regionDto = this.regionDtoMapper.modelToDto(regionModel);
        return new ResponseEntity<>(regionDto, HttpStatus.OK);
    }

    /**
     * REST API method to retrieve list of regions(country ISO, VAT, country name).
     *
     * @return The list of region instances.
     */
    @ApiOperation(value = "Get list of information for all countries")
    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RegionDto>> getAllRegions() {
        List<RegionDto> regions = this.regionDtoMapper.modelsToDtos(this.regionService.getAllRegions());

        return new ResponseEntity<>(regions, HttpStatus.OK);
    }
    
}
