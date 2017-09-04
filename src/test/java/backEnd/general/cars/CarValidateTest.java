package backEnd.general.cars;

import backEnd.general.GTSportDataTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Testing the car validate service.
 *
 * @author jonathan
 */
public class CarValidateTest extends GTSportDataTesting {

    private static final String CAR_4_KEY = "XXX900000004";
    private static final String CAR_4_NAME = "Esperante GTR-1 Race Car '98";
    private static final String CAR_4_DEALER_KEY = DEALER1.getPrimaryKey();

    private static final DriveTrain BAD_DRIVETRAIN = null;
    private static final Aspiration BAD_ASPIRATION = null;
    private static final String BAD_DEALER_KEY = "!!!XXX!!!";
    private static final String BAD_CAR_KEY = "!!!XXX!!!";

    @Autowired
    private CarValidate carValidate;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);

        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);

        dealerRepository.saveAndFlush(DEALER1);
        dealerRepository.saveAndFlush(DEALER2);
        dealerRepository.saveAndFlush(DEALER3);

        carRepository.saveAndFlush(CAR1);
        carRepository.saveAndFlush(CAR2);
        carRepository.saveAndFlush(CAR3);

        ownerRepository.saveAndFlush(OWNER1);
        ownerCarRepository.saveAndFlush(OWNERCAR3);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteOwnerCarTestRecord(OWNERCAR3.getPrimaryKey());

        deleteOwnerTestRecord(OWNER1.getPrimaryKey());

        deleteCarTestRecord(CAR1.getPrimaryKey());
        deleteCarTestRecord(CAR2.getPrimaryKey());
        deleteCarTestRecord(CAR3.getPrimaryKey());

        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());
        deleteDealerTestRecord(DEALER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    /**
     * Test to validate a car save.
     *
     * @throws CarException should find no errors
     */
    @Test(groups = "goodSave")
    public void validateCarSave() throws CarException {
        logger.info("Validate Car Save");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR1.getPrimaryKey());
        carJson.setDealerKey(DEALER1.getPrimaryKey());
        carJson.setName(CAR1.getName());
        carJson.setDriveTrain(CAR1.getDriveTrain());
        carJson.setAspiration(CAR1.getAspiration());

        carValidate.validateCarSave(carJson);
    }

    /**
     * Test car save validate with a empty drivetrain.
     *
     * @throws CarException should find no errors
     */
    @Test(groups = "goodSave")
    public void validateCarSaveEmptyDriveTrain() throws CarException {
        logger.info("Validate Car Save - Empty Drivetrain");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR1.getPrimaryKey());
        carJson.setDealerKey(DEALER1.getPrimaryKey());
        carJson.setName(CAR1.getName());
        carJson.setDriveTrain(DriveTrain.EMPTY);

        carValidate.validateCarSave(carJson);
    }

    /**
     * Test car save validate with an empty aspiration.
     *
     * @throws CarException should find no errors
     */
    @Test(groups = "goodSave")
    public void validateCarSaveEmptyAspiration() throws CarException {
        logger.info("Validate Car Save - Empty Aspiration");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR1.getPrimaryKey());
        carJson.setDealerKey(DEALER1.getPrimaryKey());
        carJson.setName(CAR1.getName());
        carJson.setDriveTrain(CAR1.getDriveTrain());
        carJson.setAspiration(Aspiration.EMPTY);

        carValidate.validateCarSave(carJson);
    }

    /**
     * test car save validate with the name not filled in.
     *
     * @throws CarException should find an error with the name not set
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveNameNotSet() throws CarException {
        logger.info("Validate Car Save Name Not Set");

        String expectedError = CarException.NAME_NOT_SET;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR1.getPrimaryKey());
            carJson.setDealerKey(DEALER1.getPrimaryKey());
            carJson.setName("");

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car save validate with the dealer key not filled.
     *
     * @throws CarException should find an error with the dealer key not filled
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveDealerKeyNotSet() throws CarException {
        logger.info("Validate Car Save Dealer Key Not Set");

        String expectedError = CarException.DEALER_KEY_NOT_SET;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR1.getPrimaryKey());
            carJson.setDealerKey("");
            carJson.setName(CAR1.getName());

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car save validate with the a bad drivetrain.
     *
     * @throws CarException should find an error with the bad drivetrain
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveDrivetrainNotValid() throws CarException {
        logger.info("Validate Car Save Drivetrain Not Valid");

        String expectedError = CarException.DRIVETRAIN_NOT_VALID;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR1.getPrimaryKey());
            carJson.setDealerKey(CAR1.getDealerKey());
            carJson.setName(CAR1.getName());
            carJson.setDriveTrain(BAD_DRIVETRAIN);

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car save validate with a bad aspiration.
     *
     * @throws CarException should find an error with the bad aspiration
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveAspirationNotValid() throws CarException {
        logger.info("Validate Car Save Aspiration Not Valid");

        String expectedError = CarException.ASPIRATION_NOT_VALID;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR1.getPrimaryKey());
            carJson.setDealerKey(CAR1.getDealerKey());
            carJson.setName(CAR1.getName());
            carJson.setDriveTrain(CAR1.getDriveTrain());
            carJson.setAspiration(BAD_ASPIRATION);

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car save validate with the car name already existing.
     *
     * @throws CarException should find an error with the name already existing
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveNameAlreadyExists() throws CarException {
        logger.info("Validate Car Save Name Already Exists");

        String expectedError = CarException.NAME_ALREADY_EXISTS;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR_4_KEY);
            carJson.setDealerKey(CAR_4_DEALER_KEY);
            carJson.setName(CAR_4_NAME);

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car save validate with a bad dealer key.
     *
     * @throws CarException should find an error with the dealer key not found
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveDealerKeyNotFound() throws CarException {
        logger.info("Validate Car Save Dealer Key Not Found: " + BAD_DEALER_KEY);

        String expectedError = CarException.DEALER_KEY_DOES_NOT_EXIST + BAD_DEALER_KEY;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR1.getPrimaryKey());
            carJson.setDealerKey(BAD_DEALER_KEY);
            carJson.setName(CAR1.getName());
            carJson.setDriveTrain(CAR1.getDriveTrain());
            carJson.setAspiration(CAR1.getAspiration());

            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test car delete validate.
     *
     * @throws CarException should find no errors
     */
    @Test(groups = "goodDelete")
    public void validateCarDelete() throws CarException {
        logger.info("Validate Car Delete");

        carValidate.validateCarDelete(CAR1.getPrimaryKey());
    }

    /**
     * Test car delete validate with a bad car primary key.
     *
     * @throws CarException should find an error with not finding a car key
     */
    @Test(dependsOnGroups = "goodDelete", expectedExceptions = CarException.class)
    public void validateCarDeleteBadCarKey() throws CarException {
        logger.info("Validate Car Delete Bad Car Key: " + BAD_CAR_KEY);

        String expectedError = CarException.CAR_KEY_NOT_FOUND_TO_DELETE + BAD_CAR_KEY;

        try {
            carValidate.validateCarDelete(BAD_CAR_KEY);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Testing the car delete where the car is still in use.
     *
     * @throws CarException should find an error that the car is in use
     */
    @Test(dependsOnGroups = "goodDelete", expectedExceptions = CarException.class)
    public void validateCarDeleteCarKeyInUse() throws CarException {
        logger.info("Validate Car Delete Car Key In Use");

        String expectedError = CarException.CAR_IS_IN_USE;

        try {
            carValidate.validateCarDelete(CAR3.getPrimaryKey());
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
}
