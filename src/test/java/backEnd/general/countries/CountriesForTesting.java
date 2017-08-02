/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.countries;

import backEnd.general.regions.RegionsForTesting;

/**
 *
 * @author jonathan
 */
public class CountriesForTesting {

    private static final String COUNTRY_1_KEY = "COU900000001";
    private static final String COUNTRY_1_DESCRIPTION = "COUNTRY_1";
    private static final String COUNTRY_1_REGION_KEY = RegionsForTesting.region1.getPrimaryKey();

    private static final String COUNTRY_2_KEY = "COU900000002";
    private static final String COUNTRY_2_DESCRIPTION = "COUNTRY_2";
    private static final String COUNTRY_2_REGION_KEY = RegionsForTesting.region2.getPrimaryKey();

    private static final String COUNTRY_3_KEY = "COU900000003";
    private static final String COUNTRY_3_DESCRIPTION = "COUNTRY_3";
    private static final String COUNTRY_3_REGION_KEY = RegionsForTesting.region1.getPrimaryKey();

    public static Country country1 = new Country(COUNTRY_1_KEY, COUNTRY_1_DESCRIPTION, 
            COUNTRY_1_REGION_KEY);
    public static Country country2 = new Country(COUNTRY_2_KEY, COUNTRY_2_DESCRIPTION, 
            COUNTRY_2_REGION_KEY);
    public static Country country3 = new Country(COUNTRY_3_KEY, COUNTRY_3_DESCRIPTION, 
            COUNTRY_3_REGION_KEY);
}
