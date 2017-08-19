package backEnd.general.cars;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Testing class for car searching.
 *
 * @author jonathan
 */
public class CarRepositorySearchTest extends GTSportDataTesting {

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

    private static final int POWER_POINTS_MIN_RANGE = 500;
    private static final int POWER_POINTS_MAX_RANGE = 800;
    private static final int POWER_POINTS_RANGE_RECORDS_EXPECTED = 3;
    private static final int POWER_POINTS_MIN_SET_RECORDS_EXPECTED = 5;
    private static final int POWER_POINTS_MAX_SET_RECORDS_EXPECTED = 7;

    private static final int HORSE_POWER_MIN_RANGE = 500;
    private static final int HORSE_POWER_MAX_RANGE = 900;
    private static final int HORSE_POWER_RANGE_RECORDS_EXPECTED = 3;
    private static final int HORSE_POWER_MIN_SET_RECORDS_EXPECTED = 5;
    private static final int HORSE_POWER_MAX_SET_RECORDS_EXPECTED = 7;

    private static final DriveTrain DRIVE_TRAIN_TEST = DriveTrain.FR;
    private static final int DRIVE_TRAIN_RECORDS_EXPECTED = 5;

    private static final String DEALER_NAME_TEST = DEALER2.getName();
    private static final int DEALER_NAME_RECORDS_EXPECTED = 1;

    private static final String COUNTRY_DESCRIPTION_TEST = COUNTRY2.getDescription();
    private static final int COUNTRY_DESCRIPTION_RECORDS_EXPECTED = 1;

    private static final String REGION_DESCRIPTION_TEST = REGION2.getDescription();
    private static final int REGION_DESCRIPTION_RECORDS_EXPECTED = 1;

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
     * Delete the setup records added for testing.
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

        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    /**
     * Test to find all cars by criteria with no criteria being passed.
     *
     * @throws CarException should throw an error that there is no criteria
     */
    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoCriteria() throws CarException {
        logger.info("Find All By Criteria No Criteria");

        String ExpectedError = CarException.SEARCH_CRITERIA_NOT_PROVIDED;

        CarSearchJson carSearchJson = new CarSearchJson();

        try {
            carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    /**
     * Test to find all cars by criteria with no records found.
     *
     * @throws CarException should throw an error that no records were found
     */
    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoRecordsFound() throws CarException {
        logger.info("Find All By Criteria No Records Found");

        String ExpectedError = CarException.NO_CARS_FOUND_FOR_CRITERIA;

        CarSearchJson carSearchJson = new CarSearchJson();
        carSearchJson.setLevelFrom(99999);

        try {
            carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    /**
     * Test to find all cars by a level criteria range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaLevelRange() throws CarException {
        logger.info("Find All By Criteria Level Range: " + LEVEL_MIN_RANGE + " - " + LEVEL_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelFrom(LEVEL_MIN_RANGE);
        carSearchJson.setLevelTo(LEVEL_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find all cars by a level range with the min value set and the max
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaLevelRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + LEVEL_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelFrom(LEVEL_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find all cars by a level range with the max value set and the min
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaLevelRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + "MIN - " + LEVEL_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelTo(LEVEL_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to find all cars by a year range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaYearRange() throws CarException {
        logger.info("Find All By Criteria Year Range: " + YEAR_MIN_RANGE + " - " + YEAR_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearFrom(YEAR_MIN_RANGE);
        carSearchJson.setYearTo(YEAR_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }

    /**
     * Test to find all cars by a year range with the min value set and the max
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaYearRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Year Range: " + YEAR_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearFrom(YEAR_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }

    /**
     * Test to find all cars by a year range with the max value set and the min
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaYearRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Year Range: MIN - " + YEAR_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearTo(YEAR_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to find all cars by a power point range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaPowerPointsRange() throws CarException {
        logger.info("Find All By Criteria Power Points Range: "
                + POWER_POINTS_MIN_RANGE + " - " + POWER_POINTS_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setPowerPointsFrom(POWER_POINTS_MIN_RANGE);
        carSearchJson.setPowerPointsTo(POWER_POINTS_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), POWER_POINTS_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find all cars by power points with the min value set and the max
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaPowerPointsSetMin() throws CarException {
        logger.info("Find All By Criteria Power Points Range: "
                + POWER_POINTS_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setPowerPointsFrom(POWER_POINTS_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), POWER_POINTS_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find all cars by power points with the max value set and the min
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaPowerPointsSetMax() throws CarException {
        logger.info("Find All By Criteria Power Points Range: MIN - " + POWER_POINTS_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setPowerPointsTo(POWER_POINTS_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), POWER_POINTS_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to find cars by horse power range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaHorsePowerRange() throws CarException {
        logger.info("Find All By Criteria Horse Power Range: "
                + HORSE_POWER_MIN_RANGE + " - " + HORSE_POWER_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setHorsePowerFrom(HORSE_POWER_MIN_RANGE);
        carSearchJson.setHorsePowerTo(HORSE_POWER_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), HORSE_POWER_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find all cars by horse power range with the min value set and the
     * max set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaHorsePowerSetMin() throws CarException {
        logger.info("Find All By Criteria Horse Power Range: "
                + HORSE_POWER_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setHorsePowerFrom(HORSE_POWER_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), HORSE_POWER_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find cars by horse power range with the max value set and the min
     * set to null.
     *
     * @throws CarException
     */
    @Test
    public void findAllByCriteriaHorsePowerSetMax() throws CarException {
        logger.info("Find All By Criteria Horse Power Range: MIN - " + HORSE_POWER_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setHorsePowerTo(HORSE_POWER_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), HORSE_POWER_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to find cars by a drivetrain.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaDriveTrain() throws CarException {
        logger.info("Find All By Criteria Drive Train: " + DRIVE_TRAIN_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setDriveTrain(DRIVE_TRAIN_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), DRIVE_TRAIN_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    /**
     * Test to find cars by a dealer name.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaDealerName() throws CarException {
        logger.info("Find All By Criteria Dealer Name: " + DEALER_NAME_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setDealerName(DEALER_NAME_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), DEALER_NAME_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }

    /**
     * Test to find cars by a country.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaCountryDescription() throws CarException {
        logger.info("Find All By Criteria Country Description: " + COUNTRY_DESCRIPTION_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setCountryDescription(COUNTRY_DESCRIPTION_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), COUNTRY_DESCRIPTION_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }

    /**
     * Test to find cars by a region.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaRegionDescription() throws CarException {
        logger.info("Find All By Criteria Region Description: " + REGION_DESCRIPTION_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setRegionDescription(REGION_DESCRIPTION_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), REGION_DESCRIPTION_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }
}
