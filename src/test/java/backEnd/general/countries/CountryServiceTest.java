package backEnd.general.countries;

import backEnd.general.GTSportConfig;
import backEnd.general.regions.Region;
import backEnd.general.regions.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test for the country service.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CountryServiceTest extends AbstractTestNGSpringContextTests {

    private static final String REGION_1_KEY = "REG900000001";
    private static final String REGION_1_DESCRIPTION = "REGION_1";

    private static final String REGION_2_KEY = "REG900000002";
    private static final String REGION_2_DESCRIPTION = "REGION_2";

    private static final String COUNTRY_1_KEY = "XXX900000001";
    private static final String COUNTRY_1_DESCRIPTION = "COUNTRY_1";
    private static final String COUNTRY_1_REGION_KEY = REGION_1_KEY;

    private static final String COUNTRY_2_KEY = "XXX900000002";
    private static final String COUNTRY_2_DESCRIPTION = "COUNTRY_2";
    private static final String COUNTRY_2_REGION_KEY = REGION_2_KEY;

    private static final String COUNTRY_3_KEY = "XXX900000003";
    private static final String COUNTRY_3_DESCRIPTION = "COUNTRY_3";
    private static final String COUNTRY_3_REGION_KEY = REGION_1_KEY;

    private static final String COUNTRY_4_DESCRIPTION = "COUNTRY_4";
    private static final String COUNTRY_4_REGION_KEY = REGION_2_KEY;
    private static final String NEW_COUNTRY_4_DESCRIPTION = "NEW_4TH_COUNTRY";

    private static final String BAD_COUNTRY_KEY = "C!C999999999";
    private static final String BAD_COUNTRY_DESCRIPTION = "BAD DESCRIPTION";

    private static final int EXPECTED_NUMBER_OF_COUNTRIES = 3;
    private static final int EXPECTED_NUMBER_OF_COUNTRIES_BY_REGION = 2;

    private static final String EXPECTED_COUNTRY_4_KEY = "COU900000004";

    private String country4Key = "";

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // Add the regions to work with.
        Region region1 = new Region(REGION_1_KEY, REGION_1_DESCRIPTION);
        regionRepository.saveAndFlush(region1);

        Region region2 = new Region(REGION_2_KEY, REGION_2_DESCRIPTION);
        regionRepository.saveAndFlush(region2);

        // Add the 3 countries to work with.
        Country country1 = new Country(COUNTRY_1_KEY, COUNTRY_1_DESCRIPTION, COUNTRY_1_REGION_KEY);
        countryRepository.saveAndFlush(country1);

        Country country2 = new Country(COUNTRY_2_KEY, COUNTRY_2_DESCRIPTION, COUNTRY_2_REGION_KEY);
        countryRepository.saveAndFlush(country2);

        Country country3 = new Country(COUNTRY_3_KEY, COUNTRY_3_DESCRIPTION, COUNTRY_3_REGION_KEY);
        countryRepository.saveAndFlush(country3);
    }

    /**
     * Delete the country records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // Delete the test records.
        deleteCountryTestRecord(COUNTRY_1_KEY);
        deleteCountryTestRecord(COUNTRY_2_KEY);
        deleteCountryTestRecord(COUNTRY_3_KEY);
        deleteCountryTestRecord(country4Key);
        
        countryService.resetKeys();

        deleteRegionTestRecord(REGION_1_KEY);
        deleteRegionTestRecord(REGION_2_KEY);
    }

    /**
     * Test the get country Json by the primary key method.
     *
     * @throws CountryException
     */
    @Test
    public void getCountryJsonByKey() throws CountryException {
        logger.info("Get Country Json by Key: " + COUNTRY_2_KEY);

        CountryJson countryJson = countryService.getCountryJsonByKey(COUNTRY_2_KEY);

        assertEquals(countryJson.getPrimaryKey(), COUNTRY_2_KEY);
    }

    /**
     * Test the get country Json by primary key when sending in a non-exiting
     * key.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void getCountryJsonByKeyBadCountryKey() throws CountryException {
        logger.info("Get Country Json by Key Bad Country Key: " + BAD_COUNTRY_KEY);

        String expectedError = CountryException.COUNTRY_KEY_NOT_FOUND + BAD_COUNTRY_KEY;

        try {
            countryService.getCountryJsonByKey(BAD_COUNTRY_KEY);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test the get country Json by description.
     *
     * @throws CountryException
     */
    @Test
    public void getCountryJsonByDescription() throws CountryException {
        logger.info("Get Country Json by Description: " + COUNTRY_3_DESCRIPTION);

        CountryJson countryJson = countryService.getCountryJsonByDescription(COUNTRY_3_DESCRIPTION);

        assertEquals(countryJson.getPrimaryKey(), COUNTRY_3_KEY);
    }

    /**
     * Test the get country Json by description when passing in a non-existing
     * description.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void getCountryByDescriptionBadDescription() throws CountryException {
        logger.info("Get Country by Description Bad Description: " + BAD_COUNTRY_DESCRIPTION);

        String expectedError = CountryException.COUNTRY_DESCRIPTION_NOT_FOUND + BAD_COUNTRY_DESCRIPTION;

        try {
            countryService.getCountryJsonByDescription(BAD_COUNTRY_DESCRIPTION);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test saving a new country record.
     *
     * @throws CountryException
     */
    @Test
    public void saveNewCountry() throws CountryException {
        logger.info("Save New Country");

        CountryJson countryJson = new CountryJson();

        countryJson.setPrimaryKey("");
        countryJson.setDescription(COUNTRY_4_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_4_REGION_KEY);

        countryService.saveCountry(countryJson);

        country4Key = countryJson.getPrimaryKey();
    }

    /**
     * Test updating a country record.
     *
     * @throws CountryException
     */
    @Test(dependsOnMethods = {"saveNewCountry"})
    public void updateCountry() throws CountryException {
        logger.info("Update Country: " + country4Key);

        CountryJson countryJson = new CountryJson();

        countryJson.setPrimaryKey(country4Key);
        countryJson.setDescription(NEW_COUNTRY_4_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_4_REGION_KEY);

        countryService.saveCountry(countryJson);
    }

    /**
     * Test deleting a country.
     *
     * @throws CountryException
     */
    @Test(dependsOnMethods = {"updateCountry"})
    public void deleteCountry() throws CountryException {
        logger.info("Delete Country: " + country4Key);

        countryService.deleteCountry(country4Key);
    }

    /**
     * Test getting a list of countries.
     */
    @Test(dependsOnMethods = {"deleteCountry"})
    public void getCountryList() {
        logger.info("Get Country List");

        List<CountryJson> countryJsons = countryService.getCountryList();

        assertEquals(countryJsons.size(), EXPECTED_NUMBER_OF_COUNTRIES);
        assertEquals(countryJsons.get(0).getPrimaryKey(), COUNTRY_1_KEY);
    }

    /**
     * Test getting a list of countries by region key.
     */
    @Test(dependsOnMethods = {"deleteCountry"})
    public void getCountryListByRegionKey() {
        logger.info("Get Country List by Region Key: " + REGION_1_KEY);

        List<CountryJson> countryJsons = countryService.getCountryListByRegionKey(REGION_1_KEY);

        assertEquals(countryJsons.size(), EXPECTED_NUMBER_OF_COUNTRIES_BY_REGION);
        assertEquals(countryJsons.get(0).getPrimaryKey(), COUNTRY_1_KEY);
    }

    /**
     * Test reseting the primary key.
     *
     * @throws CountryException
     */
    @Test(dependsOnMethods = {"deleteCountry"})
    public void resetKeys() throws CountryException {
        logger.info("Reset Keys");

        countryService.resetKeys();

        CountryJson countryJson = new CountryJson();

        countryJson.setPrimaryKey("");
        countryJson.setDescription(COUNTRY_4_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_4_REGION_KEY);

        countryService.saveCountry(countryJson);

        country4Key = countryJson.getPrimaryKey();

        assertEquals(country4Key, EXPECTED_COUNTRY_4_KEY);
    }

    private void deleteCountryTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }

    private void deleteRegionTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }
}
