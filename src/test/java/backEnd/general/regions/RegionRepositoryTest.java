package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the region repository.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class RegionRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String MAX_REGION_KEY = "XXX900000003";

    private static final String REGION_1_KEY = "XXX900000001";
    private static final String REGION_1_DESCRIPTION = "TEST_REGION_1";

    private static final String REGION_2_KEY = "XXX900000002";
    private static final String REGION_2_DESCRIPTION = "TEST_REGION_2";

    private static final String REGION_3_KEY = MAX_REGION_KEY;
    private static final String REGION_3_DESCRIPTION = "TEST_REGION_3";

    private static final String BAD_DESCRIPTION = "XXX_BAD_DESCRIPTION_XXX";

    @Autowired
    private RegionRepository regionRepository;

    /**
     * Setup records to test against.
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
    }

    /**
     * Delete the region records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteTestRecord(REGION_1_KEY);
        deleteTestRecord(REGION_2_KEY);
        deleteTestRecord(REGION_3_KEY);
    }

    /**
     * Tests the find by description method.
     */
    @Test
    public void findByDescription() {
        logger.info("Find by Description");
        Region region = regionRepository.findbyDescription(REGION_2_DESCRIPTION);

        assertEquals(region.getPrimaryKey(), REGION_2_KEY);
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

    private void deleteTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }
}
