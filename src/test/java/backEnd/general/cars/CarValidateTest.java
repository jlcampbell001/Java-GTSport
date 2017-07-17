package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
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
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CarValidateTest extends AbstractTestNGSpringContextTests {

    private static final String DEALER_1_KEY = "DEA900000001";
    private static final String DEALER_1_NAME = "CAR TEST DEALER";
    private static final String DEALER_1_COUNTRY_KEY = "COU900000001";

    private static final String CAR_1_KEY = "XXX900000001";
    private static final String CAR_1_NAME = "Corvette Coupe (C2) '63";
    private static final String CAR_1_DEALER_KEY = DEALER_1_KEY;
    private static final int CAR_1_YEAR = 1963;
    private static final int CAR_1_LEVEL = 4;
    private static final int CAR_1_POWER_POINTS = 430;
    private static final double CAR_1_PRICE = 84890.00;
    private static final String CAR_1_DISPLACEMENTCC = "5359";
    private static final int CAR_1_HORSE_POWER = 249;
    private static final String CAR_1_POWER_RPM = "4400";
    private static final double CAR_1_TORQUE_FTLB = 350.00;
    private static final String CAR_1_TORQUE_RPM = "2800";
    private static final String CAR_1_DRIVETRAIN = "FR";
    private static final String CAR_1_ASPIRATION = "NA";
    private static final double CAR_1_LENGTH = 175.10;
    private static final double CAR_1_WIDTH = 69.60;
    private static final double CAR_1_HEIGHT = 49.80;
    private static final double CAR_1_WEIGHT = 1370;

    private static final String CAR_2_KEY = "XXX900000002";
    private static final String CAR_2_NAME = "Firebird Trans AM '78";
    private static final String CAR_2_DEALER_KEY = "DEA9000000002";
    private static final int CAR_2_YEAR = 1978;
    private static final int CAR_2_LEVEL = 4;
    private static final int CAR_2_POWER_POINTS = 415;
    private static final double CAR_2_PRICE = 30000.00;
    private static final String CAR_2_DISPLACEMENTCC = "6558";
    private static final int CAR_2_HORSE_POWER = 219;
    private static final String CAR_2_POWER_RPM = "4000";
    private static final double CAR_2_TORQUE_FTLB = 321.00;
    private static final String CAR_2_TORQUE_RPM = "2800";
    private static final String CAR_2_DRIVETRAIN = "FR";
    private static final String CAR_2_ASPIRATION = "NA";
    private static final double CAR_2_LENGTH = 196.80;
    private static final double CAR_2_WIDTH = 73.40;
    private static final double CAR_2_HEIGHT = 49.50;
    private static final double CAR_2_WEIGHT = 1640;

    private static final String CAR_3_KEY = "XXX900000003";
    private static final String CAR_3_NAME = "Esperante GTR-1 Race Car '98";
    private static final String CAR_3_DEALER_KEY = DEALER_1_KEY;
    private static final int CAR_3_YEAR = 1998;
    private static final int CAR_3_LEVEL = 6;
    private static final int CAR_3_POWER_POINTS = 633;
    private static final double CAR_3_PRICE = 1700000.00;
    private static final String CAR_3_DISPLACEMENTCC = "";
    private static final int CAR_3_HORSE_POWER = 741;
    private static final String CAR_3_POWER_RPM = "6500";
    private static final double CAR_3_TORQUE_FTLB = 654.50;
    private static final String CAR_3_TORQUE_RPM = "3500";
    private static final String CAR_3_DRIVETRAIN = "FR";
    private static final String CAR_3_ASPIRATION = "NA";
    private static final double CAR_3_LENGTH = 175.60;
    private static final double CAR_3_WIDTH = 72.40;
    private static final double CAR_3_HEIGHT = 48.60;
    private static final double CAR_3_WEIGHT = 1150;
    
    private static final String CAR_4_KEY = "XXX900000004";
    private static final String CAR_4_NAME = "Esperante GTR-1 Race Car '98";
    private static final String CAR_4_DEALER_KEY = DEALER_1_KEY;
    
    private static final String BAD_DRIVETRAIN = "XX";
    private static final String BAD_ASPIRATION = "XX";
    private static final String BAD_DEALER_KEY = "!!!XXX!!!";
    private static final String BAD_CAR_KEY = "!!!XXX!!!";

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarValidate carValidate;
    
    @Autowired
    private DealerRepository dealerRepository;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 cars to work with.
        Car car1 = new Car(CAR_1_KEY, CAR_1_NAME, CAR_1_DEALER_KEY, CAR_1_YEAR,
                CAR_1_LEVEL, CAR_1_POWER_POINTS, CAR_1_PRICE, CAR_1_DISPLACEMENTCC,
                CAR_1_HORSE_POWER, CAR_1_POWER_RPM, CAR_1_TORQUE_FTLB, CAR_1_TORQUE_RPM,
                CAR_1_DRIVETRAIN, CAR_1_ASPIRATION, CAR_1_LENGTH, CAR_1_WIDTH,
                CAR_1_HEIGHT, CAR_1_WEIGHT);
        carRepository.saveAndFlush(car1);

        Car car2 = new Car(CAR_2_KEY, CAR_2_NAME, CAR_2_DEALER_KEY, CAR_2_YEAR,
                CAR_2_LEVEL, CAR_2_POWER_POINTS, CAR_2_PRICE, CAR_2_DISPLACEMENTCC,
                CAR_2_HORSE_POWER, CAR_2_POWER_RPM, CAR_2_TORQUE_FTLB, CAR_2_TORQUE_RPM,
                CAR_2_DRIVETRAIN, CAR_2_ASPIRATION, CAR_2_LENGTH, CAR_2_WIDTH,
                CAR_2_HEIGHT, CAR_2_WEIGHT);
        carRepository.saveAndFlush(car2);

        Car car3 = new Car(CAR_3_KEY, CAR_3_NAME, CAR_3_DEALER_KEY, CAR_3_YEAR,
                CAR_3_LEVEL, CAR_3_POWER_POINTS, CAR_3_PRICE, CAR_3_DISPLACEMENTCC,
                CAR_3_HORSE_POWER, CAR_3_POWER_RPM, CAR_3_TORQUE_FTLB, CAR_3_TORQUE_RPM,
                CAR_3_DRIVETRAIN, CAR_3_ASPIRATION, CAR_3_LENGTH, CAR_3_WIDTH,
                CAR_3_HEIGHT, CAR_3_WEIGHT);
        carRepository.saveAndFlush(car3);
        
        Dealer dealer = new Dealer(DEALER_1_KEY, DEALER_1_NAME, DEALER_1_COUNTRY_KEY);
        dealerRepository.saveAndFlush(dealer);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteTestRecord(CAR_1_KEY);
        deleteTestRecord(CAR_2_KEY);
        deleteTestRecord(CAR_3_KEY);
        
        deleteDealerTestRecord(DEALER_1_KEY);
    }

    @Test(groups = "goodSave")
    public void validateCarSave() throws CarException {
        logger.info("Validate Car Save");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR_1_KEY);
        carJson.setDealerKey(DEALER_1_KEY);
        carJson.setName(CAR_1_NAME);
        carJson.setDriveTrain(CAR_1_DRIVETRAIN);
        carJson.setApiration(CAR_1_ASPIRATION);

        carValidate.validateCarSave(carJson);
    }

    @Test(groups = "goodSave")
    public void validateCarSaveEmptyDriveTrain() throws CarException {
        logger.info("Validate Car Save - Empty Drivetrain");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR_1_KEY);
        carJson.setDealerKey(DEALER_1_KEY);
        carJson.setName(CAR_1_NAME);
        carJson.setDriveTrain("");

        carValidate.validateCarSave(carJson);
    }

    @Test(groups = "goodSave")
    public void validateCarSaveEmptyAspiration() throws CarException {
        logger.info("Validate Car Save - Empty Aspiration");

        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(CAR_1_KEY);
        carJson.setDealerKey(DEALER_1_KEY);
        carJson.setName(CAR_1_NAME);
        carJson.setDriveTrain(CAR_1_DRIVETRAIN);
        carJson.setApiration("");

        carValidate.validateCarSave(carJson);
    }

    @Test(dependsOnGroups = "goodSave", expectedExceptions = CarException.class)
    public void validateCarSaveNameNotSet() throws CarException {
        logger.info("Validate Car Save Name Not Set");

        String expectedError = CarException.NAME_NOT_SET;

        try {
            CarJson carJson = new CarJson();

            carJson.setPrimaryKey(CAR_1_KEY);
            carJson.setDealerKey(DEALER_1_KEY);
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

            carJson.setPrimaryKey(CAR_1_KEY);
            carJson.setDealerKey("");
            carJson.setName(CAR_1_NAME);

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

            carJson.setPrimaryKey(CAR_1_KEY);
            carJson.setDealerKey(CAR_1_DEALER_KEY);
            carJson.setName(CAR_1_NAME);
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

            carJson.setPrimaryKey(CAR_1_KEY);
            carJson.setDealerKey(CAR_1_DEALER_KEY);
            carJson.setName(CAR_1_NAME);
            carJson.setDriveTrain(CAR_1_DRIVETRAIN);
            carJson.setApiration(BAD_ASPIRATION);

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

            carJson.setPrimaryKey(CAR_1_KEY);
            carJson.setDealerKey(BAD_DEALER_KEY);
            carJson.setName(CAR_1_NAME);
            carJson.setDriveTrain(CAR_1_DRIVETRAIN);
            carJson.setApiration(CAR_1_ASPIRATION);
            
            carValidate.validateCarSave(carJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
    
    @Test(groups = "goodDelete")
    public void validateCarDelete() throws CarException {
        logger.info("Validate Car Delete");
        
        carValidate.validateCarDelete(CAR_1_KEY);
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
    
    private void deleteTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }
    
    private void deleteDealerTestRecord(String deleteKey) {
        Dealer dealer = dealerRepository.findOne(deleteKey);

        if (dealer != null) {
            dealerRepository.delete(dealer);
        }
    }
}
