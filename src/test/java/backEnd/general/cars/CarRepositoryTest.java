package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
import backEnd.general.dealers.DealersForTesting;
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
public class CarRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = CAR3.getPrimaryKey();

    private static final int EXPECTED_NUMBER_OF_CARS = 2;

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
     * Delete the country records added for testing.
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

    @Test
    public void findByName() {
        logger.info("Find By Name");

        Car car = carRepository.findByName(CAR3.getName());

        assertEquals(car.getPrimaryKey(), CAR3.getPrimaryKey());
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

        List<Car> cars = carRepository.findAllByDealerKey(DEALER1.getPrimaryKey());

        assertEquals(cars.size(), EXPECTED_NUMBER_OF_CARS);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }
}
