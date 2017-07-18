/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
import java.util.ArrayList;
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
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class CarServiceTest extends AbstractTestNGSpringContextTests {
    private static final String DEALER_1_KEY = "DEA9CAR00001";
  private static final String DEALER_1_NAME = "CAR TEST DEALER";
    private static final String DEALER_1_COUNTRY_KEY = "COU9CAR00001";

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
    private static final String CAR_1_DRIVETRAIN = DriveTrain.FR.getDescription();
    private static final String CAR_1_ASPIRATION = Aspiration.NA.getDescription();
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
    private static final String CAR_2_DRIVETRAIN = DriveTrain.FR.getDescription();
    private static final String CAR_2_ASPIRATION = Aspiration.NA.getDescription();
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
    private static final String CAR_3_DRIVETRAIN = DriveTrain.FR.getDescription();
    private static final String CAR_3_ASPIRATION = Aspiration.NA.getDescription();
    private static final double CAR_3_LENGTH = 175.60;
    private static final double CAR_3_WIDTH = 72.40;
    private static final double CAR_3_HEIGHT = 48.60;
    private static final double CAR_3_WEIGHT = 1150;
    
    private static final String CAR_4_NAME = "Mustang Mach 1 '71";
    private static final String CAR_4_DEALER_KEY = DEALER_1_KEY;
    private static final int CAR_4_YEAR = 1971;
    private static final int CAR_4_LEVEL = 4;
    private static final int CAR_4_POWER_POINTS = 453;
    private static final double CAR_4_PRICE = 50000.00;
    private static final String CAR_4_DISPLACEMENTCC = "5752";
    private static final int CAR_4_HORSE_POWER = 299;
    private static final String CAR_4_POWER_RPM = "";
    private static final double CAR_4_TORQUE_FTLB = 0.00;
    private static final String CAR_4_TORQUE_RPM = "";
    private static final String CAR_4_DRIVETRAIN = DriveTrain.FR.getDescription();
    private static final String CAR_4_ASPIRATION = Aspiration.NA.getDescription();
    private static final double CAR_4_LENGTH = 189.50;
    private static final double CAR_4_WIDTH = 74.10;
    private static final double CAR_4_HEIGHT = 50.10;
    private static final double CAR_4_WEIGHT = 1615;
    
    private static final String CAR_4_NEW_NAME = "Mustang Mach 1 1971";
    
    private static final String BAD_CAR_KEY = "xx!!!xx";
    private static final String BAD_CAR_NAME = "GTR";
    
    private static final int EXPECTED_NUMBER_OF_ROWS = 3;
    private static final int EXPECTED_NUMBER_OF_ROWS_BY_DEALER = 2;
    
    private static final String EXPECTED_MAX_KEY = "CAR900000004";

    
    private String car4Key = "";
    
    @Autowired
    private CarService carService;
    
    @Autowired
    private CarRepository carRepository;
    
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
        deleteTestRecord(car4Key);

        carService.resetKeys();
        
        deleteDealerTestRecord(DEALER_1_KEY);        
    }
    
    @Test
    public void getCarJsonByKey() throws CarException {
        logger.info("Get Car Json By Key");
        
        CarJson carJson = carService.getCarJsonByKey(CAR_2_KEY);
        
        assertEquals(carJson.getPrimaryKey(), CAR_2_KEY);
    }
    
    @Test(expectedExceptions = CarException.class)
    public void getCarJsonByKeyBadKey() throws CarException {
        logger.info("Get Car Json By Key Bad Key: " + BAD_CAR_KEY);
        
        String expectedError = CarException.CAR_KEY_NOT_FOUND + BAD_CAR_KEY;
        
        try {
            carService.getCarJsonByKey(BAD_CAR_KEY);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
    
    @Test
    public void getCarJsonByName() throws CarException {
        logger.info("Get Car Json By Name");
        
        CarJson carJson = carService.getCarJsonByName(CAR_3_NAME);
        
        assertEquals(carJson.getPrimaryKey(), CAR_3_KEY);
    }
    
    @Test(expectedExceptions = CarException.class)
    public void getCarJsonByKeyBadName() throws CarException {
        logger.info("Get Car Json By Key Bad Name: " + BAD_CAR_NAME);
        
        String expectedError = CarException.CAR_NAME_NOT_FOUND + BAD_CAR_NAME;
        
        try {
            carService.getCarJsonByName(BAD_CAR_NAME);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
    
    @Test
    public void saveNewCar() throws CarException {
        logger.info("Save New Car");
        
        CarJson carJson = new CarJson();
        carJson.setPrimaryKey("");
        carJson.setName(CAR_4_NAME);
        carJson.setDealerKey(CAR_4_DEALER_KEY);
        carJson.setYear(CAR_4_YEAR);
        carJson.setLevel(CAR_4_LEVEL);
        carJson.setPowerPoints(CAR_4_POWER_POINTS);
        carJson.setPrice(CAR_4_PRICE);
        carJson.setDisplacementCC(CAR_4_DISPLACEMENTCC);
        carJson.setHorsePower(CAR_4_HORSE_POWER);
        carJson.setPowerRPM(CAR_4_POWER_RPM);
        carJson.setTorqueFtLb(CAR_4_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_4_TORQUE_RPM);
        carJson.setDriveTrain(CAR_4_DRIVETRAIN);
        carJson.setAspiration(CAR_4_ASPIRATION);
        carJson.setLength(CAR_4_LENGTH);
        carJson.setWidth(CAR_4_WIDTH);
        carJson.setHeight(CAR_4_HEIGHT);
        carJson.setWeight(CAR_4_WEIGHT);
        
        carService.saveCar(carJson);
        
        car4Key = carJson.getPrimaryKey();
    }
    
    @Test(dependsOnMethods = {"saveNewCar"})
    public void updateCar() throws CarException {
        logger.info("Update Car");
        
        CarJson carJson = new CarJson();
        carJson.setPrimaryKey(car4Key);
        carJson.setName(CAR_4_NEW_NAME);
        carJson.setDealerKey(CAR_4_DEALER_KEY);
        carJson.setYear(CAR_4_YEAR);
        carJson.setLevel(CAR_4_LEVEL);
        carJson.setPowerPoints(CAR_4_POWER_POINTS);
        carJson.setPrice(CAR_4_PRICE);
        carJson.setDisplacementCC(CAR_4_DISPLACEMENTCC);
        carJson.setHorsePower(CAR_4_HORSE_POWER);
        carJson.setPowerRPM(CAR_4_POWER_RPM);
        carJson.setTorqueFtLb(CAR_4_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_4_TORQUE_RPM);
        carJson.setDriveTrain(CAR_4_DRIVETRAIN);
        carJson.setAspiration(CAR_4_ASPIRATION);
        carJson.setLength(CAR_4_LENGTH);
        carJson.setWidth(CAR_4_WIDTH);
        carJson.setHeight(CAR_4_HEIGHT);
        carJson.setWeight(CAR_4_WEIGHT);
        
        carService.saveCar(carJson);        
    }
    
    @Test(dependsOnMethods = {"updateCar"})
    public void deleteCar() throws CarException {
        logger.info("Delete Car: " + car4Key);
        
        carService.deleteCar(car4Key);
    }
    
    @Test(dependsOnMethods = {"deleteCar"})
    public void getCarList() {
        logger.info("Get Car List");
        
        List<CarJson> carJsons = carService.getCarList();
        
        assertEquals(carJsons.size(), EXPECTED_NUMBER_OF_ROWS);
        assertEquals(carJsons.get(0).getPrimaryKey(), CAR_1_KEY);
    }
    
    @Test(dependsOnMethods = {"deleteCar"})
    public void getCarListByDealerKey() {
        logger.info("Get Car List By Dealer Key: " + DEALER_1_KEY);
        
        List<CarJson> carJsons = carService.getCarListByDealerKey(DEALER_1_KEY);
        
        assertEquals(carJsons.size(), EXPECTED_NUMBER_OF_ROWS_BY_DEALER);
        assertEquals(carJsons.get(0).getPrimaryKey(), CAR_1_KEY);
    }
    
    @Test(dependsOnMethods = {"deleteCar"})
    public void resetKeys() throws CarException {
        logger.info("Reset Keys");
        
        carService.resetKeys();
        
        CarJson carJson = new CarJson();
        carJson.setPrimaryKey("");
        carJson.setName(CAR_4_NAME);
        carJson.setDealerKey(CAR_4_DEALER_KEY);
        carJson.setYear(CAR_4_YEAR);
        carJson.setLevel(CAR_4_LEVEL);
        carJson.setPowerPoints(CAR_4_POWER_POINTS);
        carJson.setPrice(CAR_4_PRICE);
        carJson.setDisplacementCC(CAR_4_DISPLACEMENTCC);
        carJson.setHorsePower(CAR_4_HORSE_POWER);
        carJson.setPowerRPM(CAR_4_POWER_RPM);
        carJson.setTorqueFtLb(CAR_4_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_4_TORQUE_RPM);
        carJson.setDriveTrain(CAR_4_DRIVETRAIN);
        carJson.setAspiration(CAR_4_ASPIRATION);
        carJson.setLength(CAR_4_LENGTH);
        carJson.setWidth(CAR_4_WIDTH);
        carJson.setHeight(CAR_4_HEIGHT);
        carJson.setWeight(CAR_4_WEIGHT);
        
        carService.saveCar(carJson);
        
        car4Key = carJson.getPrimaryKey();
        
        assertEquals(EXPECTED_MAX_KEY, car4Key);
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
