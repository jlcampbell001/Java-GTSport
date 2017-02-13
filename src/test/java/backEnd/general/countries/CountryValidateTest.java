package backEnd.general.countries;

import static org.testng.AssertJUnit.assertEquals;

import backEnd.general.GTSportConfig;
import backEnd.general.regions.RegionRepository;
import backEnd.general.regions.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test for the country validate.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CountryValidateTest extends AbstractTestNGSpringContextTests {

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

    private static final String BAD_REGION_KEY = "X!X900000001";
    private static final String BAD_COUNTRY_KEY = "C!C999999999";

    @Autowired
    CountryValidate countryValidate;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    RegionRepository regionRepository;

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

        deleteRegionTestRecord(REGION_1_KEY);
        deleteRegionTestRecord(REGION_2_KEY);
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
        countryJson.setPrimaryKey(COUNTRY_1_KEY);
        countryJson.setDescription(COUNTRY_1_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_1_REGION_KEY);

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
        countryJson.setPrimaryKey(COUNTRY_1_KEY);
        countryJson.setDescription("");
        countryJson.setRegionKey(COUNTRY_1_REGION_KEY);

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
        countryJson.setPrimaryKey(COUNTRY_1_KEY);
        countryJson.setDescription(COUNTRY_1_DESCRIPTION);
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
        countryJson.setPrimaryKey(COUNTRY_1_KEY);
        countryJson.setDescription(COUNTRY_2_DESCRIPTION);
        countryJson.setRegionKey(COUNTRY_1_REGION_KEY);

        try {
            countryValidate.validateCountrySave(countryJson);
        } catch (CountryException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test saving country validate where the region key dose not exist.
     *
     * @throws CountryException
     */
    @Test(expectedExceptions = CountryException.class)
    public void validateCountrySaveBadRegionKey() throws CountryException {
        logger.info("Validate Country Save Bad Region Key: " + BAD_REGION_KEY);

        String expectedError = CountryException.REGION_DOSE_NOT_EXIST + BAD_REGION_KEY;

        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(COUNTRY_1_KEY);
        countryJson.setDescription(COUNTRY_1_DESCRIPTION);
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

        countryValidate.validateCountryDelete(COUNTRY_3_KEY);
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
