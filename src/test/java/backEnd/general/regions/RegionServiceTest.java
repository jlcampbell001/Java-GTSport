/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class RegionServiceTest extends AbstractTestNGSpringContextTests {

    private static final String REGION_1_KEY = "XXX900000001";
    private static final String REGION_1_DESCRIPTION = "TEST_REGION_1";

    private static final String REGION_2_KEY = "XXX900000002";
    private static final String REGION_2_DESCRIPTION = "TEST_REGION_2";

    private static final String REGION_3_KEY = "XXX900000003";
    private static final String REGION_3_DESCRIPTION = "TEST_REGION_3";

    private static final String BAD_REGION_KEY = "X!!990000009";
    private static final String BAD_DESCRIPTION = "XXX_BAD_DESCRIP_XXX";

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

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
    
    @Test
    public void getRegionJsonByKey() throws RegionException {
        logger.info("Get Region Json by Key: " + REGION_3_KEY);
        
        RegionJson regionJson = regionService.getRegionJsonByKey(REGION_3_KEY);
        
        assertEquals(regionJson.getPrimaryKey(), REGION_3_KEY);
    }
    
    @Test(expectedExceptions = RegionException.class)
    public void getRegionJsonByKeyBadKey() throws RegionException {
        logger.info("Get Region Json by Key Bad Key: " + BAD_REGION_KEY);
        
        String expectedError = RegionException.REGION_NOT_FOUND_BY_KEY + BAD_REGION_KEY;
        
        try {
            RegionJson regionJson = regionService.getRegionJsonByKey(BAD_REGION_KEY);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }
    
    @Test
    public void getRegionJsonByDescription() throws RegionException {
        logger.info("Get Region Json by Description: " + REGION_1_DESCRIPTION);
        
        RegionJson regionJson = regionService.getRegionJsonByDescription(REGION_1_DESCRIPTION);
        
        assertEquals(regionJson.getPrimaryKey(), REGION_1_KEY);
    }
    
    @Test(expectedExceptions = RegionException.class) 
    public void getRegionJsonByDescriptionBadDescription() throws RegionException {
        logger.info("Get Region Json by Description Bad Description: " + BAD_DESCRIPTION);
        
        String expectedError = RegionException.REGION_NOT_FOUND_BY_DESCRIPTION + BAD_DESCRIPTION;
        try {
            RegionJson regionJson = regionService.getRegionJsonByDescription(BAD_DESCRIPTION);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    private void deleteTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }
}
