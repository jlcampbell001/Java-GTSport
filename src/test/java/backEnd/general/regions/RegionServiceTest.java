/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import backEnd.general.GTSportConfig;
import java.util.List;
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

    private static final String REGION_4_DESCRIPTION = "TEST_REGION_4";
    private static final String REGION_4_NEW_DESCRIPTION = "NEW_REGION_4";

    private static final String BAD_REGION_KEY = "X!!990000009";
    private static final String BAD_DESCRIPTION = "XXX_BAD_DESCRIP_XXX";

    private static final String EXPECTED_MAX_KEY = "REG900000004";
    private static final int EXPECTED_NUMBER_OF_RECORDS = 4;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

    private String region4Key = "";

    /**
     * This will setup records for the region tests.
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
        deleteTestRecord(region4Key);
    }

    /**
     * Tests the getting a region by primary key.
     *
     * @throws RegionException
     */
    @Test
    public void getRegionJsonByKey() throws RegionException {
        logger.info("Get Region Json by Key: " + REGION_3_KEY);

        RegionJson regionJson = regionService.getRegionJsonByKey(REGION_3_KEY);

        assertEquals(regionJson.getPrimaryKey(), REGION_3_KEY);
    }

    /**
     * Tests getting the region by primary key and passing a non-existing
     * primary key.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void getRegionJsonByKeyBadKey() throws RegionException {
        logger.info("Get Region Json by Key Bad Key: " + BAD_REGION_KEY);

        String expectedError = RegionException.REGION_NOT_FOUND_BY_KEY + BAD_REGION_KEY;

        try {
            regionService.getRegionJsonByKey(BAD_REGION_KEY);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    /**
     * Tests getting the region by a description.
     *
     * @throws RegionException
     */
    @Test
    public void getRegionJsonByDescription() throws RegionException {
        logger.info("Get Region Json by Description: " + REGION_1_DESCRIPTION);

        RegionJson regionJson = regionService.getRegionJsonByDescription(REGION_1_DESCRIPTION);

        assertEquals(regionJson.getPrimaryKey(), REGION_1_KEY);
    }

    /**
     * Tests getting the region by a description by passing in a non-existing
     * description.
     *
     * @throws RegionException
     */
    @Test(expectedExceptions = RegionException.class)
    public void getRegionJsonByDescriptionBadDescription() throws RegionException {
        logger.info("Get Region Json by Description Bad Description: " + BAD_DESCRIPTION);

        String expectedError = RegionException.REGION_NOT_FOUND_BY_DESCRIPTION + BAD_DESCRIPTION;
        try {
            regionService.getRegionJsonByDescription(BAD_DESCRIPTION);
        } catch (RegionException re) {
            assertEquals(re.getMessage(), expectedError);
            throw re;
        }
    }

    /**
     * Tests saving a new region.
     *
     * @throws RegionException
     */
    @Test
    public void saveNewRegion() throws RegionException {
        logger.info("Save New Region");

        RegionJson regionJson = new RegionJson();
        regionJson.setDescription(REGION_4_DESCRIPTION);

        regionService.saveRegion(regionJson);

        region4Key = regionJson.getPrimaryKey();
    }

    /**
     * Tests saving an update for a region.
     *
     * @throws RegionException
     */
    @Test(dependsOnMethods = {"saveNewRegion"})
    public void updateRegion() throws RegionException {
        logger.info("Update Region");

        RegionJson regionJson = new RegionJson();
        regionJson.setPrimaryKey(region4Key);
        regionJson.setDescription(REGION_4_NEW_DESCRIPTION);

        regionService.saveRegion(regionJson);
    }

    /**
     * Tests deleting a region.
     *
     * @throws RegionException
     */
    @Test(dependsOnMethods = {"updateRegion"})
    public void deleteRegion() throws RegionException {
        logger.info("Delete Region: " + region4Key);

        regionService.deleteRegion(region4Key);
    }

    /**
     * Tests the reset keys function.
     *
     * @throws RegionException
     */
    @Test(dependsOnMethods = {"deleteRegion"})
    public void resetKeys() throws RegionException {
        logger.info("Reset Keys");

        regionService.resetKeys();

        RegionJson regionJson = new RegionJson();
        regionJson.setDescription(REGION_4_DESCRIPTION);

        regionService.saveRegion(regionJson);

        region4Key = regionJson.getPrimaryKey();

        assertEquals(region4Key, EXPECTED_MAX_KEY);
    }

    /**
     * Tests getting a list of regions.
     */
    @Test(dependsOnMethods = {"resetKeys"})
    public void getRegionList() {
        logger.info("Get Region List");
        List<RegionJson> regionJsons = regionService.getRegionList();

        assertEquals(regionJsons.size(), EXPECTED_NUMBER_OF_RECORDS);
        assertEquals(regionJsons.get(0).getPrimaryKey(), REGION_1_KEY);
    }

    private void deleteTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }
}
