package backEnd.general.cars;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the car repository.
 *
 * @author jonathan
 */
public class CarRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = CAR15.getPrimaryKey();

    private static final int EXPECTED_NUMBER_OF_CARS = 2;

    private static final int EXPECTED_NUMBER_OF_CAR_CATEGORY_STAT_ROWS = 14;
    private static final int EXPECTED_CATEGORY_N400_ROW = 3;
    private static final long CATEGORY_N400_NUMBER_OF_CARS = 2L;
    private static final double CATEGORY_N400_AVG_HP = 394.5;
    private static final double CATEGORY_N400_AVG_PRICE = 54155.00;

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
     * Test to find a car by a name.
     */
    @Test
    public void findByName() {
        logger.info("Find By Name");

        Car car = carRepository.findByName(CAR3.getName());

        assertEquals(car.getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find the highest car primary key.
     */
    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");

        String maxKey = carRepository.getMaxKey();

        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }

    /**
     * Test find all cars by a dealer key.
     */
    @Test
    public void findAllByDealerKey() {
        logger.info("Find All By Dealer Key");

        List<Car> cars = carRepository.findAllByManufacturerKey(MANUFACTURER1.getPrimaryKey());

        assertEquals(cars.size(), EXPECTED_NUMBER_OF_CARS);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to get a list of car statistics.
     */
    @Test
    public void getCarStatistics() {
        logger.info("Get Car Statistics");

        List<Object[]> carsStats = carRepository.getCarsStatistics();

        assertEquals(carsStats.size(), EXPECTED_NUMBER_OF_CAR_CATEGORY_STAT_ROWS);
        assertEquals(carsStats.get(EXPECTED_CATEGORY_N400_ROW)[0], Category.N400);
        assertEquals(carsStats.get(EXPECTED_CATEGORY_N400_ROW)[1], CATEGORY_N400_NUMBER_OF_CARS);
        assertEquals(carsStats.get(EXPECTED_CATEGORY_N400_ROW)[2], CATEGORY_N400_AVG_HP);
        assertEquals(carsStats.get(EXPECTED_CATEGORY_N400_ROW)[3], CATEGORY_N400_AVG_PRICE);
    }
}
