package backEnd.general.ownerCars;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the owner car repository.
 *
 * @author jonathan
 */
public class OwnerCarRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = OWNERCAR3.getPrimaryKey();

    private static final int EXPECTED_NUMBER_OF_OWNERCARS_BY_OWNER = 2;
    private static final int EXPRECTED_NUMBER_OF_OWNERCARS_BY_CAR = 1;

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
     * Test for finding an owner car by a car Id.
     */
    @Test
    public void findByCarId() {
        logger.info("Find By Car Id");

        OwnerCar ownerCar = ownerCarRepository.findByCarId(OWNERCAR2.getCarId());

        assertEquals(ownerCar.getPrimaryKey(), OWNERCAR2.getPrimaryKey());
    }

    /**
     * Test to get the highest valued primary key.
     */
    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");

        String maxKey = ownerCarRepository.getMaxKey();

        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }

    /**
     * Test for finding all the owner cars by an owner key.
     */
    @Test
    public void findAllByOwnerKey() {
        logger.info("Find All By Owner Key");

        List<OwnerCar> ownerCars = ownerCarRepository.findAllByOwnerKey(OWNER1.getPrimaryKey());

        assertEquals(ownerCars.size(), EXPECTED_NUMBER_OF_OWNERCARS_BY_OWNER);
        assertEquals(ownerCars.get(0).getPrimaryKey(), OWNERCAR1.getPrimaryKey());
    }

    /**
     * Test for finding all the owner cars for a car key.
     */
    @Test
    public void findAllByCarKey() {
        logger.info("Find All By Car Key");

        List<OwnerCar> ownersCars = ownerCarRepository.findAllByCarKey(CAR1.getPrimaryKey());

        assertEquals(ownersCars.size(), EXPRECTED_NUMBER_OF_OWNERCARS_BY_CAR);
        assertEquals(ownersCars.get(0).getPrimaryKey(), OWNERCAR1.getPrimaryKey());
    }
}
