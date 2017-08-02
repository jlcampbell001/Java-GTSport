package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.countries.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the country repository.
 *
 * @author jonathan
 */
public class RegionRepositoryTest extends GTSportDataTesting {

    private static final String MAX_REGION_KEY = REGION3.getPrimaryKey();   

    private static final String BAD_DESCRIPTION = "XXX_BAD_DESCRIPTION_XXX";
    
    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 regions to work with.
        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);        
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteOwnerTestRecord(REGION1.getPrimaryKey());
        deleteOwnerTestRecord(REGION2.getPrimaryKey());
        deleteOwnerTestRecord(REGION3.getPrimaryKey());
    }

    /**
     * Tests the find by description method.
     */
    @Test
    public void findByDescription() {
        logger.info("Find by Description");
        Region region = regionRepository.findbyDescription(REGION2.getDescription());

        assertEquals(region.getPrimaryKey(), REGION2.getPrimaryKey());
    }

    /**
     * Tests the find by description method with passing a not existing
     * description.
     */
    @Test
    public void findByDescriptionBadDescription() {
        logger.info("Find by Description Bad Description: " + BAD_DESCRIPTION);
        Region region = regionRepository.findbyDescription(BAD_DESCRIPTION);

        assertNull(region);
    }

    /**
     * Test the get max key method.
     */
    @Test
    public void getMaxKey() {
        logger.info("Get Max Region Key");

        String maxKey = regionRepository.getMaxKey();

        assertEquals(maxKey, MAX_REGION_KEY);
    }
}
