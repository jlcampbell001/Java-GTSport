package backEnd.general.manufacturers;

import backEnd.general.manufacturers.Manufacturer;
import backEnd.general.countries.CountriesForTesting;

/**
 * Test setup data for manufacturers.
 *
 * @author jonathan
 */
public class ManufacturerForTesting {

    private static final String MANUFACTURER_1_KEY = "MAN900000001";
    private static final String MANUFACTURER_1_NAME = "MAZDA";
    private static final String MANUFACTURER_1_COUNTRY_KEY = CountriesForTesting.COUNTRY1.getPrimaryKey();

    private static final String MANUFACTURER_2_KEY = "MAN900000002";
    private static final String MANUFACTURER_2_NAME = "VOLKSWAGEN";
    private static final String MANUFACTURER_2_COUNTRY_KEY = CountriesForTesting.COUNTRY2.getPrimaryKey();

    private static final String MANUFACTURER_3_KEY = "MAN900000003";
    private static final String MANUFACTURER_3_NAME = "FORD";
    private static final String MANUFACTURER_3_COUNTRY_KEY = CountriesForTesting.COUNTRY3.getPrimaryKey();

    private static final String MANUFACTURER_4_KEY = "MAN900000004";
    private static final String MANUFACTURER_4_NAME = "MERCEDES-BENZ";
    private static final String MANUFACTURER_4_COUNTRY_KEY = CountriesForTesting.COUNTRY2.getPrimaryKey();

    private static final String MANUFACTURER_5_KEY = "MAN900000005";
    private static final String MANUFACTURER_5_NAME = "PORSCHE";
    private static final String MANUFACTURER_5_COUNTRY_KEY = CountriesForTesting.COUNTRY2.getPrimaryKey();

    private static final String MANUFACTURER_6_KEY = "MAN900000006";
    private static final String MANUFACTURER_6_NAME = "JAGUAR";
    private static final String MANUFACTURER_6_COUNTRY_KEY = CountriesForTesting.COUNTRY4.getPrimaryKey();

    private static final String MANUFACTURER_7_KEY = "MAN900000007";
    private static final String MANUFACTURER_7_NAME = "MCLAREN";
    private static final String MANUFACTURER_7_COUNTRY_KEY = CountriesForTesting.COUNTRY4.getPrimaryKey();

    private static final String MANUFACTURER_8_KEY = "MAN900000008";
    private static final String MANUFACTURER_8_NAME = "ASTON MARTIN";
    private static final String MANUFACTURER_8_COUNTRY_KEY = CountriesForTesting.COUNTRY4.getPrimaryKey();

    private static final String MANUFACTURER_9_KEY = "MAN900000009";
    private static final String MANUFACTURER_9_NAME = "BUGATTI";
    private static final String MANUFACTURER_9_COUNTRY_KEY = CountriesForTesting.COUNTRY5.getPrimaryKey();

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURER1 = new Manufacturer(MANUFACTURER_1_KEY, MANUFACTURER_1_NAME, MANUFACTURER_1_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURER2 = new Manufacturer(MANUFACTURER_2_KEY, MANUFACTURER_2_NAME, MANUFACTURER_2_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE3 = new Manufacturer(MANUFACTURER_3_KEY, MANUFACTURER_3_NAME, MANUFACTURER_3_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE4 = new Manufacturer(MANUFACTURER_4_KEY, MANUFACTURER_4_NAME, MANUFACTURER_4_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE5 = new Manufacturer(MANUFACTURER_5_KEY, MANUFACTURER_5_NAME, MANUFACTURER_5_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE6 = new Manufacturer(MANUFACTURER_6_KEY, MANUFACTURER_6_NAME, MANUFACTURER_6_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE7 = new Manufacturer(MANUFACTURER_7_KEY, MANUFACTURER_7_NAME, MANUFACTURER_7_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE8 = new Manufacturer(MANUFACTURER_8_KEY, MANUFACTURER_8_NAME, MANUFACTURER_8_COUNTRY_KEY);

    /**
     * Test data for a manufacturer.
     */
    public static final Manufacturer MANUFACTURE9 = new Manufacturer(MANUFACTURER_9_KEY, MANUFACTURER_9_NAME, MANUFACTURER_9_COUNTRY_KEY);
}
