package com.globalsavings.calculator.domain.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.globalsavings.calculator.domain.exception.ItemNotFoundException;
import com.globalsavings.calculator.domain.region.RegionModel;
import com.globalsavings.calculator.domain.region.RegionServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public CalculatorModel getNetPrice(double grossPrice, String countryIso) {
        RegionModel region = RegionServiceImpl.REGIONS.get(countryIso);

        if (region == null) {
            String msg = String.format("Region with country ISO=%s does not exist!", countryIso);
            log.error(msg);
            throw new ItemNotFoundException(msg);
        }

        BigDecimal actualPercentage = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(region.getVAT()));
        BigDecimal price = BigDecimal.valueOf(grossPrice);

        double netPrice = price.multiply(actualPercentage).setScale(2, RoundingMode.HALF_UP).doubleValue();

        log.info("For country ISO = " + countryIso + ", net price = " + netPrice);

        CalculatorModel calculatorModel = new CalculatorModel();
        calculatorModel.setCountryIso(countryIso);
        calculatorModel.setGrossPrice(grossPrice);
        calculatorModel.setNetPrice(netPrice);

        return calculatorModel;
    }
    
}
