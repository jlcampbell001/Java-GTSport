package backEnd.general.cars;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the car service.
 *
 * @author jonathan
 */
public class CarServiceTest extends GTSportDataTesting {

    private static final String CAR_4_NAME = "Mustang Mach 1 '71";
    private static final String CAR_4_DEALER_KEY = MANUFACTURER1.getPrimaryKey();
    private static final int CAR_4_YEAR = 1971;
    private static final int CAR_4_LEVEL = 4;
    private static final int CAR_4_POWER_POINTS = 453;
    private static final double CAR_4_PRICE = 50000.00;
    private static final String CAR_4_DISPLACEMENTCC = "5752";
    private static final int CAR_4_HORSE_POWER = 299;
    private static final String CAR_4_POWER_RPM = "";
    private static final double CAR_4_TORQUE_FTLB = 0.00;
    private static final String CAR_4_TORQUE_RPM = "";
    private static final DriveTrain CAR_4_DRIVETRAIN = DriveTrain.FR;
    private static final Aspiration CAR_4_ASPIRATION = Aspiration.NA;
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

        manufacturerRepository.saveAndFlush(MANUFACTURER1);
        manufacturerRepository.saveAndFlush(MANUFACTURER2);
        manufacturerRepository.saveAndFlush(MANUFACTURER3);

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
        deleteCarTestRecord(car4Key);

        carService.resetKeys();

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
     * Test to get the car Json by the car primary key.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void getCarJsonByKey() throws CarException {
        logger.info("Get Car Json By Key");

        CarJson carJson = carService.getCarJsonByKey(CAR2.getPrimaryKey());

        assertEquals(carJson.getPrimaryKey(), CAR2.getPrimaryKey());
    }

    /**
     * Test to get the car Json by the car primary key but using a bad key.
     *
     * @throws CarException should have an error of no car record found
     */
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

    /**
     * Test to get a car Json by the car name.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void getCarJsonByName() throws CarException {
        logger.info("Get Car Json By Name");

        CarJson carJson = carService.getCarJsonByName(CAR3.getName());

        assertEquals(carJson.getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to get a car Json name with a bad name.
     *
     * @throws CarException should find an error that no car was found
     */
    @Test(expectedExceptions = CarException.class)
    public void getCarJsonByNameBadName() throws CarException {
        logger.info("Get Car Json By Name Bad Name: " + BAD_CAR_NAME);

        String expectedError = CarException.CAR_NAME_NOT_FOUND + BAD_CAR_NAME;

        try {
            carService.getCarJsonByName(BAD_CAR_NAME);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    /**
     * Test saving a new car.
     *
     * @throws CarException should find no errors
     */
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

    /**
     * Test the save of a car with updated data.
     *
     * @throws CarException should find no errors
     */
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

    /**
     * Test deleting a car.
     *
     * @throws CarException should find no errors
     */
    @Test(dependsOnMethods = {"updateCar"})
    public void deleteCar() throws CarException {
        logger.info("Delete Car: " + car4Key);

        carService.deleteCar(car4Key);
    }

    /**
     * Test get a list of all cars.
     */
    @Test(dependsOnMethods = {"deleteCar"})
    public void getCarList() {
        logger.info("Get Car List");

        List<CarJson> carJsons = carService.getCarList();

        assertEquals(carJsons.size(), EXPECTED_NUMBER_OF_ROWS);
        assertEquals(carJsons.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test of getting a list of cars by a dealer key.
     */
    @Test(dependsOnMethods = {"deleteCar"})
    public void getCarListByDealerKey() {
        logger.info("Get Car List By Dealer Key: " + MANUFACTURER1.getPrimaryKey());

        List<CarJson> carJsons = carService.getCarListByDealerKey(MANUFACTURER1.getPrimaryKey());

        assertEquals(carJsons.size(), EXPECTED_NUMBER_OF_ROWS_BY_DEALER);
        assertEquals(carJsons.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to reset the keys to the max car key.
     *
     * @throws CarException should find no errors
     */
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
}
