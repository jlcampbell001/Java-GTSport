/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.statistics;

import backEnd.general.GTSportDataTesting;
import backEnd.general.cars.CarException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author jonathan
 */
public class StatisticsServiceTest extends GTSportDataTesting {

        private static final int EXPECTED_NUMBER_OF_CARS = 8;
    
    private static final int EXPECTED_NUMBER_OF_CAR_LEVEL_STAT_ROWS = 8;
    private static final Integer LEVEL_4_ID = 4;
    private static final Long LEVEL_4_NUMBER_OF_CARS = 2L;
    private static final Double LEVEL_4_AVG_PP = 422.5;
    private static final Double LEVEL_4_AVG_HP = 234.0;
    private static final Double LEVEL_4_AVG_PRICE = 57445.00;

    
    @Autowired
    private StatisticsService statisticsService;

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
        deleteDealerTestRecord(DEALER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }
    
    @Test
    public void getCarLevelStatistics() throws CarException {
        logger.info("Get Car Level Statistics");
        
        Map<Integer, CarLevelStatistic> carLevelStatistics = statisticsService.getCarLevelStatistics();
        
        assertEquals(carLevelStatistics.size(), EXPECTED_NUMBER_OF_CAR_LEVEL_STAT_ROWS);
        
        CarLevelStatistic carLevelStatistic4 = carLevelStatistics.get(LEVEL_4_ID);
        
        assertNotNull(carLevelStatistic4, "Can not find level 4 record.");
        assertEquals(carLevelStatistic4.getLevel(), LEVEL_4_ID);
        assertEquals(carLevelStatistic4.getNoOfCars(), LEVEL_4_NUMBER_OF_CARS);
        assertEquals(carLevelStatistic4.getAvgPowerPoints(), LEVEL_4_AVG_PP);
        assertEquals(carLevelStatistic4.getAvgHorsePower(), LEVEL_4_AVG_HP);
        assertEquals(carLevelStatistic4.getAvgPrice(), LEVEL_4_AVG_PRICE);
    }
}
