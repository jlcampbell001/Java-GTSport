package backEnd.general.countries;

import static org.testng.AssertJUnit.assertEquals;

import backEnd.general.GTSportDataTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test for the country validate.
 *
 * @author jonathan
 */
public class CountryValidateTest extends GTSportDataTesting {

    private static final String BAD_REGION_KEY = "X!X900000001";
    private static final String BAD_COUNTRY_KEY = "C!C999999999";

    @Autowired
    private CountryValidate countryValidate;

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

        // Add the 3 countries to work with.
        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);
        countryRepository.saveAndFlush(COUNTRY3);

        // Add dealer record to work with.
        dealerRepository.saveAndFlush(DEALER1);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // Delete the test records.
        deleteDealerTestRecord(DEALER1.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    /**
     * Test the validate country save.
     *
     * @throws CountryException
     */
    @Test
    public void validateCountrySave() throws CountryException {
        logger.info("Validate Country Save");

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY1.getPrimaryKey());
        countryJson.setDescription(COUNTRY1.getDescription());
        countryJson.setRegionKey(COUNTRY1.getRegionKey());

        countryValidate.validateCountrySave(countryJson);
    }

    /**
     * Test the country validate save for missing description.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountrySaveDescriptionNotSet() throws CountryException {
        logger.info("Validate Country Save Description Not Set");

        String expectedError = CountryException.DESCRIPTION_NOT_SET;

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY1.getPrimaryKey());
        countryJson.setDescription("");
        countryJson.setRegionKey(COUNTRY1.getRegionKey());

        try {
            countryValidate.validateCountrySave(countryJson);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test the saving country validate where the region key is not filled.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountrySaveRegionKeyNotSet() throws CountryException {
        logger.info("Validate Country Save Region Key Not Set");

        String expectedError = CountryException.REGION_KEY_NOT_SET;

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY1.getPrimaryKey());
        countryJson.setDescription(COUNTRY1.getDescription());
        countryJson.setRegionKey("");

        try {
            countryValidate.validateCountrySave(countryJson);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test saving country validate where the description already exists.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountrySaveDescriptionAlreadyExists() throws CountryException {
        logger.info("Validate Country Save Description Already Exists");

        String expectedError = CountryException.DESCRIPTION_ALREADY_EXISTS;

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY1.getPrimaryKey());
        countryJson.setDescription(COUNTRY2.getDescription());
        countryJson.setRegionKey(COUNTRY1.getRegionKey());

        try {
            countryValidate.validateCountrySave(countryJson);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test saving country validate where the region key does not exist.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountrySaveBadRegionKey() throws CountryException {
        logger.info("Validate Country Save Bad Region Key: " + BAD_REGION_KEY);

        String expectedError = CountryException.REGION_DOES_NOT_EXIST + BAD_REGION_KEY;

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY1.getPrimaryKey());
        countryJson.setDescription(COUNTRY1.getDescription());
        countryJson.setRegionKey(BAD_REGION_KEY);

        try {
            countryValidate.validateCountrySave(countryJson);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test deleting country validate.
     *
     * @throws CountryException
     */
    @Test
    public void validateCountryDelete() throws CountryException {
        logger.info("Validate Country Delete");

        countryValidate.validateCountryDelete(COUNTRY3.getPrimaryKey());
    }

    /**
     * Test the country delete validate where the primary key can not be found.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountryDeleteBadCountryKey() throws CountryException {
        logger.info("Validate Country Delete Bad Country Key: " + BAD_COUNTRY_KEY);

        String expectedError = CountryException.COUNTRY_KEY_NOT_FOUND_DO_DELETE + BAD_COUNTRY_KEY;
        try {
            countryValidate.validateCountryDelete(BAD_COUNTRY_KEY);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test for the error where the country key is still in use and can not be
     * deleted.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountryDeleteCountryInUse() throws CountryException {
        logger.info("Validate Country Delete Country In Use: " + DEALER1.getCountryKey());

        String expectedError = CountryException.COUNTRY_IS_IN_USE;
        try {
            countryValidate.validateCountryDelete(DEALER1.getCountryKey());
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
}
