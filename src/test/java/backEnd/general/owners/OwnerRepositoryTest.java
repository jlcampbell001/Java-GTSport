package backEnd.general.owners;

import static org.testng.Assert.assertEquals;

import java.util.List;

import backEnd.general.GTSportConfig;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertNull;
import org.testng.annotations.AfterClass;

/**
 * Tests for testing the owner repository.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class OwnerRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String MAX_OWN_KEY = "XXX900000003";

    private static final String OWNER_1_KEY = "XXX900000001";
    private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
    private static final boolean OWNER_1_DEFAULT = false;

    private static final String OWNER_2_KEY = "XXX900000002";
    private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
    private static final boolean OWNER_2_DEFAULT = true;

    private static final String OWNER_3_KEY = MAX_OWN_KEY;
    private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
    private static final boolean OWNER_3_DEFAULT = false;

    private static final String BAD_OWNER_NAME = "XXX_";

    private static final int NUMBER_OF_DEFAULT_OWNERS = 1;

    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Setup owner records for testing.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 owners to work with.
        Owner owner1 = new Owner(OWNER_1_KEY, OWNER_1_NAME, OWNER_1_DEFAULT);
        ownerRepository.saveAndFlush(owner1);

        Owner owner2 = new Owner(OWNER_2_KEY, OWNER_2_NAME, OWNER_2_DEFAULT);
        ownerRepository.saveAndFlush(owner2);

        Owner owner3 = new Owner(OWNER_3_KEY, OWNER_3_NAME, OWNER_3_DEFAULT);
        ownerRepository.saveAndFlush(owner3);
    }

    /**
     * Delete the owner records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteTestRecord(OWNER_1_KEY);
        deleteTestRecord(OWNER_2_KEY);
        deleteTestRecord(OWNER_3_KEY);
    }

    /**
     * Get an owner record by the owner name.
     */
    @Test
    public void findOwnerByName() {
        logger.info("Find Owner By Name: " + OWNER_3_NAME);

        Owner foundOwner = ownerRepository.findByName(OWNER_3_NAME);

        assertEquals(foundOwner.getPrimaryKey(), OWNER_3_KEY);
    }

    /**
     * Test the error of trying to get an owner record with a non-existing owner
     * name.
     */
    @Test
    public void findOwnerByNameBadOwer() {
        logger.info("Find Owner By Name Bad Owner: " + BAD_OWNER_NAME);

        Owner foundOwner = ownerRepository.findByName(BAD_OWNER_NAME);

        assertNull(foundOwner);
    }

    /**
     * Get the default owner record.
     */
    @Test
    public void findDefaultOwner() {
        logger.info("Find Default Owner");

        Owner foundOwner = ownerRepository.findDefaultOwner();

        assertEquals(foundOwner.getPrimaryKey(), OWNER_2_KEY);
    }

    /**
     * Get the highest primary key in the owner table.
     */
    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");

        String maxKey = ownerRepository.getMaxKey();

        assertEquals(maxKey, MAX_OWN_KEY);
    }

    /**
     * Get a list of all the default owners.
     */
    @Test
    public void findAllDefaultOwners() {
        logger.info("Find All Default Owners");

        List<Owner> defaultOwners = ownerRepository.findAllDefaultOwners();

        assertEquals(defaultOwners.size(), NUMBER_OF_DEFAULT_OWNERS);
        assertEquals(defaultOwners.get(0).getPrimaryKey(), OWNER_2_KEY);
    }

    /**
     * Delete the owner test record for the passed primary key.
     *
     * @param deleteKey - the primary key of the owner record to delete.
     */
    private void deleteTestRecord(String deleteKey) {
        Owner owner = ownerRepository.findOne(deleteKey);

        if (owner != null) {
            ownerRepository.delete(owner);
        }
    }
}
