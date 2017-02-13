package backEnd.general.countries;

import backEnd.general.GTSportConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the country repository.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CountryRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String EXPECTED_MAX_KEY = "XXX900000003";
    private static final String REGION_KEY_1 = "REG900000001";

    private static final String COUNTRY_1_KEY = "XXX900000001";
    private static final String COUNTRY_1_DESCRIPTION = "COUNTRY_1";
    private static final String COUNTRY_1_REGION_KEY = REGION_KEY_1;

    private static final String COUNTRY_2_KEY = "XXX900000002";
    private static final String COUNTRY_2_DESCRIPTION = "COUNTRY_2";
    private static final String COUNTRY_2_REGION_KEY = "REG900000002";

    private static final String COUNTRY_3_KEY = EXPECTED_MAX_KEY;
    private static final String COUNTRY_3_DESCRIPTION = "COUNTRY_3";
    private static final String COUNTRY_3_REGION_KEY = REGION_KEY_1;

    private static final int EXPECTED_REGION_1_RECORDS = 2;

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 countries to work with.
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

        // delete the test records.
        deleteTestRecord(COUNTRY_1_KEY);
        deleteTestRecord(COUNTRY_2_KEY);
        deleteTestRecord(COUNTRY_3_KEY);
    }

    /**
     * Test the find by description method.
     */
    @Test
    public void findByDescription() {
        logger.info("Find By Description");

        Country country = countryRepository.findByDescription(COUNTRY_2_DESCRIPTION);

        assertEquals(country.getPrimaryKey(), COUNTRY_2_KEY);
    }

    /**
     * Test the get max key method.
     */
    @Test
    public void getMaxKey() {
        String maxKey = countryRepository.getMaxKey();

        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }

    /**
     * Test the find all by region key method.
     */
    @Test
    public void findAllByRegionKey() {
        List<Country> countries = countryRepository.findAllByRegionKey(REGION_KEY_1);

        assertEquals(countries.size(), EXPECTED_REGION_1_RECORDS);
        assertEquals(countries.get(1).getPrimaryKey(), COUNTRY_3_KEY);
    }

    private void deleteTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }
}
