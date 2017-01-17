package backEnd.general.owners;

import static org.testng.AssertJUnit.assertEquals;

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
public class OwnerValidateTest extends AbstractTestNGSpringContextTests {
	private static final String OWNER_1_KEY = "XXX900000001";
	private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
	private static final boolean OWNER_1_DEFAULT = false;

	private static final String OWNER_2_KEY = "XXX900000002";
	private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
	private static final boolean OWNER_2_DEFAULT = true;

	private static final String OWNER_3_KEY = "XXX900000003";
	private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
	private static final boolean OWNER_3_DEFAULT = false;

	private static final String BAD_KEY = "ZXZ_!_001";

	@Autowired
	private OwnerValidate ownerValidate;

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
	 * Delete the test owner records that where created.
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
	 * Validate a good owner to save.
	 * 
	 * @throws OwnerException
	 */
	@Test()
	public void validateOwnerSave() throws OwnerException {
		logger.info("Validate Owner Save");
		
		OwnerJson owner = new OwnerJson();
		owner.setPrimaryKey(OWNER_1_KEY);
		owner.setOwnerName(OWNER_1_NAME);
		owner.setDefaultOwner(OWNER_1_DEFAULT);

		ownerValidate.validateOwnerSave(owner);
	}

	/**
	 * Test the error of an owner object missing the owner name.
	 * 
	 * @throws OwnerException
	 */
	@Test(expectedExceptions = OwnerException.class)
	public void validateOwnerSaveNameNotSet() throws OwnerException {
		logger.info("Validate Owner Save Name Not Set");
		
		String expectedError = OwnerException.OWNER_NAME_NOT_SET;

		try {
			OwnerJson misingOwnerName = new OwnerJson();
			misingOwnerName.setOwnerName("");
			misingOwnerName.setDefaultOwner(OWNER_1_DEFAULT);

			ownerValidate.validateOwnerSave(misingOwnerName);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	/**
	 * Test the error of an owner object where the name already exists as another owner record.
	 * 
	 * @throws OwnerException
	 */
	@Test(expectedExceptions = OwnerException.class)
	public void validateOwnerSaveNameExits() throws OwnerException {
		logger.info("Validate Owner Save Name Exists: " + OWNER_1_NAME);
		
		String expectedError = OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + OWNER_1_NAME;

		try {
			OwnerJson badOwnerName = new OwnerJson();
			badOwnerName.setOwnerName(OWNER_1_NAME);
			badOwnerName.setDefaultOwner(OWNER_1_DEFAULT);

			ownerValidate.validateOwnerSave(badOwnerName);

		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	/**
	 * Validate the delete of an owner.
	 * 
	 * @throws OwnerException
	 */
	@Test
	public void validateOwnerDelete() throws OwnerException {
		logger.info("Validate Owner Delete");
		
		ownerValidate.validateOwnerDelete(OWNER_2_KEY);
	}

	/**
	 * Test the error of a non-existing primary key to delete.
	 * 
	 * @throws OwnerException
	 */
	@Test(expectedExceptions = OwnerException.class)
	public void validateOwnerDeleteBadKey() throws OwnerException {
		logger.info("Validate Onwer Delete Bad Key: " + BAD_KEY);
		
		String expectedError = OwnerException.OWNER_NOT_FOUND_KEY_DELETE_ERROR + BAD_KEY;

		try {
			ownerValidate.validateOwnerDelete(BAD_KEY);
		} catch (OwnerException oe) {
			assertEquals(expectedError, oe.getMessage());
			throw oe;
		}
	}

	/**
	 * Delete an owner test record for the passed primary key.
	 * 
	 * @param deleteKey - the primary key of the owner to delete.
	 */
	private void deleteTestRecord(String deleteKey) {
		Owner owner = ownerRepository.findOne(deleteKey);

		if (owner != null) {
			ownerRepository.delete(owner);
		}
	}
}
