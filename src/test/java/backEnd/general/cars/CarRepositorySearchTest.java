/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import backEnd.general.GTSportConfig;
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
public class CarRepositorySearchTest extends AbstractTestNGSpringContextTests {

    private static final String DEALER_1_KEY = "DEA900000001";

    private static final String CAR_1_KEY = "XXX900000001";
    private static final String CAR_1_NAME = "Corvette Coupe (C2) '63";
    private static final String CAR_1_DEALER_KEY = DEALER_1_KEY; // Chevrolet North America USA
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
    private static final String CAR_2_DEALER_KEY = "DEA9000000002"; // Pontiac North American USA
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
    private static final String CAR_3_DEALER_KEY = DEALER_1_KEY; // Panoz North American USA
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
    private static final String CAR_4_NAME = "500 R '72";
    private static final String CAR_4_DEALER_KEY = DEALER_1_KEY; // Fiat Europe Italy
    private static final int CAR_4_YEAR = 1972;
    private static final int CAR_4_LEVEL = 2;
    private static final int CAR_4_POWER_POINTS = 221;
    private static final double CAR_4_PRICE = 15700.00;
    private static final String CAR_4_DISPLACEMENTCC = "594";
    private static final int CAR_4_HORSE_POWER = 17;
    private static final String CAR_4_POWER_RPM = "4000";
    private static final double CAR_4_TORQUE_FTLB = 28.90;
    private static final String CAR_4_TORQUE_RPM = "2500";
    private static final String CAR_4_DRIVETRAIN = "RR";
    private static final String CAR_4_ASPIRATION = "NA";
    private static final double CAR_4_LENGTH = 120.90;
    private static final double CAR_4_WIDTH = 54.30;
    private static final double CAR_4_HEIGHT = 52.60;
    private static final double CAR_4_WEIGHT = 595;

    private static final String CAR_5_KEY = "XXX900000005";
    private static final String CAR_5_NAME = "1500 Biposto Bertone B.A.T. 1 '52";
    private static final String CAR_5_DEALER_KEY = DEALER_1_KEY; // Abarth Europe Italy
    private static final int CAR_5_YEAR = 1952;
    private static final int CAR_5_LEVEL = 3;
    private static final int CAR_5_POWER_POINTS = 300;
    private static final double CAR_5_PRICE = 3500000.00;
    private static final String CAR_5_DISPLACEMENTCC = "";
    private static final int CAR_5_HORSE_POWER = 73;
    private static final String CAR_5_POWER_RPM = "5500";
    private static final double CAR_5_TORQUE_FTLB = 73.70;
    private static final String CAR_5_TORQUE_RPM = "4000";
    private static final String CAR_5_DRIVETRAIN = "FR";
    private static final String CAR_5_ASPIRATION = "NA";
    private static final double CAR_5_LENGTH = 0.00;
    private static final double CAR_5_WIDTH = 0.00;
    private static final double CAR_5_HEIGHT = 0.00;
    private static final double CAR_5_WEIGHT = 870;

    private static final String CAR_6_KEY = "XXX900000006";
    private static final String CAR_6_NAME = "599 '06";
    private static final String CAR_6_DEALER_KEY = DEALER_1_KEY; // Ferrari Europe Italy
    private static final int CAR_6_YEAR = 2006;
    private static final int CAR_6_LEVEL = 5;
    private static final int CAR_6_POWER_POINTS = 545;
    private static final double CAR_6_PRICE = 320300.00;
    private static final String CAR_6_DISPLACEMENTCC = "5999";
    private static final int CAR_6_HORSE_POWER = 611;
    private static final String CAR_6_POWER_RPM = "7600";
    private static final double CAR_6_TORQUE_FTLB = 448.40;
    private static final String CAR_6_TORQUE_RPM = "5600";
    private static final String CAR_6_DRIVETRAIN = "FR";
    private static final String CAR_6_ASPIRATION = "NA";
    private static final double CAR_6_LENGTH = 183.70;
    private static final double CAR_6_WIDTH = 77.20;
    private static final double CAR_6_HEIGHT = 52.60;
    private static final double CAR_6_WEIGHT = 1580;

    private static final String CAR_7_KEY = "XXX900000007";
    private static final String CAR_7_NAME = "Chaparral 2X Vision Gran Turismo";
    private static final String CAR_7_DEALER_KEY = DEALER_1_KEY; // Chevrolet North American USA
    private static final int CAR_7_YEAR = 2025;
    private static final int CAR_7_LEVEL = 7;
    private static final int CAR_7_POWER_POINTS = 793;
    private static final double CAR_7_PRICE = 1000000.00;
    private static final String CAR_7_DISPLACEMENTCC = "";
    private static final int CAR_7_HORSE_POWER = 905;
    private static final String CAR_7_POWER_RPM = "9100";
    private static final double CAR_7_TORQUE_FTLB = 0.00;
    private static final String CAR_7_TORQUE_RPM = "";
    private static final String CAR_7_DRIVETRAIN = "";
    private static final String CAR_7_ASPIRATION = "";
    private static final double CAR_7_LENGTH = 0.00;
    private static final double CAR_7_WIDTH = 0.00;
    private static final double CAR_7_HEIGHT = 0.00;
    private static final double CAR_7_WEIGHT = 450;

