package backEnd.general.countries;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the country repository.
 *
 * @author jonathan
 */
public class CountryRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = COUNTRY5.getPrimaryKey();

    private static final int EXPECTED_REGION_2_RECORDS = 3;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        
        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);

        // add the countries to work with.
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

        // delete the test records.
        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY4.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY5.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
        deleteRegionTestRecord(REGION3.getPrimaryKey());
    }

    /**
     * Test the find by description method.
     */
    @Test
    public void findByDescription() {
        logger.info("Find By Description");

        Country country = countryRepository.findByDescription(COUNTRY2.getDescription());

        assertEquals(country.getPrimaryKey(), COUNTRY2.getPrimaryKey());
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
        List<Country> countries = countryRepository.findAllByRegionKey(REGION2.getPrimaryKey());

        assertEquals(countries.size(), EXPECTED_REGION_2_RECORDS);
        assertEquals(countries.get(1).getPrimaryKey(), COUNTRY4.getPrimaryKey());
    }
}
