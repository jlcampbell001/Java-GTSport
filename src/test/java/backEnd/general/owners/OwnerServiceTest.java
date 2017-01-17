package backEnd.general.owners;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import backEnd.general.GTSportConfig;

@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class OwnerServiceTest extends AbstractTestNGSpringContextTests {
	private static final String OWNER_1_KEY = "XXX900000001";
	private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
	private static final boolean OWNER_1_DEFAULT = false;

	private static final String OWNER_2_KEY = "XXX900000002";
	private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
	private static final boolean OWNER_2_DEFAULT = true;

	private static final String OWNER_3_KEY = "XXX900000003";
	private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
	private static final boolean OWNER_3_DEFAULT = false;

	private static final String OWNER_4_NAME = "XXX_Test_Owner_4_XXX";
	private static final boolean OWNER_4_DEFAULT = false;
	private static final String OWNER_4_NEW_NAME = "XXX_New_Owner_4_XXX";

	private static final String BAD_KEY = "ZXZ_!_001";
	private static final String BAD_NAME = "JFUEKNCK DFJI";

	private static final String OWNER_4_KEY_EXPECTED_AFTER_RESET = "OWN900000004";

	private static final int NUMBER_OF_LIST_RECORDS_EXPECTED = 4;

	private String owner4Key = "";

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private OwnerRepository ownerRepository;

	/**
	 * Setup records in the owner table for testing.
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
	 * Clean up the owner table when done testing.
	 */
	@AfterClass
	@Rollback(false)
	public void afterClass() {
		logger.info("After Class");
		
		// delete the test records.
		deleteTestRecord(OWNER_1_KEY);
		deleteTestRecord(OWNER_2_KEY);
		deleteTestRecord(OWNER_3_KEY);
		deleteTestRecord(owner4Key);
		ownerService.resetKeys();
	}

	/**
	 * Save a new owner record.
	 * 
	 * @throws OwnerException
	 */
	@Test
	public void saveNewOwner() throws OwnerException {
		logger.info("Save New Owner");

		OwnerJson owner4 = new OwnerJson();
		owner4.setOwnerName(OWNER_4_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);

		owner4Key = owner4.getPrimaryKey();		
	}

	/**
	 * Update a owner record with a change.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "saveNewOwner" })
	public void updateOwner() throws OwnerException {
		logger.info("Update Owner");

		OwnerJson owner4 = new OwnerJson();
		owner4.setPrimaryKey(owner4Key);
		owner4.setOwnerName(OWNER_4_NEW_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);
	}

	/**
	 * Delete an owner record.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "updateOwner" })
	public void deleteOwner() throws OwnerException {
		logger.info("Delete Owner: " + owner4Key);

		ownerService.deleteOwner(owner4Key);
	}

	/**
	 * Reset the primary key value.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "deleteOwner" })
	public void resetKey() throws OwnerException {
		logger.info("Reset Keys");

		ownerService.resetKeys();

		OwnerJson owner4 = new OwnerJson();
		owner4.setOwnerName(OWNER_4_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);

		owner4Key = owner4.getPrimaryKey();

		assertEquals(OWNER_4_KEY_EXPECTED_AFTER_RESET, owner4Key);
	}

	/**
	 * Get an owner record by the primary key.
	 * 
	 * @throws OwnerException
	 */
	@Test
	public void getOwnerJsonByKey() throws OwnerException {
		logger.info("Get Owner By Key: " + OWNER_3_KEY);
		
		OwnerJson ownerJson = ownerService.getOwnerJsonByKey(OWNER_3_KEY);
		assertEquals(ownerJson.getOwnerName(), OWNER_3_NAME);
	}

	/**
	 * Test the error of trying to get an owner record with a non-existing primary key.
	 * 
	 * @throws OwnerException
	 */
	@Test(expectedExceptions = OwnerException.class)
	public void getOwnerJsonByKeyBadKey() throws OwnerException {
		logger.info("Get Owner By Key Bad Key: " + BAD_KEY);
		
		String expectedError = OwnerException.OWNER_NOT_FOUND_KEY_ERROR + BAD_KEY;

		try {
			ownerService.getOwnerJsonByKey(BAD_KEY);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	/**
	 * Get an owner record using the owner name.
	 * 
	 * @throws OwnerException
	 */
	@Test
	public void getOwnerJsonByName() throws OwnerException {
		logger.info("Get Owner By Name: " + OWNER_2_NAME);
		
		OwnerJson ownerJson = ownerService.getOwnerJsonByName(OWNER_2_NAME);

		assertEquals(ownerJson.getPrimaryKey(), OWNER_2_KEY);
	}

	/**
	 * Test the error of trying to get an owner record with a non-existing name.
	 * 
	 * @throws OwnerException
	 */
	@Test(expectedExceptions = OwnerException.class)
	public void getOwnerJsonByNameBadName() throws OwnerException {
		logger.info("Get Owner By Name Bad Name: " + BAD_NAME);
		
		String expectedError = OwnerException.OWNER_NOT_FOUND_NAME_ERROR + BAD_NAME;

		try {
			ownerService.getOwnerJsonByName(BAD_NAME);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	/**
	 * Get the list of owner records.
	 */
	@Test(dependsOnMethods = { "resetKey" })
	public void getOwnersList() {
		logger.info("Get Owner List");
		
		List<OwnerJson> owners = ownerService.getOwnersList();

		assertEquals(NUMBER_OF_LIST_RECORDS_EXPECTED, owners.size());
		assertEquals(OWNER_1_KEY, owners.get(0).getPrimaryKey());
	}

	/**
	 * Get the set default owner.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "resetKey" })
	public void getDefaultOwner() throws OwnerException {
		logger.info("Get Default Owner");
		
		OwnerJson ownerJson = ownerService.getDefaultOwnerJson();

		assertEquals(OWNER_2_KEY, ownerJson.getPrimaryKey());

	}

	/**
	 * Get the default owner where there is no default owner set and the system default owner is a record.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "getDefaultOwner" })
	public void getDefaultOwnerMultipleOwnersNoneAreDefaultDefaultOwnerExits() throws OwnerException {
		logger.info("Get Default Owner Multiple Owners None Are Set As Default And DEFULAT Owner Exists");
		
		deleteDefaultOwner();

		// Clear the default owners.
		clearDefaultOwners();

		// Create the DEFAULT owner.
		OwnerJson defaultOwner = new OwnerJson();

		defaultOwner.setOwnerName(OwnerService.DEFAULT_OWNER_NAME);
		defaultOwner.setDefaultOwner(false);

		ownerService.saveOwner(defaultOwner);

		OwnerJson ownerJson = ownerService.getDefaultOwnerJson();

		assertEquals(OwnerService.DEFAULT_OWNER_NAME, ownerJson.getOwnerName());

		deleteDefaultOwner();
	}

	/**
	 * Get the default owner where there is no default owner set and the system default owner is not a record.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "getDefaultOwner" })
	public void getDefaultOwnerMultipleOwnersNoneAreDefaultDefaultOwnerNotExits() throws OwnerException {
		logger.info("Get Default Owner Multiple Owners None Are Set As Default And DEFAULT Owner Dose Not Exist");
		
		deleteDefaultOwner();

		// Clear the default owners.
		clearDefaultOwners();

		OwnerJson ownerJson = ownerService.getDefaultOwnerJson();

		assertEquals(OwnerService.DEFAULT_OWNER_NAME, ownerJson.getOwnerName());

		deleteDefaultOwner();
	}

	/**
	 * Change the set default owner.
	 * 
	 * @throws OwnerException
	 */
	@Test(dependsOnMethods = { "getDefaultOwner" })
	public void saveNewDefaultOwner() throws OwnerException {
		logger.info("Save A New Default Owner");
		
		// Clear the default owners.
		clearDefaultOwners();

		// Set owner 3 as the default owner.
		Owner owner3 = new Owner(OWNER_3_KEY, OWNER_3_NAME, true);
		ownerRepository.saveAndFlush(owner3);

		// Change the default owner to owner 2.
		OwnerJson owner2 = new OwnerJson();
		owner2.setPrimaryKey(OWNER_2_KEY);
		owner2.setOwnerName(OWNER_2_NAME);
		owner2.setDefaultOwner(true);

		ownerService.saveOwner(owner2);

		OwnerJson defaultOwner = ownerService.getDefaultOwnerJson();

		assertEquals(owner2.getPrimaryKey(), defaultOwner.getPrimaryKey());

	}

	/**
	 * Delete the owner test record if it exists.
	 * 
	 * @param deleteKey - the owner primary key for the record to delete.
	 */
	private void deleteTestRecord(String deleteKey) {
		Owner owner = ownerRepository.findOne(deleteKey);

		if (owner != null) {
			ownerRepository.delete(owner);
		}
	}

	/**
	 * Delete the system default owner record.
	 */
	private void deleteDefaultOwner() {
		// Delete the DEFAULT owner.
		try {
			OwnerJson defaultOwner = ownerService.getOwnerJsonByName(OwnerService.DEFAULT_OWNER_NAME);

			deleteTestRecord(defaultOwner.getPrimaryKey());

		} catch (OwnerException oe) {
			// Swallow the exception. The DEFAULT owner was not found which is
			// OK.
		}
	}

	/**
	 * Set all of the owner records default settings to false.
	 *  
	 * @throws OwnerException
	 */
	private void clearDefaultOwners() throws OwnerException {
		List<OwnerJson> owners = ownerService.getOwnersList();

		for (OwnerJson ownerJson : owners) {
			if (ownerJson.isDefaultOwner()) {
				ownerJson.setDefaultOwner(false);

				ownerService.saveOwner(ownerJson);
			}
		}
	}
}
