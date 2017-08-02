/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.owners;

/**
 *
 * @author jonathan
 */
public class OwnersForTesting {
    private static final String OWNER_1_KEY = "OWN900000001";
    private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
    private static final boolean OWNER_1_DEFAULT = false;

    private static final String OWNER_2_KEY = "OWN900000002";
    private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
    private static final boolean OWNER_2_DEFAULT = true;

    private static final String OWNER_3_KEY = "OWN900000003";
    private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
    private static final boolean OWNER_3_DEFAULT = false;
    
    public static Owner owner1 = new Owner(OWNER_1_KEY, OWNER_1_NAME, OWNER_1_DEFAULT);
    public static Owner owner2 = new Owner(OWNER_2_KEY, OWNER_2_NAME, OWNER_2_DEFAULT);
    public static Owner owner3 = new Owner(OWNER_3_KEY, OWNER_3_NAME, OWNER_3_DEFAULT);
}
