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

    private static final String EXPECTED_MAX_KEY = CAR9.getPrimaryKey();

    private static final int EXPECTED_NUMBER_OF_CARS = 8;

    private static final int EXPECTED_NUMBER_OF_CAR_LEVEL_STAT_ROWS = 8;
    private static final int EXPECTED_LEVEL_4_ROW = 2;
    private static final long LEVEL_4_NUMBER_OF_CARS = 2L;
    private static final double LEVEL_4_AVG_PP = 422.5;
    private static final double LEVEL_4_AVG_HP = 234.0;
    private static final double LEVEL_4_AVG_PRICE = 57445.00;

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

        assertEquals(carsStats.size(), EXPECTED_NUMBER_OF_CAR_LEVEL_STAT_ROWS);
        assertEquals(carsStats.get(EXPECTED_LEVEL_4_ROW)[0], 4);
        assertEquals(carsStats.get(EXPECTED_LEVEL_4_ROW)[1], LEVEL_4_NUMBER_OF_CARS);
        assertEquals(carsStats.get(EXPECTED_LEVEL_4_ROW)[2], LEVEL_4_AVG_PP);
        assertEquals(carsStats.get(EXPECTED_LEVEL_4_ROW)[3], LEVEL_4_AVG_HP);
        assertEquals(carsStats.get(EXPECTED_LEVEL_4_ROW)[4], LEVEL_4_AVG_PRICE);
    }
}
