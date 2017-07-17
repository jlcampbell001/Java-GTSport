package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.countries.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the car repository.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CarRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String DEALER_1_KEY = "DEA900000001";
    private static final String EXPECTED_MAX_KEY = "XXX900000003";

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

    private static final String CAR_3_KEY = EXPECTED_MAX_KEY;
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
    
    private static final int EXPECTED_NUMBER_OF_CARS = 2;

    @Autowired
    private CarRepository carRepository;

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
    }

    /**
     * Delete the country records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteTestRecord(CAR_1_KEY);
        deleteTestRecord(CAR_2_KEY);
        deleteTestRecord(CAR_3_KEY);
    }

    @Test
    public void findByName() {
        logger.info("Find By Name");
        
        Car car = carRepository.findByName(CAR_3_NAME);
        
        assertEquals(car.getPrimaryKey(), CAR_3_KEY);
    }
    
    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");
        
        String maxKey = carRepository.getMaxKey();
        
        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }
    
    @Test
    public void findAllByDealerKey() {
        logger.info("Find All By Dealer Key");
        
        List<Car> cars = carRepository.findAllByDealerKey(DEALER_1_KEY);
        
        assertEquals(cars.size(), EXPECTED_NUMBER_OF_CARS);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_1_KEY);
    }

    private void deleteTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }
}
