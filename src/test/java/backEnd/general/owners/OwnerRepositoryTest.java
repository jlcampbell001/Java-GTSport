package backEnd.general.owners;

import static org.testng.Assert.assertEquals;

import java.util.List;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;

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
public class OwnerRepositoryTest extends GTSportDataTesting {
    
    private static final String MAX_OWN_KEY = OWNER3.getPrimaryKey();  

    private static final String BAD_OWNER_NAME = "XXX_";

    private static final int NUMBER_OF_DEFAULT_OWNERS = 1;

    /**
     * Setup owner records for testing.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 owners to work with.
        ownerRepository.saveAndFlush(OWNER1);
        ownerRepository.saveAndFlush(OWNER2);
        ownerRepository.saveAndFlush(OWNER3);
    }

    /**
     * Delete the owner records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteOwnerTestRecord(OWNER1.getPrimaryKey());
        deleteOwnerTestRecord(OWNER2.getPrimaryKey());
        deleteOwnerTestRecord(OWNER3.getPrimaryKey());
    }

    /**
     * Get an owner record by the owner name.
     */
    @Test
    public void findOwnerByName() {
        logger.info("Find Owner By Name: " + OWNER3.getOwnerName());

        Owner foundOwner = ownerRepository.findByName(OWNER3.getOwnerName());

        assertEquals(foundOwner.getPrimaryKey(), OWNER3.getPrimaryKey());
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

        assertEquals(foundOwner.getPrimaryKey(), OWNER2.getPrimaryKey());
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
        assertEquals(defaultOwners.get(0).getPrimaryKey(), OWNER2.getPrimaryKey());
    }
}
