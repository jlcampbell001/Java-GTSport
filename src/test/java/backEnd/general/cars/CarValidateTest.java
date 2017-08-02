package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
import backEnd.general.dealers.DealersForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author jonathan
 */
public class CarValidateTest extends GTSportDataTesting {

    private static final String CAR_4_KEY = "XXX900000004";
    private static final String CAR_4_NAME = "Esperante GTR-1 Race Car '98";
    private static final String CAR_4_DEALER_KEY = DEALER1.getPrimaryKey();
    
    private static final String BAD_DRIVETRAIN = "XX";
    private static final String BAD_ASPIRATION = "XX";
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

        // add the 3 cars to work with.
        carRepository.saveAndFlush(CAR1);
        carRepository.saveAndFlush(CAR2);
        carRepository.saveAndFlush(CAR3);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteCarTestRecord(CAR1.getPrimaryKey());
        deleteCarTestRecord(CAR2.getPrimaryKey());
        deleteCarTestRecord(CAR3.getPrimaryKey());

        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

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

    @Test(groups = "goodSave")
    public void validateCarSaveEmptyDriveTrain() throws CarException {
        logger.info("Validate Car Save - Empty Drivetrain");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR1.getPrimaryKey());
        carJson.setDealerKey(DEALER1.getPrimaryKey());
        carJson.setName(CAR1.getName());
        carJson.setDriveTrain("");

        carValidate.validateCarSave(carJson);
    }

    @Test(groups = "goodSave")
    public void validateCarSaveEmptyAspiration() throws CarException {
        logger.info("Validate Car Save - Empty Aspiration");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR1.getPrimaryKey());
        carJson.setDealerKey(DEALER1.getPrimaryKey());
        carJson.setName(CAR1.getName());
        carJson.setDriveTrain(CAR1.getDriveTrain());
        carJson.setAspiration("");

        carValidate.validateCarSave(carJson);
    }

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
    
    @Test(groups = "goodDelete")
    public void validateCarDelete() throws CarException {
        logger.info("Validate Car Delete");
        
        carValidate.validateCarDelete(CAR1.getPrimaryKey());
    }
    
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
}
