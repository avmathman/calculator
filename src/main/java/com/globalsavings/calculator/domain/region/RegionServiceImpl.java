package com.globalsavings.calculator.domain.region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.globalsavings.calculator.domain.exception.DuplicateItemException;
import com.globalsavings.calculator.domain.exception.ItemNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    public static final Map<String, RegionModel> REGIONS = new HashMap<>();

    @Override
    public RegionModel addRegion(RegionModel regionModel) {
        RegionModel region = REGIONS.get(regionModel.getCountryIso());

        if (region != null) {
            String msg = String.format("Region with country ISO=%s exists!", regionModel.getCountryIso());
            log.error(msg);
            throw new DuplicateItemException(msg);
        }

        REGIONS.put(regionModel.getCountryIso(), regionModel);
        log.info("Added region " + regionModel.toString());
        return regionModel;
    }

    @Override
    public RegionModel updateRegion(RegionModel regionModel) {
        RegionModel region = REGIONS.get(regionModel.getCountryIso());
        
        if (region == null) {
            String msg = String.format("Region with country ISO=%s does not exist!", regionModel.getCountryIso());
            log.error(msg);
            throw new ItemNotFoundException(msg);
        }

        REGIONS.put(region.getCountryIso(), regionModel);
        log.info("Updated region " + regionModel.toString());
        return regionModel;
    }

    @Override
    public void deleteRegion(String countryIso) {
        RegionModel region = REGIONS.get(countryIso);
        
        if (region == null) {
            String msg = String.format("Region with country ISO=%s does not exist to delete", countryIso);
            log.error(msg);
            throw new ItemNotFoundException(msg);
        }

        REGIONS.remove(countryIso);
        log.info("Remove region with countryIso="+ countryIso);
    }

    @Override
    public RegionModel getRegion(String countryIso) {
        RegionModel region = REGIONS.get(countryIso);
        
        if (region == null) {
            String msg = String.format("Region with country ISO=%s does not exist!", countryIso);
            log.error(msg);
            throw new ItemNotFoundException(msg);
        }

        log.info("Retrieved region information for countryIso=" + countryIso);
        return region;
    }

    @Override
    public List<RegionModel> getAllRegions() {
        log.info("Retrieved all existing region information.");
        return REGIONS.values().stream().collect(Collectors.toList());
    }

    @Override
    public void clearRegions() {
        REGIONS.clear();
    }
    
}
