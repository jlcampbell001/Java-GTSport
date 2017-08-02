package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
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
public class RegionValidateTest extends GTSportDataTesting {

    private static final String BAD_REGION_KEY = "X!!990000009";

    @Autowired
    private RegionValidate regionValidate;

    /**
     * Sets up the data in the regions for testing.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 regions to work with.
        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);

        // add a country to work with.
        countryRepository.saveAndFlush(COUNTRY1);
    }

    /**
     * Delete the region records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
        deleteRegionTestRecord(REGION3.getPrimaryKey());        
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
        regionJson.setPrimaryKey(REGION1.getPrimaryKey());
        regionJson.setDescription(REGION1.getDescription());

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
        logger.info("Validate Region Save Description Already Exists: " + REGION2.getDescription());

        String expectedError = RegionException.REGION_DESCRIPTION_ALREADY_EXISTS 
                + REGION2.getDescription();

        try {
            RegionJson duplicateRegionJson = new RegionJson();
            duplicateRegionJson.setDescription(REGION2.getDescription());

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

        regionValidate.validateRegionDelete(REGION3.getPrimaryKey());
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
        logger.info("Validate Region Delete Region In Country: " + REGION1.getPrimaryKey());

        String expectedError = RegionException.REGION_CANNOT_DELETE_IN_USE_COUNTRY;

        try {
            regionValidate.validateRegionDelete(REGION1.getPrimaryKey());
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }
}
