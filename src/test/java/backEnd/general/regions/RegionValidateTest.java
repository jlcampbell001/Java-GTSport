package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test the region validation.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class RegionValidateTest extends AbstractTestNGSpringContextTests {

    private static final String REGION_1_KEY = "XXX900000001";
    private static final String REGION_1_DESCRIPTION = "TEST_REGION_1";

    private static final String REGION_2_KEY = "XXX900000002";
    private static final String REGION_2_DESCRIPTION = "TEST_REGION_2";

    private static final String REGION_3_KEY = "XXX900000003";
    private static final String REGION_3_DESCRIPTION = "TEST_REGION_3";

    private static final String BAD_REGION_KEY = "X!!990000009";

    private static final String COUNTRY_KEY = "COU900000001";
    private static final String COUNTRY_DESCRIPTION = "REGION_TEST_COUNTRY";

    @Autowired
    private RegionValidate regionValidate;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Sets up the data in the regions for testing.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 regions to work with.
        Region region1 = new Region(REGION_1_KEY, REGION_1_DESCRIPTION);
        regionRepository.saveAndFlush(region1);

        Region region2 = new Region(REGION_2_KEY, REGION_2_DESCRIPTION);
        regionRepository.saveAndFlush(region2);

        Region region3 = new Region(REGION_3_KEY, REGION_3_DESCRIPTION);
        regionRepository.saveAndFlush(region3);

        // add a country to work with.
        Country country = new Country(COUNTRY_KEY, COUNTRY_DESCRIPTION, REGION_1_KEY);
        countryRepository.saveAndFlush(country);
    }

    /**
     * Delete the region records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteRegionTestRecord(REGION_1_KEY);
        deleteRegionTestRecord(REGION_2_KEY);
        deleteRegionTestRecord(REGION_3_KEY);
        deleteCountryTestRecord(COUNTRY_KEY);
    }

    /**
     * Test the validating the region save.
     *
     * @throws RegionException
     */
    @Test
    public void validateRegionSave() throws RegionException {
        logger.info("Validate Region Save");

        RegionJson regionJson = new RegionJson();
        regionJson.setPrimaryKey(REGION_1_KEY);
        regionJson.setDescription(REGION_1_DESCRIPTION);

        regionValidate.validateRegionSave(regionJson);
    }

    /**
     * Test the region save validating with a missing description.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void validateRegionSaveDescriptionNotSet() throws RegionException {
        logger.info("Validate Region Save Description Not Set");

        String expectedError = RegionException.REGION_DESCRIPTION_NOT_SET;

        try {
            RegionJson missingRegionJson = new RegionJson();
            missingRegionJson.setDescription("");

            regionValidate.validateRegionSave(missingRegionJson);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    /**
     * Test saving a region validation where the description already exists.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void validateRegionSaveDescriptionAlreadyExists() throws RegionException {
        logger.info("Validate Region Save Description Already Exists: " + REGION_2_DESCRIPTION);

        String expectedError = RegionException.REGION_DESCRIPTION_ALREADY_EXISTS + REGION_2_DESCRIPTION;

        try {
            RegionJson duplicateRegionJson = new RegionJson();
            duplicateRegionJson.setDescription(REGION_2_DESCRIPTION);

            regionValidate.validateRegionSave(duplicateRegionJson);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    /**
     * Test deleting a region validation.
     *
     * @throws RegionException
     */
    @Test()
    public void validateRegionDelete() throws RegionException {
        logger.info("Validate Region Delete");

        regionValidate.validateRegionDelete(REGION_3_KEY);
    }

    /**
     * Test deleting a region validation where the primary key is not in the
     * regions table.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void validateRegionDeleteKeyNotFound() throws RegionException {
        logger.info("Validate Region Delete Bad Key: " + BAD_REGION_KEY);

        String expectedError = RegionException.REGION_KEY_NOT_FOUND_TO_DELETE + BAD_REGION_KEY;

        try {
            regionValidate.validateRegionDelete(BAD_REGION_KEY);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    /**
     * Test deleting a region validation where the primary key is still in use in a country.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void validateRegionDeleteKeyRegionInCountry() throws RegionException {
        logger.info("Validate Region Delete Region In Country: " + REGION_1_KEY);

        String expectedError = RegionException.REGION_CANNOT_DELETE_IN_USE_COUNTRY;

        try {
            regionValidate.validateRegionDelete(REGION_1_KEY);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    private void deleteRegionTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }

    private void deleteCountryTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }
}
