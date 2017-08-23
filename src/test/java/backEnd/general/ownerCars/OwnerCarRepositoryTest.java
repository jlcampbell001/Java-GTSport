package backEnd.general.ownerCars;

import backEnd.general.GTSportDataTesting;
import backEnd.general.cars.Car;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author jonathan
 */
public class OwnerCarRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = OWNERCAR3.getPrimaryKey();

    private static final int EXPECTED_NUMBER_OF_OWNERCARS = 2;

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

        carRepository.saveAndFlush(CAR1);
        carRepository.saveAndFlush(CAR2);
        carRepository.saveAndFlush(CAR3);
        carRepository.saveAndFlush(CAR4);
        carRepository.saveAndFlush(CAR5);
        carRepository.saveAndFlush(CAR6);
        carRepository.saveAndFlush(CAR7);
        carRepository.saveAndFlush(CAR8);
        carRepository.saveAndFlush(CAR9);

        ownerRepository.saveAndFlush(OWNER1);
        ownerRepository.saveAndFlush(OWNER2);
        ownerRepository.saveAndFlush(OWNER3);

        ownerCarRepository.saveAndFlush(OWNERCAR1);
        ownerCarRepository.saveAndFlush(OWNERCAR2);
        ownerCarRepository.saveAndFlush(OWNERCAR3);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteOwnerCarTestRecord(OWNERCAR1.getPrimaryKey());
        deleteOwnerCarTestRecord(OWNERCAR2.getPrimaryKey());
        deleteOwnerCarTestRecord(OWNERCAR3.getPrimaryKey());

        deleteOwnerTestRecord(OWNER1.getPrimaryKey());
        deleteOwnerTestRecord(OWNER2.getPrimaryKey());
        deleteOwnerTestRecord(OWNER3.getPrimaryKey());

        deleteCarTestRecord(CAR1.getPrimaryKey());
        deleteCarTestRecord(CAR2.getPrimaryKey());
        deleteCarTestRecord(CAR3.getPrimaryKey());
        deleteCarTestRecord(CAR4.getPrimaryKey());
        deleteCarTestRecord(CAR5.getPrimaryKey());
        deleteCarTestRecord(CAR6.getPrimaryKey());
        deleteCarTestRecord(CAR7.getPrimaryKey());
        deleteCarTestRecord(CAR8.getPrimaryKey());
        deleteCarTestRecord(CAR9.getPrimaryKey());

        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());
        deleteDealerTestRecord(DEALER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    @Test
    public void findByCarId() {
        logger.info("Find By Car Id");

        OwnerCar ownerCar = ownerCarRepository.findByCarId(OWNERCAR2.getCarId());

        assertEquals(ownerCar.getPrimaryKey(), OWNERCAR2.getPrimaryKey());
    }

    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");

        String maxKey = ownerCarRepository.getMaxKey();

        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }

    @Test
    public void findAllByOwnerKey() {
        logger.info("Find All By Owner Key");

        List<OwnerCar> ownerCars = ownerCarRepository.findAllByOwnerKey(OWNER1.getPrimaryKey());

        assertEquals(ownerCars.size(), EXPECTED_NUMBER_OF_OWNERCARS);
        assertEquals(ownerCars.get(0).getPrimaryKey(), OWNERCAR1.getPrimaryKey());
    }
}
