package backEnd.general.ownerCars;

import backEnd.general.GTSportDataTesting;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the owner car service.
 *
 * @author jonathan
 */
public class OwnerCarServiceTest extends GTSportDataTesting {

    private static final String BAD_OWNERCAR_KEY = "X!X999999999";

    private static final String OWNERCAR4_OWNER_KEY = OWNER3.getPrimaryKey();
    private static final String OWNERCAR4_CAR_KEY = CAR4.getPrimaryKey();
    private static final String OWNERCAR4_ID = CAR4.getName() + "_TESTING";
    private static final String OWNERCAR4_COLOUR = "White";
    private static final int OWNERCAR4_POWER_POINTS = CAR4.getPowerPoints();
    private static final LocalDate OWNERCAR4_AQUIRED_DATE = LocalDate.of(2017, Month.AUGUST, 20);

    private static final String OWNERCAR4_NEW_ID = "Testing owner car 4 update";

    private static final int EXPECTED_OWNERCAR_COUNT = 3;
    private static final int EXPECTED_OWNERCAR_BY_OWNER_COUNT = 2;

    private static final String EXPECTED_OWNERCAR_KEY = "OWC900000004";

    private String ownerCar4Key = "";

    @Autowired
    private OwnerCarService ownerCarService;

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
        deleteOwnerCarTestRecord(ownerCar4Key);

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

    /**
     * Test for getting an owner car by the primary key.
     *
     * @throws OwnerCarException should not find any errors
     */
    @Test
    public void getOwnerCarByKey() throws OwnerCarException {
        logger.info("Get Owner Car By Key");

        OwnerCarJson ownerCarJson = ownerCarService.getOwnerCarByKey(OWNERCAR2.getPrimaryKey());

        assertEquals(ownerCarJson.getPrimaryKey(), OWNERCAR2.getPrimaryKey());
    }

    /**
     * Test for finding an owner car by the primary key with a bad key.
     *
     * @throws OwnerCarException Should find an error that the owner car can not
     * be found.
     */
    @Test(expectedExceptions = OwnerCarException.class)
    public void getOwnerCarByKeyBadKey() throws OwnerCarException {
        logger.info("Get Owner Car By Key Bad Key: " + BAD_OWNERCAR_KEY);

        String expectedError = OwnerCarException.OWNERCAR_KEY_NOT_FOUND + BAD_OWNERCAR_KEY;

        try {
            ownerCarService.getOwnerCarByKey(BAD_OWNERCAR_KEY);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test saving a new owner car.
     *
     * @throws OwnerCarException should not find any errors
     */
    @Test
    public void saveNewOwnerCar() throws OwnerCarException {
        logger.info("Save New Owner Car");

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setOwnerKey(OWNERCAR4_OWNER_KEY);
        ownerCarJson.setCarKey(OWNERCAR4_CAR_KEY);
        ownerCarJson.setCarId(OWNERCAR4_ID);
        ownerCarJson.setColour(OWNERCAR4_COLOUR);
        ownerCarJson.setPowerPoints(OWNERCAR4_POWER_POINTS);
        ownerCarJson.setAcquiredDate(OWNERCAR4_AQUIRED_DATE);

        ownerCarService.saveOwnerCar(ownerCarJson);

        ownerCar4Key = ownerCarJson.getPrimaryKey();
    }

    /**
     * Test updating an existing owner car.
     *
     * @throws OwnerCarException should find no errors
     */
    @Test(dependsOnMethods = {"saveNewOwnerCar"})
    public void updateOwnerCar() throws OwnerCarException {
        logger.info("Update Owner Car");

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(ownerCar4Key);
        ownerCarJson.setOwnerKey(OWNERCAR4_OWNER_KEY);
        ownerCarJson.setCarKey(OWNERCAR4_CAR_KEY);
        ownerCarJson.setCarId(OWNERCAR4_NEW_ID);
        ownerCarJson.setColour(OWNERCAR4_COLOUR);
        ownerCarJson.setPowerPoints(OWNERCAR4_POWER_POINTS);
        ownerCarJson.setAcquiredDate(OWNERCAR4_AQUIRED_DATE);

        ownerCarService.saveOwnerCar(ownerCarJson);
    }

    /**
     * Test for deleting an owner car.
     *
     * @throws OwnerCarException should find no errors
     */
    @Test(dependsOnMethods = {"updateOwnerCar"})
    public void deleteOwnerCar() throws OwnerCarException {
        logger.info("Delete Owner Car");

        ownerCarService.deleteOwnerCar(ownerCar4Key);
    }

    /**
     * Test for getting a list of all the owner cars.
     */
    @Test(dependsOnMethods = {"deleteOwnerCar"})
    public void getOwnerCarList() {
        logger.info("Get Owner Car List");

        List<OwnerCarJson> ownerCarJsons = ownerCarService.getOwnerCarList();

        assertEquals(ownerCarJsons.size(), EXPECTED_OWNERCAR_COUNT);
        assertEquals(ownerCarJsons.get(0).getPrimaryKey(), OWNERCAR1.getPrimaryKey());
    }

    /**
     * Test for getting a list of owner cars for an owner key.
     */
    @Test(dependsOnMethods = {"deleteOwnerCar"})
    public void getOwnerCarListByOwnerKey() {
        logger.info("Get Owner Car List By Owner Key: " + OWNER1.getPrimaryKey());

        List<OwnerCarJson> ownerCarJsons = ownerCarService.getOwnerCarListByOwnerKey(OWNER1.getPrimaryKey());

        assertEquals(ownerCarJsons.size(), EXPECTED_OWNERCAR_BY_OWNER_COUNT);
        assertEquals(ownerCarJsons.get(0).getPrimaryKey(), OWNERCAR1.getPrimaryKey());
    }

    /**
     * Test for resetting the last owner car key.
     *
     * @throws OwnerCarException should find no errors
     */
    @Test(dependsOnMethods = {"deleteOwnerCar"})
    public void resetKeys() throws OwnerCarException {
        logger.info("Reset Keys");

        ownerCarService.resetKeys();

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setOwnerKey(OWNERCAR4_OWNER_KEY);
        ownerCarJson.setCarKey(OWNERCAR4_CAR_KEY);
        ownerCarJson.setCarId(OWNERCAR4_ID);
        ownerCarJson.setColour(OWNERCAR4_COLOUR);
        ownerCarJson.setPowerPoints(OWNERCAR4_POWER_POINTS);
        ownerCarJson.setAcquiredDate(OWNERCAR4_AQUIRED_DATE);

        ownerCarService.saveOwnerCar(ownerCarJson);

        ownerCar4Key = ownerCarJson.getPrimaryKey();

        assertEquals(ownerCar4Key, EXPECTED_OWNERCAR_KEY);
    }
}
