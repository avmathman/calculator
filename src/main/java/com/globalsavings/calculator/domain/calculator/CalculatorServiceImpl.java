package com.globalsavings.calculator.domain.calculator;

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

        double netPrice = (1 - region.getVAT()) * grossPrice;
        log.info("For country ISO="+countryIso+" net price=" + netPrice);

        CalculatorModel calculatorModel = new CalculatorModel();
        calculatorModel.setCountryIso(countryIso);
        calculatorModel.setGrossPrice(grossPrice);
        calculatorModel.setNetPrice(netPrice);

        return calculatorModel;
    }
    
}
