package com.globalsavings.calculator.domain.region;

import java.util.List;

/**
 * Provides methods for working with regions.
 */
public interface RegionService {

    /**
     * Adds new region.
     *
     * @param regionModel - The region to be added.
     * @return New region added.
     */
    RegionModel addRegion(RegionModel regionModel);

    /**
     * Updates the specified region and returns updated model.
     *
     * @param regionModel - The region to be update.
     * @return Updated region model.
     */
    RegionModel updateRegion(RegionModel regionModel);

    /**
     * Removes country with given country ISO.
     *
     * @param countryIso - The country ISO.
     */
    void deleteRegion(String countryIso);

    /**
     * Returns region by it's country ISO if it exists.
     *
     * @param countryIso - The country ISO to get.
     * @return The {@link RegionModel} specified by country ISO or null if no region found.
     */
    RegionModel getRegion(String countryIso);

    /**
     * Returns all regions.
     *
     * @return The list of regions.
     */
    List<RegionModel> getAllRegions();
}
