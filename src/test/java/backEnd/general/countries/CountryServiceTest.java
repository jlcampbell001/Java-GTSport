package backEnd.general.countries;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test for the country service.
 *
 * @author jonathan
 */
public class CountryServiceTest extends GTSportDataTesting {

    private static final String COUNTRY_6_DESCRIPTION = "COUNTRY_6";
    private static final String COUNTRY_6_REGION_KEY = REGION2.getPrimaryKey();
    private static final String NEW_COUNTRY_6_DESCRIPTION = "NEW_6TH_COUNTRY";

    private static final String BAD_COUNTRY_KEY = "C!C999999999";
    private static final String BAD_COUNTRY_DESCRIPTION = "BAD DESCRIPTION";

    private static final int EXPECTED_NUMBER_OF_COUNTRIES = 5;
    private static final int EXPECTED_NUMBER_OF_COUNTRIES_BY_REGION = 3;

    private static final String EXPECTED_COUNTRY_6_KEY = "COU900000006";

    private String country6Key = "";

    @Autowired
    private CountryService countryService;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // Add the regions to work with.
        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);

        // Add the countries to work with.
        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);
        countryRepository.saveAndFlush(COUNTRY3);
        countryRepository.saveAndFlush(COUNTRY4);
        countryRepository.saveAndFlush(COUNTRY5);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // Delete the test records.
        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY4.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY5.getPrimaryKey());
        deleteCountryTestRecord(country6Key);

        countryService.resetKeys();

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
        deleteRegionTestRecord(REGION3.getPrimaryKey());
    }

    /**
     * Test the get country Json by the primary key method.
     *
     * @throws CountryException
     */
    @Test
    public void getCountryJsonByKey() throws CountryException {
        logger.info("Get Country Json by Key: " + COUNTRY2.getPrimaryKey());

        CountryJson countryJson = countryService.getCountryJsonByKey(COUNTRY2.getPrimaryKey());

        assertEquals(countryJson.getPrimaryKey(), COUNTRY2.getPrimaryKey());
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
        logger.info("Get Country Json by Description: " + COUNTRY3.getDescription());

        CountryJson countryJson = countryService.getCountryJsonByDescription(COUNTRY3.getDescription());

        assertEquals(countryJson.getPrimaryKey(), COUNTRY3.getPrimaryKey());
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
        countryJson.setDescription(COUNTRY_6_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_6_REGION_KEY);

        countryService.saveCountry(countryJson);

        country6Key = countryJson.getPrimaryKey();
    }

    /**
     * Test updating a country record.
     *
     * @throws CountryException
     */
    @Test(dependsOnMethods = {"saveNewCountry"})
    public void updateCountry() throws CountryException {
        logger.info("Update Country: " + country6Key);

        CountryJson countryJson = new CountryJson();

        countryJson.setPrimaryKey(country6Key);
        countryJson.setDescription(NEW_COUNTRY_6_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_6_REGION_KEY);

        countryService.saveCountry(countryJson);
    }

    /**
     * Test deleting a country.
     *
     * @throws CountryException
     */
    @Test(dependsOnMethods = {"updateCountry"})
    public void deleteCountry() throws CountryException {
        logger.info("Delete Country: " + country6Key);

        countryService.deleteCountry(country6Key);
    }

    /**
     * Test getting a list of countries.
     */
    @Test(dependsOnMethods = {"deleteCountry"})
    public void getCountryList() {
        logger.info("Get Country List");

        List<CountryJson> countryJsons = countryService.getCountryList();

        assertEquals(countryJsons.size(), EXPECTED_NUMBER_OF_COUNTRIES);
        assertEquals(countryJsons.get(0).getPrimaryKey(), COUNTRY1.getPrimaryKey());
    }

    /**
     * Test getting a list of countries by region key.
     */
    @Test(dependsOnMethods = {"deleteCountry"})
    public void getCountryListByRegionKey() {
        logger.info("Get Country List by Region Key: " + REGION2.getPrimaryKey());

        List<CountryJson> countryJsons = countryService.getCountryListByRegionKey(REGION2.getPrimaryKey());

        assertEquals(countryJsons.size(), EXPECTED_NUMBER_OF_COUNTRIES_BY_REGION);
        assertEquals(countryJsons.get(0).getPrimaryKey(), COUNTRY2.getPrimaryKey());
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
        countryJson.setDescription(COUNTRY_6_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_6_REGION_KEY);

        countryService.saveCountry(countryJson);

        country6Key = countryJson.getPrimaryKey();

        assertEquals(country6Key, EXPECTED_COUNTRY_6_KEY);
    }
}
