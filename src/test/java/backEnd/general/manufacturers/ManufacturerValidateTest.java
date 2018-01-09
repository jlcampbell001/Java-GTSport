package backEnd.general.manufacturers;

import backEnd.general.manufacturers.ManufacturerJson;
import backEnd.general.manufacturers.ManufacturerException;
import backEnd.general.manufacturers.ManufacturerValidate;
import backEnd.general.GTSportDataTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the manufacturer validate.
 *
 * @author jonathan
 */
public class ManufacturerValidateTest extends GTSportDataTesting {

    private static final String BAD_COUNTRY_KEY = "C!X999999999";
    private static final String BAD_MANUFACTURER_KEY = "D!X999999999";

    @Autowired
    private ManufacturerValidate manufacturerValidate;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);

        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);
        countryRepository.saveAndFlush(COUNTRY3);

        // add the manufacturers to work with.
        manufacturerRepository.saveAndFlush(MANUFACTURER1);
        manufacturerRepository.saveAndFlush(MANUFACTURER2);
        manufacturerRepository.saveAndFlush(MANUFACTURER3);

        // add car record.
        carRepository.saveAndFlush(CAR2);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteCarTestRecord(CAR2.getPrimaryKey());

        deleteManufacturerTestRecord(MANUFACTURER1.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER2.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
        deleteRegionTestRecord(REGION3.getPrimaryKey());
    }

    /**
     * Test validating a manufacturer save that is good to save.
     *
     * @throws ManufacturerException
     */
    @Test
    public void validateManufacturerSave() throws ManufacturerException {
        logger.info("Validate Manufacturer Save");

        ManufacturerJson manufacturerJson = new ManufacturerJson();

        manufacturerJson.setPrimaryKey(MANUFACTURER1.getPrimaryKey());
        manufacturerJson.setName(MANUFACTURER1.getName());
        manufacturerJson.setCountryKey(MANUFACTURER1.getCountryKey());

        manufacturerValidate.validateManufacturerSave(manufacturerJson);
    }

    /**
     * Test the validating the manufacturer on a save with the manufacturer name not filled.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerSaveNameNotSet() throws ManufacturerException {
        logger.info("Validate Manufacturer Save Name Not Set");

        String expectedError = ManufacturerException.NAME_NOT_SET;

        try {
            ManufacturerJson manufacturerJson = new ManufacturerJson();
            manufacturerJson.setCountryKey(COUNTRY1.getPrimaryKey());

            manufacturerValidate.validateManufacturerSave(manufacturerJson);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test validating the manufacturer save where the country key is not filled.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerSaveCountryKeyNotSet() throws ManufacturerException {
        logger.info("Validate Manufacturer Save Country Key Not Set");

        String expectedError = ManufacturerException.COUNTRY_KEY_NOT_SET;

        try {
            ManufacturerJson manufacturerJson = new ManufacturerJson();
            manufacturerJson.setPrimaryKey(MANUFACTURER1.getPrimaryKey());
            manufacturerJson.setName(MANUFACTURER1.getName());

            manufacturerValidate.validateManufacturerSave(manufacturerJson);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test validating the manufacturer save where the name already exists on another
     * manufacturer.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerSaveNameAlreadyExists() throws ManufacturerException {
        logger.info("Validate Manufacturer Save Name Already Exists: " + MANUFACTURER1.getName());

        String expectedError = ManufacturerException.NAME_ALREADY_EXISTS;

        try {
            ManufacturerJson manufacturerJson = new ManufacturerJson();
            manufacturerJson.setPrimaryKey("");
            manufacturerJson.setName(MANUFACTURER1.getName());
            manufacturerJson.setCountryKey(MANUFACTURER1.getCountryKey());

            manufacturerValidate.validateManufacturerSave(manufacturerJson);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test the validate manufacturer save where the country key does not exist.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerSaveBadCountryKey() throws ManufacturerException {
        logger.info("Validate Manufacturer Save Bad Country Key: " + BAD_COUNTRY_KEY);

        String expectedError = ManufacturerException.COUNTRY_KEY_DOES_NOT_EXIST + BAD_COUNTRY_KEY;

        try {
            ManufacturerJson manufacturerJson = new ManufacturerJson();
            manufacturerJson.setPrimaryKey(MANUFACTURER1.getPrimaryKey());
            manufacturerJson.setName(MANUFACTURER1.getName());
            manufacturerJson.setCountryKey(BAD_COUNTRY_KEY);

            manufacturerValidate.validateManufacturerSave(manufacturerJson);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test the validate deleting the manufacturer successfully.
     *
     * @throws ManufacturerException
     */
    @Test
    public void validateManufacturerDelete() throws ManufacturerException {
        logger.info("Validate Manufacturer Delete");

        manufacturerValidate.validateManufacturerDelete(MANUFACTURER3.getPrimaryKey());
    }

    /**
     * Test the validate deleting the manufacturer and passing in a non-existing
     * primary key.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerDeleteBadDealerKey() throws ManufacturerException {
        logger.info("Validate Manufacturer Delete Bad Dealer Key");

        String expectedException = ManufacturerException.MANUFACTURER_KEY_DOES_NOT_EXIST_DELETE + BAD_MANUFACTURER_KEY;

        try {
            manufacturerValidate.validateManufacturerDelete(BAD_MANUFACTURER_KEY);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedException);
            throw de;
        }
    }

    /**
     * Test manufacturer delete validate where the manufacturer key is still in use.
     *
     * @throws ManufacturerException should find an error that the dealer key is in
     * use
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void validateManufacturerDeleteInUse() throws ManufacturerException {
        logger.info("Validate Manufacturer Delete In Use");

        String expectedException = ManufacturerException.MANUFACTURER_IS_IN_USE;

        try {
            manufacturerValidate.validateManufacturerDelete(MANUFACTURER2.getPrimaryKey());
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedException);
            throw de;
        }

    }
}
