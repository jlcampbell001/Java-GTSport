package backEnd.general.statistics;

import backEnd.general.cars.Category;
import org.apache.log4j.Logger;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * Test for the car level statistic class.
 *
 * @author jonathan
 */
public class CarCategoryStatisticTest {

    private static final Category CATEGORY_OK = Category.N100;
    private static final Long NO_OF_CARS_OK = 4L;
    private static final Double AVG_MAX_POWER_OK = 84.0;
    private static final Double AVG_PRICE_OK = 2999.99;

    private static final String BAD_CATEGORY = "1";
    private static final String BAD_NO_OF_CARS = "4L";
    private static final String BAD_AVG_MAX_POWER = "84.0";
    private static final String BAD_AVG_PRICE = "2999.99";

    private final Logger logger;

    /**
     * Constructor to create a car level statistic test class.
     */
    public CarCategoryStatisticTest() {
        this.logger = Logger.getLogger(CarCategoryStatisticTest.class);
    }

    /**
     * Test for creating a category statistic class.
     *
     * @throws CarCategoryStatisticException should find no errors
     */
    @Test
    public void carCategoryStatisticTestObjectConstructor() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor");

        Object[] objectParam = new Object[]{CATEGORY_OK, NO_OF_CARS_OK,
            AVG_MAX_POWER_OK, AVG_PRICE_OK};

        CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
        logger.info(carCategoryStatistic);
    }

    /**
     * Test for creating a category statistic class with a bad category.
     *
     * @throws CarCategoryStatisticException should find an error with a bad category
     */
    @Test(expectedExceptions = {CarCategoryStatisticException.class})
    public void carCategoryStatisticTestObjectConstructorBadCategory() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor Bad Level");

        String expectedError = CarCategoryStatisticException.CAR_STAT_OBJECT_0_NOT_CATEGORY + BAD_CATEGORY.getClass().getName();

        Object[] objectParam = new Object[]{BAD_CATEGORY, NO_OF_CARS_OK, 
            AVG_MAX_POWER_OK, AVG_PRICE_OK};

        try {
            CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
            logger.info(carCategoryStatistic);
        } catch (CarCategoryStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a category statistic class with bad number of cars.
     *
     * @throws CarCategoryStatisticException should find an error with bad number
     * of cars
     */
    @Test(expectedExceptions = {CarCategoryStatisticException.class})
    public void carCategoryStatisticTestObjectConstructorBadNoOfCars() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor Bad No Of Cars");

        String expectedError = CarCategoryStatisticException.CAR_STAT_OBJECT_1_NOT_LONG
                + BAD_NO_OF_CARS.getClass().getName();

        Object[] objectParam = new Object[]{CATEGORY_OK, BAD_NO_OF_CARS, 
            AVG_MAX_POWER_OK, AVG_PRICE_OK};

        try {
            CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
            logger.info(carCategoryStatistic);
        } catch (CarCategoryStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a category statistic class with bad average max power.
     *
     * @throws CarCategoryStatisticException should find an error with bad average
     * max power.
     */
    @Test(expectedExceptions = {CarCategoryStatisticException.class})
    public void carCategoryStatisticTestObjectConstructorBadAvgMaxPower() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor Bad Avg Max Power");

        String expectedError = CarCategoryStatisticException.CAR_STAT_OBJECT_2_NOT_DOUBLE
                + BAD_AVG_MAX_POWER.getClass().getName();

        Object[] objectParam = new Object[]{CATEGORY_OK, NO_OF_CARS_OK, 
            BAD_AVG_MAX_POWER, AVG_PRICE_OK};

        try {
            CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
            logger.info(carCategoryStatistic);
        } catch (CarCategoryStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a category statistic class with bad average price.
     *
     * @throws CarCategoryStatisticException should find an error with bad average
     * price
     */
    @Test(expectedExceptions = {CarCategoryStatisticException.class})
    public void carCategoryStatisticTestObjectConstructorBadAvgPrice() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor Bad Avg Price");

        String expectedError = CarCategoryStatisticException.CAR_STAT_OBJECT_3_NOT_DOUBLE
                + BAD_AVG_PRICE.getClass().getName();

        Object[] objectParam = new Object[]{CATEGORY_OK, NO_OF_CARS_OK, 
            AVG_MAX_POWER_OK, BAD_AVG_PRICE};

        try {
            CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
            logger.info(carCategoryStatistic);
        } catch (CarCategoryStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a category statistic class with the wrong number of
     * elements.
     *
     * @throws CarCategoryStatisticException should find an error with the wrong
     * number of elements
     */
    @Test(expectedExceptions = {CarCategoryStatisticException.class})
    public void carCategoryStatisticTestObjectConstructorWrongSize() throws CarCategoryStatisticException {
        logger.info("Object[] Constructor Wrong size");

        Object[] objectParam = new Object[]{CATEGORY_OK, NO_OF_CARS_OK, 
            AVG_MAX_POWER_OK};

        String expectedError = CarCategoryStatisticException.CAR_STAT_OBJECT_SIZE_WRONG
                + objectParam.length;

        try {
            CarCategoryStatistic carCategoryStatistic = new CarCategoryStatistic(objectParam);
            logger.info(carCategoryStatistic);
        } catch (CarCategoryStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }
}
