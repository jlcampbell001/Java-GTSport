package backEnd.general.owners;

import static org.testng.AssertJUnit.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import backEnd.general.GTSportConfig;

@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class OwnerServiceTest extends AbstractTestNGSpringContextTests {
	private static final String OWNER_1_KEY = "XXX900000001";
	private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
	private static final Boolean OWNER_1_DEFAULT = false;

	private static final String OWNER_2_KEY = "XXX900000002";
	private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
	private static final Boolean OWNER_2_DEFAULT = true;

	private static final String OWNER_3_KEY = "XXX900000003";
	private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
	private static final Boolean OWNER_3_DEFAULT = false;

	private static final String OWNER_4_NAME = "XXX_Test_Owner_4_XXX";
	private static final Boolean OWNER_4_DEFAULT = false;
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

	@BeforeClass
	@Rollback(false)
	public void beforeClass() {
		// add the 3 owners to work with.
		Owner owner1 = new Owner(OWNER_1_KEY, OWNER_1_NAME, OWNER_1_DEFAULT);
		ownerRepository.saveAndFlush(owner1);

		Owner owner2 = new Owner(OWNER_2_KEY, OWNER_2_NAME, OWNER_2_DEFAULT);
		ownerRepository.saveAndFlush(owner2);

		Owner owner3 = new Owner(OWNER_3_KEY, OWNER_3_NAME, OWNER_3_DEFAULT);
		ownerRepository.saveAndFlush(owner3);
	}

	@AfterClass
	@Rollback(false)
	public void afterClass() {
		// delete the test records.
		deleteTestRecord(OWNER_1_KEY);
		deleteTestRecord(OWNER_2_KEY);
		deleteTestRecord(OWNER_3_KEY);
		deleteTestRecord(owner4Key);
		ownerService.resetKeys();
	}

	@Test
	public void saveNewOwner() throws OwnerException {
		OwnerJson owner4 = new OwnerJson();
		owner4.setOwnerName(OWNER_4_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);

		owner4Key = owner4.getPrimaryKey();
	}

	@Test(dependsOnMethods = { "saveNewOwner" })
	public void updateOwner() throws OwnerException {
		OwnerJson owner4 = new OwnerJson();
		owner4.setPrimaryKey(owner4Key);
		owner4.setOwnerName(OWNER_4_NEW_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);
	}

	@Test(expectedExceptions = OwnerException.class)
	public void saveNewOwnerNameExists() throws OwnerException {
		String expectedError = OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + OWNER_1_NAME;

		try {
			OwnerJson badownerwner = new OwnerJson();
			badownerwner.setOwnerName(OWNER_1_NAME);
			badownerwner.setDefaultOwner(OWNER_1_DEFAULT);

			ownerService.saveOwner(badownerwner);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test(expectedExceptions = OwnerException.class)
	public void updateOwnerNameExists() throws OwnerException {
		String expectedError = OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + OWNER_1_NAME;

		try {
			OwnerJson badownerwner = new OwnerJson();
			badownerwner.setPrimaryKey(OWNER_2_KEY);
			badownerwner.setOwnerName(OWNER_1_NAME);
			badownerwner.setDefaultOwner(OWNER_1_DEFAULT);

			ownerService.saveOwner(badownerwner);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test(expectedExceptions = OwnerException.class)
	public void saveNewOwnerNameNotSet() throws OwnerException {
		String expectedError = OwnerException.OWNER_NAME_NOT_SET;

		try {
			OwnerJson badownerwner = new OwnerJson();
			badownerwner.setOwnerName("");
			badownerwner.setDefaultOwner(OWNER_1_DEFAULT);

			ownerService.saveOwner(badownerwner);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test(dependsOnMethods = { "updateOwner" })
	public void deleteOwner() throws OwnerException {
		ownerService.deleteOwner(owner4Key);
	}

	@Test(expectedExceptions = OwnerException.class)
	public void deleteOwnerBadKey() throws OwnerException {
		String expectedError = OwnerException.OWNER_NOT_FOUND_KEY_DELETE_ERROR + BAD_KEY;

		try {
			ownerService.deleteOwner(BAD_KEY);
		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test(dependsOnMethods = { "deleteOwner" })
	public void resetKey() throws OwnerException {
		ownerService.resetKeys();

		OwnerJson owner4 = new OwnerJson();
		owner4.setOwnerName(OWNER_4_NAME);
		owner4.setDefaultOwner(OWNER_4_DEFAULT);

		ownerService.saveOwner(owner4);

		owner4Key = owner4.getPrimaryKey();

		assertEquals(OWNER_4_KEY_EXPECTED_AFTER_RESET, owner4Key);
	}

	@Test
	public void getOwnerJsonByKey() throws OwnerException {
		OwnerJson ownerJson = ownerService.getOwnerJsonByKey(OWNER_3_KEY);

		assertEquals(ownerJson.getOwnerName(), OWNER_3_NAME);
	}

	@Test(expectedExceptions = OwnerException.class)
	public void getOwnerJsonByKeyBadKey() throws OwnerException {
		String expectedError = OwnerException.OWNER_NOT_FOUND_KEY_ERROR + BAD_KEY;

		try {
			ownerService.getOwnerJsonByKey(BAD_KEY);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test
	public void getOwnerJsonByName() throws OwnerException {
		OwnerJson ownerJson = ownerService.getOwnerJsonByName(OWNER_2_NAME);

		assertEquals(ownerJson.getPrimaryKey(), OWNER_2_KEY);
	}

	@Test(expectedExceptions = OwnerException.class)
	public void getOwnerJsonByNameBadKey() throws OwnerException {
		String expectedError = OwnerException.OWNER_NOT_FOUND_NAME_ERROR + BAD_NAME;

		try {
			ownerService.getOwnerJsonByName(BAD_NAME);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	@Test(dependsOnMethods = { "resetKey" })
	public void getOwnersList() {
		List<OwnerJson> owners = ownerService.getOwnersList();

		assertEquals(OWNER_1_KEY, owners.get(0).getPrimaryKey());
		assertEquals(NUMBER_OF_LIST_RECORDS_EXPECTED, owners.size());
	}

	@Test(dependsOnMethods = { "resetKey" })
	public void getDefaultOwner() throws OwnerException {
		OwnerJson ownerJson = ownerService.getDefaultOwnerJson();

		assertEquals(OWNER_2_KEY, ownerJson.getPrimaryKey());

	}

	@Test(dependsOnMethods = { "getDefaultOwner" })
	public void getDefaultOwnerMultipleOwnersNoneAreDefaultDefaultOwnerExits() throws OwnerException {
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

	@Test(dependsOnMethods = { "getDefaultOwner" })
	public void getDefaultOwnerMultipleOwnersNoneAreDefaultDefaultOwnerNotExits() throws OwnerException {
		deleteDefaultOwner();
		
		// Clear the default owners.
		clearDefaultOwners();

		OwnerJson ownerJson = ownerService.getDefaultOwnerJson();

		assertEquals(OwnerService.DEFAULT_OWNER_NAME, ownerJson.getOwnerName());

		deleteDefaultOwner();
	}

	private void deleteTestRecord(String deleteKey) {
		Owner owner = ownerRepository.findOne(deleteKey);

		if (owner != null) {
			ownerRepository.delete(owner);
		}
	}

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
	
	private void clearDefaultOwners() throws OwnerException {
		List<OwnerJson> owners = ownerService.getOwnersList();

		for (OwnerJson ownerJson : owners) {
			if (ownerJson.getDefaultOwner()) {
				ownerJson.setDefaultOwner(false);

				ownerService.saveOwner(ownerJson);
			}
		}
	}
}
