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
    private static final String COUNTRY_1_DESCRIPTION = "JAPAN";
    private static final String COUNTRY_1_REGION_KEY = RegionsForTesting.REGION3.getPrimaryKey();

    private static final String COUNTRY_2_KEY = "COU900000002";
    private static final String COUNTRY_2_DESCRIPTION = "GERMANY";
    private static final String COUNTRY_2_REGION_KEY = RegionsForTesting.REGION2.getPrimaryKey();

    private static final String COUNTRY_3_KEY = "COU900000003";
    private static final String COUNTRY_3_DESCRIPTION = "USA";
    private static final String COUNTRY_3_REGION_KEY = RegionsForTesting.REGION1.getPrimaryKey();

    private static final String COUNTRY_4_KEY = "COU900000004";
    private static final String COUNTRY_4_DESCRIPTION = "UNITED KINGDOM";
    private static final String COUNTRY_4_REGION_KEY = RegionsForTesting.REGION2.getPrimaryKey();

    private static final String COUNTRY_5_KEY = "COU900000005";
    private static final String COUNTRY_5_DESCRIPTION = "FRANCE";
    private static final String COUNTRY_5_REGION_KEY = RegionsForTesting.REGION2.getPrimaryKey();

    /**
     * Testing data for a country.
     */
    public static final Country COUNTRY1 = new Country(COUNTRY_1_KEY, COUNTRY_1_DESCRIPTION,
            COUNTRY_1_REGION_KEY);

    /**
     * Testing data for a country.
     */
    public static final Country COUNTRY2 = new Country(COUNTRY_2_KEY, COUNTRY_2_DESCRIPTION,
            COUNTRY_2_REGION_KEY);

    /**
     * Testing data for a country.
     */
    public static final Country COUNTRY3 = new Country(COUNTRY_3_KEY, COUNTRY_3_DESCRIPTION,
            COUNTRY_3_REGION_KEY);

    /**
     * Testing data for a country.
     */
    public static final Country COUNTRY4 = new Country(COUNTRY_4_KEY, COUNTRY_4_DESCRIPTION,
            COUNTRY_4_REGION_KEY);

    /**
     * Testing data for a country.
     */
    public static final Country COUNTRY5 = new Country(COUNTRY_5_KEY, COUNTRY_5_DESCRIPTION,
            COUNTRY_5_REGION_KEY);
}
