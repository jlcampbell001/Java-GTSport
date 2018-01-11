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

    private static final Category CATEGORY_MIN_RANGE = Category.N500;
    private static final Category CATEGORY_MAX_RANGE = Category.N700;
    private static final int CATEGORY_RANGE_RECORDS_EXPECTED = 3;
    private static final int CATEGORY_MIN_SET_RECORDS_EXPECTED = 10;
    private static final int CATEGORY_MAX_SET_RECORDS_EXPECTED = 8;

    private static final int YEAR_MIN_RANGE = 1972;
    private static final int YEAR_MAX_RANGE = 2013;
    private static final int YEAR_RANGE_RECORDS_EXPECTED = 4;
    private static final int YEAR_MIN_SET_RECORDS_EXPECTED = 11;
    private static final int YEAR_MAX_SET_RECORDS_EXPECTED = 8;

    private static final int MAX_POWER_MIN_RANGE = 500;
    private static final int MAX_POWER_MAX_RANGE = 900;
    private static final int MAX_POWER_RANGE_RECORDS_EXPECTED = 7;
    private static final int MAX_POWER_MIN_SET_RECORDS_EXPECTED = 8;
    private static final int MAX_POWER_MAX_SET_RECORDS_EXPECTED = 14;

    private static final DriveTrain DRIVE_TRAIN_TEST = DriveTrain.FR;
    private static final int DRIVE_TRAIN_RECORDS_EXPECTED = 3;

    private static final String MANUFACTURER_NAME_TEST = MANUFACTURER2.getName();
    private static final int MANUFACTURER_NAME_RECORDS_EXPECTED = 1;

    private static final String COUNTRY_DESCRIPTION_TEST = COUNTRY2.getDescription();
    private static final int COUNTRY_DESCRIPTION_RECORDS_EXPECTED = 3;

    private static final String REGION_DESCRIPTION_TEST = REGION2.getDescription();
    private static final int REGION_DESCRIPTION_RECORDS_EXPECTED = 10;

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
        carSearchJson.setCategoryFrom(Category.MAX);

        try {
            carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    /**
     * Test to find all cars by a category criteria range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaCategoryRange() throws CarException {
        logger.info("Find All By Criteria Category Range: " + CATEGORY_MIN_RANGE + " - " + CATEGORY_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setCategoryFrom(CATEGORY_MIN_RANGE);
        carSearchJson.setCategoryTo(CATEGORY_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), CATEGORY_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR5.getPrimaryKey());
    }

    /**
     * Test to find all cars by a category range with the min value set and the max
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaCategoryRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Category Range: " + CATEGORY_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setCategoryFrom(CATEGORY_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), CATEGORY_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR5.getPrimaryKey());
    }

    /**
     * Test to find all cars by a category range with the max value set and the min
     * set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaCategoryRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Category Range: " + "MIN - " + CATEGORY_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setCategoryTo(CATEGORY_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), CATEGORY_MAX_SET_RECORDS_EXPECTED);
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
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
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
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
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
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    /**
     * Test to find cars by max power range.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaMaxPowerRange() throws CarException {
        logger.info("Find All By Criteria Max Power Range: "
                + MAX_POWER_MIN_RANGE + " - " + MAX_POWER_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setMaxPowerFrom(MAX_POWER_MIN_RANGE);
        carSearchJson.setMaxPowerTo(MAX_POWER_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), MAX_POWER_RANGE_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR6.getPrimaryKey());
    }

    /**
     * Test to find all cars by max power range with the min value set and the
     * max set to null.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaMaxPowerSetMin() throws CarException {
        logger.info("Find All By Criteria Max Power Range: "
                + MAX_POWER_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setMaxPowerFrom(MAX_POWER_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), MAX_POWER_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR6.getPrimaryKey());
    }

    /**
     * Test to find cars by max power range with the max value set and the min
     * set to null.
     *
     * @throws CarException
     */
    @Test
    public void findAllByCriteriaMaxPowerSetMax() throws CarException {
        logger.info("Find All By Criteria Max Power Range: MIN - " + MAX_POWER_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setMaxPowerTo(MAX_POWER_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), MAX_POWER_MAX_SET_RECORDS_EXPECTED);
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
     * Test to find cars by a manufacturer name.
     *
     * @throws CarException should find no errors
     */
    @Test
    public void findAllByCriteriaDealerName() throws CarException {
        logger.info("Find All By Criteria Manufacturer Name: " + MANUFACTURER_NAME_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setDealerName(MANUFACTURER_NAME_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), MANUFACTURER_NAME_RECORDS_EXPECTED);
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
