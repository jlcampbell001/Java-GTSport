package backEnd.general.owners;

import static org.testng.AssertJUnit.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import backEnd.general.GTSportDataTesting;

/**
 * Tests for the owner validate service.
 *
 * @author jonathan
 */
public class OwnerValidateTest extends GTSportDataTesting {

    private static final String BAD_KEY = "ZXZ_!_001";

    @Autowired
    private OwnerValidate ownerValidate;

    /**
     * Setup records for testing.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        ownerRepository.saveAndFlush(OWNER1);
        ownerRepository.saveAndFlush(OWNER2);
        ownerRepository.saveAndFlush(OWNER3);
        
        regionRepository.saveAndFlush(REGION3);

        countryRepository.saveAndFlush(COUNTRY1);

        manufacturerRepository.saveAndFlush(MANUFACTURER1);

        carRepository.saveAndFlush(CAR3);

        ownerCarRepository.saveAndFlush(OWNERCAR3);
    }

    /**
     * Delete the test records that where created.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
       deleteOwnerCarTestRecord(OWNERCAR3.getPrimaryKey());
 
       deleteOwnerTestRecord(OWNER1.getPrimaryKey());
        deleteOwnerTestRecord(OWNER2.getPrimaryKey());
        deleteOwnerTestRecord(OWNER3.getPrimaryKey());

        deleteCarTestRecord(CAR3.getPrimaryKey());

        deleteManufacturerTestRecord(MANUFACTURER1.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());

        deleteRegionTestRecord(REGION3.getPrimaryKey());
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
        owner.setPrimaryKey(OWNER1.getPrimaryKey());
        owner.setOwnerName(OWNER1.getOwnerName());
        owner.setDefaultOwner(OWNER1.getDefaultOwner());

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
            misingOwnerName.setDefaultOwner(OWNER1.getDefaultOwner());

            ownerValidate.validateOwnerSave(misingOwnerName);

        } catch (OwnerException oe) {
            assertEquals(expectedError, oe.getMessage());
            throw oe;
        }
    }

    /**
     * Test the error of an owner object where the name already exists as
     * another owner record.
     *
     * @throws OwnerException
     */
    @Test(expectedExceptions = OwnerException.class)
    public void validateOwnerSaveNameExits() throws OwnerException {
        logger.info("Validate Owner Save Name Exists: " + OWNER1.getOwnerName());

        String expectedError = OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + OWNER1.getOwnerName();

        try {
            OwnerJson badOwnerName = new OwnerJson();
            badOwnerName.setOwnerName(OWNER1.getOwnerName());
            badOwnerName.setDefaultOwner(OWNER1.getDefaultOwner());

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

        ownerValidate.validateOwnerDelete(OWNER2.getPrimaryKey());
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
}
