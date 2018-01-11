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

    private static final String CAR_XX_NAME = "Mustang Mach 1 '71";
    private static final String CAR_XX_MANUFACTURER_KEY = MANUFACTURER3.getPrimaryKey();
    private static final int CAR_XX_YEAR = 1971;
    private static final Category CAR_XX_CATEGORY = Category.N400;
    private static final double CAR_XX_PRICE = 50000.00;
    private static final String CAR_XX_DISPLACEMENTCC = "5752";
    private static final int CAR_XX_MAX_POWER = 299;
    private static final String CAR_XX_POWER_RPM = "";
    private static final double CAR_XX_TORQUE_FTLB = 0.00;
    private static final String CAR_XX_TORQUE_RPM = "";
    private static final DriveTrain CAR_XX_DRIVETRAIN = DriveTrain.FR;
    private static final Aspiration CAR_XX_ASPIRATION = Aspiration.NA;
    private static final double CAR_XX_LENGTH = 189.50;
    private static final double CAR_XX_WIDTH = 74.10;
    private static final double CAR_XX_HEIGHT = 50.10;
    private static final double CAR_XX_WEIGHT = 1615;
    private static final double CAR_XX_MAXSPEED = 7.2;
    private static final double CAR_XX_ACCELERATION = 5.0;
    private static final double CAR_XX_BRAKING = 1.8;
    private static final double CAR_XX_CORNERING = 1.6;
    private static final double CAR_XX_STABILITY = 5.0;        


    private static final String CAR_XX_NEW_NAME = "Mustang Mach 1 1971";

    private static final String BAD_CAR_KEY = "xx!!!xx";
    private static final String BAD_CAR_NAME = "GTR";

    private static final int EXPECTED_NUMBER_OF_ROWS = 15;
    private static final int EXPECTED_NUMBER_OF_ROWS_BY_MANUFACTURER = 2;

    private static final String EXPECTED_MAX_KEY = "CAR900000016";

    private String carXXKey = "";

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
        countryRepository.saveAndFlush(COUNTRY4);
        countryRepository.saveAndFlush(COUNTRY5);

        manufacturerRepository.saveAndFlush(MANUFACTURER1);
        manufacturerRepository.saveAndFlush(MANUFACTURER2);
        manufacturerRepository.saveAndFlush(MANUFACTURER3);
        manufacturerRepository.saveAndFlush(MANUFACTURER4);
        manufacturerRepository.saveAndFlush(MANUFACTURER5);
        manufacturerRepository.saveAndFlush(MANUFACTURER6);
        manufacturerRepository.saveAndFlush(MANUFACTURER7);
        manufacturerRepository.saveAndFlush(MANUFACTURER8);
        manufacturerRepository.saveAndFlush(MANUFACTURER9);

        // add the cars to work with.
        carRepository.saveAndFlush(CAR1);
        carRepository.saveAndFlush(CAR2);
        carRepository.saveAndFlush(CAR3);
        carRepository.saveAndFlush(CAR3);
        carRepository.saveAndFlush(CAR4);
        carRepository.saveAndFlush(CAR5);
        carRepository.saveAndFlush(CAR6);
        carRepository.saveAndFlush(CAR7);
        carRepository.saveAndFlush(CAR8);
        carRepository.saveAndFlush(CAR9);
        carRepository.saveAndFlush(CAR10);
        carRepository.saveAndFlush(CAR11);
        carRepository.saveAndFlush(CAR12);
        carRepository.saveAndFlush(CAR13);
        carRepository.saveAndFlush(CAR14);
        carRepository.saveAndFlush(CAR15);

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
        deleteCarTestRecord(CAR4.getPrimaryKey());
        deleteCarTestRecord(CAR5.getPrimaryKey());
        deleteCarTestRecord(CAR6.getPrimaryKey());
        deleteCarTestRecord(CAR7.getPrimaryKey());
        deleteCarTestRecord(CAR8.getPrimaryKey());
        deleteCarTestRecord(CAR9.getPrimaryKey());
        deleteCarTestRecord(CAR10.getPrimaryKey());
        deleteCarTestRecord(CAR11.getPrimaryKey());
        deleteCarTestRecord(CAR12.getPrimaryKey());
        deleteCarTestRecord(CAR13.getPrimaryKey());
        deleteCarTestRecord(CAR14.getPrimaryKey());
        deleteCarTestRecord(CAR15.getPrimaryKey());
        deleteCarTestRecord(carXXKey);

        carService.resetKeys();

        deleteManufacturerTestRecord(MANUFACTURER1.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER2.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER3.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER4.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER5.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER6.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER7.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER8.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER9.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY4.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY5.getPrimaryKey());

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
        carJson.setName(CAR_XX_NAME);
        carJson.setManufacturerKey(CAR_XX_MANUFACTURER_KEY);
        carJson.setYear(CAR_XX_YEAR);
        carJson.setCategory(CAR_XX_CATEGORY);
        carJson.setPrice(CAR_XX_PRICE);
        carJson.setDisplacementCC(CAR_XX_DISPLACEMENTCC);
        carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setPowerRPM(CAR_XX_POWER_RPM);
        carJson.setTorqueFtLb(CAR_XX_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_XX_TORQUE_RPM);
        carJson.setDriveTrain(CAR_XX_DRIVETRAIN);
        carJson.setAspiration(CAR_XX_ASPIRATION);
        carJson.setLength(CAR_XX_LENGTH);
        carJson.setWidth(CAR_XX_WIDTH);
        carJson.setHeight(CAR_XX_HEIGHT);
        carJson.setWeight(CAR_XX_WEIGHT);
        carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setAcceleration(CAR_XX_ACCELERATION);
        carJson.setBraking(CAR_XX_BRAKING);
        carJson.setCornering(CAR_XX_CORNERING);
        carJson.setStability(CAR_XX_STABILITY);

        carService.saveCar(carJson);

        carXXKey = carJson.getPrimaryKey();
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
        carJson.setPrimaryKey(carXXKey);
        carJson.setName(CAR_XX_NEW_NAME);
        carJson.setManufacturerKey(CAR_XX_MANUFACTURER_KEY);
        carJson.setYear(CAR_XX_YEAR);
        carJson.setCategory(CAR_XX_CATEGORY);
        carJson.setPrice(CAR_XX_PRICE);
        carJson.setDisplacementCC(CAR_XX_DISPLACEMENTCC);
        carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setPowerRPM(CAR_XX_POWER_RPM);
        carJson.setTorqueFtLb(CAR_XX_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_XX_TORQUE_RPM);
        carJson.setDriveTrain(CAR_XX_DRIVETRAIN);
        carJson.setAspiration(CAR_XX_ASPIRATION);
        carJson.setLength(CAR_XX_LENGTH);
        carJson.setWidth(CAR_XX_WIDTH);
        carJson.setHeight(CAR_XX_HEIGHT);
        carJson.setWeight(CAR_XX_WEIGHT);
                carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setAcceleration(CAR_XX_ACCELERATION);
        carJson.setBraking(CAR_XX_BRAKING);
        carJson.setCornering(CAR_XX_CORNERING);
        carJson.setStability(CAR_XX_STABILITY);


        carService.saveCar(carJson);
    }

    /**
     * Test deleting a car.
     *
     * @throws CarException should find no errors
     */
    @Test(dependsOnMethods = {"updateCar"})
    public void deleteCar() throws CarException {
        logger.info("Delete Car: " + carXXKey);

        carService.deleteCar(carXXKey);
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

        List<CarJson> carJsons = carService.getCarListByManufacturerKey(MANUFACTURER1.getPrimaryKey());

        assertEquals(carJsons.size(), EXPECTED_NUMBER_OF_ROWS_BY_MANUFACTURER);
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
        carJson.setName(CAR_XX_NAME);
        carJson.setManufacturerKey(CAR_XX_MANUFACTURER_KEY);
        carJson.setYear(CAR_XX_YEAR);
        carJson.setCategory(CAR_XX_CATEGORY);
        carJson.setPrice(CAR_XX_PRICE);
        carJson.setDisplacementCC(CAR_XX_DISPLACEMENTCC);
        carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setPowerRPM(CAR_XX_POWER_RPM);
        carJson.setTorqueFtLb(CAR_XX_TORQUE_FTLB);
        carJson.setTorqueRPM(CAR_XX_TORQUE_RPM);
        carJson.setDriveTrain(CAR_XX_DRIVETRAIN);
        carJson.setAspiration(CAR_XX_ASPIRATION);
        carJson.setLength(CAR_XX_LENGTH);
        carJson.setWidth(CAR_XX_WIDTH);
        carJson.setHeight(CAR_XX_HEIGHT);
        carJson.setWeight(CAR_XX_WEIGHT);
        carJson.setMaxPower(CAR_XX_MAX_POWER);
        carJson.setAcceleration(CAR_XX_ACCELERATION);
        carJson.setBraking(CAR_XX_BRAKING);
        carJson.setCornering(CAR_XX_CORNERING);
        carJson.setStability(CAR_XX_STABILITY);
        

        carService.saveCar(carJson);

        carXXKey = carJson.getPrimaryKey();

        assertEquals(EXPECTED_MAX_KEY, carXXKey);
    }
}
