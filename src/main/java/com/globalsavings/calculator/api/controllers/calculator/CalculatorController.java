package com.globalsavings.calculator.api.controllers.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globalsavings.calculator.api.CalculatorApiLocations;
import com.globalsavings.calculator.api.controllers.calculator.dto.CalculatorDto;
import com.globalsavings.calculator.api.controllers.calculator.dto.CalculatorDtoMapper;
import com.globalsavings.calculator.domain.calculator.CalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Calculator REST API controller with representing methods.
 */
@Api(tags = {"Calculator"})
@RestController
@RequestMapping(
    path = "${calculator.api.prefix:}" + CalculatorApiLocations.CALCULATOR,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final CalculatorDtoMapper calculatorDtoMapper;
    
    /**
     * Initializes a new {@link CalculatorController} instance.
     * 
     * @param calculatorService - {@link CalculatorService} instance.
     */
    public CalculatorController(
        CalculatorService calculatorService,
        CalculatorDtoMapper calculatorDtoMapper
    ) {
        this.calculatorService = calculatorService;
        this.calculatorDtoMapper = calculatorDtoMapper;
    }

    /**
     * REST API method to calculate net price.
     *
     * @param grossPrice - The region ID.
     * @param countryIso - The region ID.
     * @return the net price region.
     */
    @ApiOperation(value = "Get specific country information by country ISO")
    @GetMapping("/{grossPrice}/{countryIso}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CalculatorDto> getRegion(
        @ApiParam(name = "grossPrice", value = "The gross price of an item", required = true) @PathVariable double grossPrice,
        @ApiParam(name = "countryIso", value = "The country ISO", required = true) @PathVariable String countryIso
    ) {
        CalculatorDto calc = this.calculatorDtoMapper.modelToDto(this.calculatorService.getNetPrice(grossPrice, countryIso));

        return new ResponseEntity<>(calc, HttpStatus.OK);
    }
}