    private static final String CAR_8_KEY = "XXX900000008";
    private static final String CAR_8_NAME = "Formula Gran Turismo";
    private static final String CAR_8_DEALER_KEY = DEALER_1_KEY; // Gran Turismo Asia-Pacific PDI
    private static final int CAR_8_YEAR = 0;
    private static final int CAR_8_LEVEL = 8;
    private static final int CAR_8_POWER_POINTS = 880;
    private static final double CAR_8_PRICE = 2000000.00;
    private static final String CAR_8_DISPLACEMENTCC = "";
    private static final int CAR_8_HORSE_POWER = 891;
    private static final String CAR_8_POWER_RPM = "17500";
    private static final double CAR_8_TORQUE_FTLB = 289.30;
    private static final String CAR_8_TORQUE_RPM = "15500";
    private static final String CAR_8_DRIVETRAIN = "MR";
    private static final String CAR_8_ASPIRATION = "NA";
    private static final double CAR_8_LENGTH = 0.00;
    private static final double CAR_8_WIDTH = 0.00;
    private static final double CAR_8_HEIGHT = 0.00;
    private static final double CAR_8_WEIGHT = 550;

    private static final String CAR_9_KEY = "XXX900000009";
    private static final String CAR_9_NAME = "Tomahawk X Vision Gran Turismo";
    private static final String CAR_9_DEALER_KEY = DEALER_1_KEY; // SRT North America USA
    private static final int CAR_9_YEAR = 2035;
    private static final int CAR_9_LEVEL = 9;
    private static final int CAR_9_POWER_POINTS = 962;
    private static final double CAR_9_PRICE = 1000000.00;
    private static final String CAR_9_DISPLACEMENTCC = "7000";
    private static final int CAR_9_HORSE_POWER = 2586;
    private static final String CAR_9_POWER_RPM = "13800";
    private static final double CAR_9_TORQUE_FTLB = 898.50;
    private static final String CAR_9_TORQUE_RPM = "11300";
    private static final String CAR_9_DRIVETRAIN = "4WD";
    private static final String CAR_9_ASPIRATION = "NA";
    private static final double CAR_9_LENGTH = 0.00;
    private static final double CAR_9_WIDTH = 0.00;
    private static final double CAR_9_HEIGHT = 0.00;
    private static final double CAR_9_WEIGHT = 749;

    private static final int LEVEL_MIN_RANGE = 5;
    private static final int LEVEL_MAX_RANGE = 7;
    private static final int LEVEL_RANGE_RECORDS_EXPECTED = 3;
    private static final int LEVEL_MIN_SET_RECORDS_EXPECTED = 5;
    private static final int LEVEL_MAX_SET_RECORDS_EXPECTED = 7;

    private static final int YEAR_MIN_RANGE = 1972;
    private static final int YEAR_MAX_RANGE = 1998;
    private static final int YEAR_RANGE_RECORDS_EXPECTED = 3;
    private static final int YEAR_MIN_SET_RECORDS_EXPECTED = 6;
    private static final int YEAR_MAX_SET_RECORDS_EXPECTED = 6;

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

        Car car4 = new Car(CAR_4_KEY, CAR_4_NAME, CAR_4_DEALER_KEY, CAR_4_YEAR,
                CAR_4_LEVEL, CAR_4_POWER_POINTS, CAR_4_PRICE, CAR_4_DISPLACEMENTCC,
                CAR_4_HORSE_POWER, CAR_4_POWER_RPM, CAR_4_TORQUE_FTLB, CAR_4_TORQUE_RPM,
                CAR_4_DRIVETRAIN, CAR_4_ASPIRATION, CAR_4_LENGTH, CAR_4_WIDTH,
                CAR_4_HEIGHT, CAR_4_WEIGHT);
        carRepository.saveAndFlush(car4);

        Car car5 = new Car(CAR_5_KEY, CAR_5_NAME, CAR_5_DEALER_KEY, CAR_5_YEAR,
                CAR_5_LEVEL, CAR_5_POWER_POINTS, CAR_5_PRICE, CAR_5_DISPLACEMENTCC,
                CAR_5_HORSE_POWER, CAR_5_POWER_RPM, CAR_5_TORQUE_FTLB, CAR_5_TORQUE_RPM,
                CAR_5_DRIVETRAIN, CAR_5_ASPIRATION, CAR_5_LENGTH, CAR_5_WIDTH,
                CAR_5_HEIGHT, CAR_5_WEIGHT);
        carRepository.saveAndFlush(car5);

