package backEnd.general.dealers;

import backEnd.general.countries.CountriesForTesting;

/**
 * Test setup data for dealers.
 *
 * @author jonathan
 */
public class DealersForTesting {

    private static final String DEALER_1_KEY = "DEA900000001";
    private static final String DEALER_1_NAME = "DEALER_1";
    private static final String DEALER_1_COUNTRY_KEY = CountriesForTesting.COUNTRY1.getPrimaryKey();

    private static final String DEALER_2_KEY = "DEA900000002";
    private static final String DEALER_2_NAME = "DEALER_2";
    private static final String DEALER_2_COUNTRY_KEY = CountriesForTesting.COUNTRY2.getPrimaryKey();

    private static final String DEALER_3_KEY = "DEA900000003";
    private static final String DEALER_3_NAME = "DEALER_3";
    private static final String DEALER_3_COUNTRY_KEY = CountriesForTesting.COUNTRY1.getPrimaryKey();

    /**
     * Test data for a dealer.
     */
    public static final Dealer DEALER1 = new Dealer(DEALER_1_KEY, DEALER_1_NAME, DEALER_1_COUNTRY_KEY);

    /**
     * Test data for a dealer.
     */
    public static final Dealer DEALER2 = new Dealer(DEALER_2_KEY, DEALER_2_NAME, DEALER_2_COUNTRY_KEY);

    /**
     * Test data for a dealer.
     */
    public static final Dealer DEALER3 = new Dealer(DEALER_3_KEY, DEALER_3_NAME, DEALER_3_COUNTRY_KEY);
}
