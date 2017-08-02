/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.countries.CountriesForTesting;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
import backEnd.general.dealers.DealersForTesting;
import backEnd.general.regions.Region;
import backEnd.general.regions.RegionRepository;
import backEnd.general.regions.RegionsForTesting;
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
    
    private static final String DRIVE_TRAIN_TEST = DriveTrain.FR.getDescription();
    private static final int DRIVE_TRAIN_RECORDS_EXPECTED = 5;

    private static final String DEALER_NAME_TEST = DEALER2.getName();
    private static final int DEALER_NAME_RECORDS_EXPECTED = 1;

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

    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoCriteria() throws CarException {
        logger.info("Find All By Criteria No Criteria");

        String ExpectedError = CarException.SEARCH_CRITERIA_NOT_PROVIDED;

        CarSearchJson carSearchJson = new CarSearchJson();

        try {
            List<Car> cars = carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

    @Test(expectedExceptions = {CarException.class})
    public void findAllByCriteriaNoRecordsFound() throws CarException {
        logger.info("Find All By Criteria No Records Found");

        String ExpectedError = CarException.NO_CARS_FOUND_FOR_CRITERIA;

        CarSearchJson carSearchJson = new CarSearchJson();
        carSearchJson.setLevelFrom(99999);

        try {
            List<Car> cars = carRepository.findAllByCriteria(carSearchJson);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), ExpectedError);
            throw ce;
        }
    }

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

    @Test
    public void findAllByCriteriaLevelRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + LEVEL_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelFrom(LEVEL_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR3.getPrimaryKey());
    }

    @Test
    public void findAllByCriteriaLevelRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Level Range: " + "MIN - " + LEVEL_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setLevelTo(LEVEL_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), LEVEL_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

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

    @Test
    public void findAllByCriteriaYearRangeMinSet() throws CarException {
        logger.info("Find All By Criteria Year Range: " + YEAR_MIN_RANGE + " - MAX");

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearFrom(YEAR_MIN_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MIN_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }

    @Test
    public void findAllByCriteriaYearRangeMaxSet() throws CarException {
        logger.info("Find All By Criteria Year Range: MIN - " + YEAR_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setYearTo(YEAR_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), YEAR_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

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

    @Test
    public void findAllByCriteriaPowerPointsSetMax() throws CarException {
        logger.info("Find All By Criteria Power Points Range: MIN - " + POWER_POINTS_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setPowerPointsTo(POWER_POINTS_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), POWER_POINTS_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }
    
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
    
    @Test
    public void findAllByCriteriaHorsePowerSetMax() throws CarException {
        logger.info("Find All By Criteria Horse Power Range: MIN - " + HORSE_POWER_MAX_RANGE);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setHorsePowerTo(HORSE_POWER_MAX_RANGE);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), HORSE_POWER_MAX_SET_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }

    @Test
    public void findAllByCriteriaDriveTrain() throws CarException {
        logger.info("Find All By Criteria Drive Train: " + DRIVE_TRAIN_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setDriveTrain(DRIVE_TRAIN_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), DRIVE_TRAIN_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR1.getPrimaryKey());
    }
    
    @Test
    public void findAllByCriteriaDealerName() throws CarException {
        logger.info("Find All By Criteria Dealer Name: " + DEALER_NAME_TEST);

        CarSearchJson carSearchJson = new CarSearchJson();

        carSearchJson.setDealerName(DEALER_NAME_TEST);

        List<Car> cars = carRepository.findAllByCriteria(carSearchJson);

        assertEquals(cars.size(), DEALER_NAME_RECORDS_EXPECTED);
        assertEquals(cars.get(0).getPrimaryKey(), CAR2.getPrimaryKey());
    }
}