        Car car6 = new Car(CAR_6_KEY, CAR_6_NAME, CAR_6_DEALER_KEY, CAR_6_YEAR,
                CAR_6_LEVEL, CAR_6_POWER_POINTS, CAR_6_PRICE, CAR_6_DISPLACEMENTCC,
                CAR_6_HORSE_POWER, CAR_6_POWER_RPM, CAR_6_TORQUE_FTLB, CAR_6_TORQUE_RPM,
                CAR_6_DRIVETRAIN, CAR_6_ASPIRATION, CAR_6_LENGTH, CAR_6_WIDTH,
                CAR_6_HEIGHT, CAR_6_WEIGHT);
        carRepository.saveAndFlush(car6);

        Car car7 = new Car(CAR_7_KEY, CAR_7_NAME, CAR_7_DEALER_KEY, CAR_7_YEAR,
                CAR_7_LEVEL, CAR_7_POWER_POINTS, CAR_7_PRICE, CAR_7_DISPLACEMENTCC,
                CAR_7_HORSE_POWER, CAR_7_POWER_RPM, CAR_7_TORQUE_FTLB, CAR_7_TORQUE_RPM,
                CAR_7_DRIVETRAIN, CAR_7_ASPIRATION, CAR_7_LENGTH, CAR_7_WIDTH,
                CAR_7_HEIGHT, CAR_7_WEIGHT);
        carRepository.saveAndFlush(car7);

        Car car8 = new Car(CAR_8_KEY, CAR_8_NAME, CAR_8_DEALER_KEY, CAR_8_YEAR,
                CAR_8_LEVEL, CAR_8_POWER_POINTS, CAR_8_PRICE, CAR_8_DISPLACEMENTCC,
                CAR_8_HORSE_POWER, CAR_8_POWER_RPM, CAR_8_TORQUE_FTLB, CAR_8_TORQUE_RPM,
                CAR_8_DRIVETRAIN, CAR_8_ASPIRATION, CAR_8_LENGTH, CAR_8_WIDTH,
                CAR_8_HEIGHT, CAR_8_WEIGHT);
        carRepository.saveAndFlush(car8);

        Car car9 = new Car(CAR_9_KEY, CAR_9_NAME, CAR_9_DEALER_KEY, CAR_9_YEAR,
                CAR_9_LEVEL, CAR_9_POWER_POINTS, CAR_9_PRICE, CAR_9_DISPLACEMENTCC,
                CAR_9_HORSE_POWER, CAR_9_POWER_RPM, CAR_9_TORQUE_FTLB, CAR_9_TORQUE_RPM,
                CAR_9_DRIVETRAIN, CAR_9_ASPIRATION, CAR_9_LENGTH, CAR_9_WIDTH,
                CAR_9_HEIGHT, CAR_9_WEIGHT);
        carRepository.saveAndFlush(car9);
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
        deleteTestRecord(CAR_4_KEY);
        deleteTestRecord(CAR_5_KEY);
        deleteTestRecord(CAR_6_KEY);
        deleteTestRecord(CAR_7_KEY);
        deleteTestRecord(CAR_8_KEY);
        deleteTestRecord(CAR_9_KEY);
    }

    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoCriteria() throws CarException {
        logger.info("Find All By Criteria No Criteria");

        String ExpectedError = CarException.SEARCH_CRITERIA_NOT_PROVIDED;

        CarSearchJson carSearchJson = new CarSearchJson();

        try {
            List<Car> cars = carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoRecordsFound() throws CarException {
        logger.info("Find All By Criteria No Records Found");

        String ExpectedError = CarException.NO_CARS_FOUND_FOR_CRITERIA;

        CarSearchJson carSearchJson = new CarSearchJson();
        carSearchJson.setLevelFrom(99999);

        try {
            List<Car> cars = carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    @Test
    public void findAllByCriteriaLevelRange() throws CarException {
        logger.info("Find All By Criteria Level Range: " + LEVEL_MIN_RANGE + " - " + LEVEL_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelFrom(LEVEL_MIN_RANGE);
        carSearchJson.setLevelTo(LEVEL_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_3_KEY);
    }

    @Test
    public void findAllByCriteriaLevelRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + LEVEL_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelFrom(LEVEL_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_3_KEY);
    }

    @Test
    public void findAllByCriteriaLevelRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + "MIN - " + LEVEL_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelTo(LEVEL_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_1_KEY);
    }

    @Test
    public void findAllByCriteriaYearRange() throws CarException {
        logger.info("Find All By Criteria Year Range: " + YEAR_MIN_RANGE + " - " + YEAR_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearFrom(YEAR_MIN_RANGE);
        carSearchJson.setYearTo(YEAR_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_2_KEY);
    }

    @Test
    public void findAllByCriteriaYearRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Year Range: " + YEAR_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearFrom(YEAR_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_2_KEY);
    }

    @Test
    public void findAllByCriteriaYearRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Year Range: MIN - " + YEAR_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearTo(YEAR_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR_1_KEY);
    }

    private void deleteTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }

}
