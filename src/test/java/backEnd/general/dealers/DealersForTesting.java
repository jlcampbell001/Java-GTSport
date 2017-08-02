/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.dealers;

import backEnd.general.countries.CountriesForTesting;

/**
 *
 * @author jonathan
 */
public class DealersForTesting {

    private static final String DEALER_1_KEY = "DEA900000001";
    private static final String DEALER_1_NAME = "DEALER_1";
    private static final String DEALER_1_COUNTRY_KEY = CountriesForTesting.country1.getPrimaryKey();

    private static final String DEALER_2_KEY = "DEA900000002";
    private static final String DEALER_2_NAME = "DEALER_2";
    private static final String DEALER_2_COUNTRY_KEY = CountriesForTesting.country2.getPrimaryKey();

    private static final String DEALER_3_KEY = "DEA900000003";
    private static final String DEALER_3_NAME = "DEALER_3";
    private static final String DEALER_3_COUNTRY_KEY = CountriesForTesting.country1.getPrimaryKey();
    
    public static Dealer dealer1 = new Dealer(DEALER_1_KEY, DEALER_1_NAME, DEALER_1_COUNTRY_KEY);
    public static Dealer dealer2 = new Dealer(DEALER_2_KEY, DEALER_2_NAME, DEALER_2_COUNTRY_KEY);
    public static Dealer dealer3 = new Dealer(DEALER_3_KEY, DEALER_3_NAME, DEALER_3_COUNTRY_KEY);
}
