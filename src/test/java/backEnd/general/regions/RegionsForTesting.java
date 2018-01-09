package backEnd.general.regions;

/**
 * Test data for regions.
 * @author jonathan
 */
public class RegionsForTesting {
    private static final String REGION_1_KEY = "REG900000001";
    private static final String REGION_1_DESCRIPTION = "AMERICA";

    private static final String REGION_2_KEY = "REG900000002";
    private static final String REGION_2_DESCRIPTION = "EUROPE";

    private static final String REGION_3_KEY = "REG900000003";
    private static final String REGION_3_DESCRIPTION = "ASIAN-PACIFIC";
    
    /**
     * Test data for a region.
     */
    public static final Region REGION1 = new Region(REGION_1_KEY, REGION_1_DESCRIPTION);

    /**
     * Test data for a region.
     */
    public static final Region REGION2 = new Region(REGION_2_KEY, REGION_2_DESCRIPTION);

    /**
     * Test data for a region.
     */
    public static final Region REGION3 = new Region(REGION_3_KEY, REGION_3_DESCRIPTION);
}
