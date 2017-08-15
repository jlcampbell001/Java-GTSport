/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.statistics;


import backEnd.general.cars.CarException;
import org.apache.log4j.Logger;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author jonathan
 */
public class CarLevelStatisticTest {
    private static final Integer LEVEL_OK = 1;
    private static final Long NO_OF_CARS_OK = 4L;
    private static final Double AVG_PP_OK = 111.0;
    private static final Double AVG_HP_OK = 84.0;
    private static final Double AVG_PRICE_OK = 2999.99;
    
    private static final String BAD_LEVEL = "1";
    private static final String BAD_NO_OF_CARS = "4L";
    private static final String BAD_AVG_PP = "111.0";
    private static final String BAD_AVG_HP = "84.0";
    private static final String BAD_AVG_PRICE = "2999.99";
    
    private Logger logger = Logger.getLogger(CarLevelStatisticTest.class);
    
    @Test
    public void CarLevelStatisticTestObjectConstructor() throws CarException {
        logger.info("Object[] Constructor");
        
        Object[] objectParam = new Object[] {LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK, 
        AVG_HP_OK, AVG_PRICE_OK};
        
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
    }

    @Test(expectedExceptions = {CarException.class})
    public void CarLevelStatisticTestObjectConstructorBadLevel() throws CarException {
        logger.info("Object[] Constructor Bad Level");

        String expectedError = CarException.CAR_STAT_OBJECT_0_NOT_INTEGER + BAD_LEVEL.getClass().getName();
        
        Object[] objectParam = new Object[] {BAD_LEVEL, NO_OF_CARS_OK, AVG_PP_OK, 
        AVG_HP_OK, AVG_PRICE_OK};
        
        try {
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
    
    @Test(expectedExceptions = {CarException.class})
    public void CarLevelStatisticTestObjectConstructorBadNoOfCars() throws CarException {
        logger.info("Object[] Constructor Bad No Of Cars");

        String expectedError = CarException.CAR_STAT_OBJECT_1_NOT_LONG + 
        BAD_NO_OF_CARS.getClass().getName();
        
        Object[] objectParam = new Object[] {LEVEL_OK, BAD_NO_OF_CARS, AVG_PP_OK, 
        AVG_HP_OK, AVG_PRICE_OK};
        
        try {
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    @Test(expectedExceptions = {CarException.class})
    public void CarLevelStatisticTestObjectConstructorBadAVGPP() throws CarException {
        logger.info("Object[] Constructor Bad Avg PP");

        String expectedError = CarException.CAR_STAT_OBJECT_2_NOT_DOUBLE + 
        BAD_AVG_PP.getClass().getName();
        
        Object[] objectParam = new Object[] {LEVEL_OK, NO_OF_CARS_OK, BAD_AVG_PP, 
        AVG_HP_OK, AVG_PRICE_OK};
        
        try {
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }

    @Test(expectedExceptions = {CarException.class})
    public void CarLevelStatisticTestObjectConstructorBadAvgHP() throws CarException {
        logger.info("Object[] Constructor Bad Avg HP");

        String expectedError = CarException.CAR_STAT_OBJECT_3_NOT_DOUBLE + 
        BAD_AVG_HP.getClass().getName();
        
        Object[] objectParam = new Object[] {LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK, 
        BAD_AVG_HP, AVG_PRICE_OK};
        
        try {
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
    
    @Test(expectedExceptions = {CarException.class})
    public void CarLevelStatisticTestObjectConstructorBadAvgPrice() throws CarException {
        logger.info("Object[] Constructor Bad Avg Price");

        String expectedError = CarException.CAR_STAT_OBJECT_4_NOT_DOUBLE + 
        BAD_AVG_PRICE.getClass().getName();
        
        Object[] objectParam = new Object[] {LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK, 
        AVG_HP_OK, BAD_AVG_PRICE};
        
        try {
        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        } catch (CarException ce) {
            assertEquals(ce.getMessage(), expectedError);
            throw ce;
        }
    }
}
