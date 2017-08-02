/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

/**
 *
 * @author jonathan
 */
public class RegionsForTesting {
    private static final String REGION_1_KEY = "REG900000001";
    private static final String REGION_1_DESCRIPTION = "TEST_REGION_1";

    private static final String REGION_2_KEY = "REG900000002";
    private static final String REGION_2_DESCRIPTION = "TEST_REGION_2";

    private static final String REGION_3_KEY = "REG900000003";
    private static final String REGION_3_DESCRIPTION = "TEST_REGION_3";
    
    public static Region region1 = new Region(REGION_1_KEY, REGION_1_DESCRIPTION);
    public static Region region2 = new Region(REGION_2_KEY, REGION_2_DESCRIPTION);
    public static Region region3 = new Region(REGION_3_KEY, REGION_3_DESCRIPTION);
}
