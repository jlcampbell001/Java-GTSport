package backEnd.general.statistics;

import org.apache.log4j.Logger;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * Test for the car level statistic class.
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

    private final Logger logger;

    /**
     * Constructor to create a car level statistic test class.
     */
    public CarLevelStatisticTest() {
        this.logger = Logger.getLogger(CarLevelStatisticTest.class);
    }

    /**
     * Test for creating a level statistic class.
     *
     * @throws CarLevelStatisticException should find no errors
     */
    @Test
    public void carLevelStatisticTestObjectConstructor() throws CarLevelStatisticException {
        logger.info("Object[] Constructor");

        Object[] objectParam = new Object[]{LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK,
            AVG_HP_OK, AVG_PRICE_OK};

        CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
        logger.info(carLevelStatistic);
    }

    /**
     * Test for creating a level statistic class with a bad level.
     *
     * @throws CarLevelStatisticException should find an error with a bad level
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorBadLevel() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Bad Level");

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_0_NOT_INTEGER + BAD_LEVEL.getClass().getName();

        Object[] objectParam = new Object[]{BAD_LEVEL, NO_OF_CARS_OK, AVG_PP_OK,
            AVG_HP_OK, AVG_PRICE_OK};

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a level statistic class with bad number of cars.
     *
     * @throws CarLevelStatisticException should find an error with bad number
     * of cars
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorBadNoOfCars() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Bad No Of Cars");

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_1_NOT_LONG
                + BAD_NO_OF_CARS.getClass().getName();

        Object[] objectParam = new Object[]{LEVEL_OK, BAD_NO_OF_CARS, AVG_PP_OK,
            AVG_HP_OK, AVG_PRICE_OK};

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a level statistic class bad average power points.
     *
     * @throws CarLevelStatisticException should find an error with bad average
     * power points
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorBadAVGPP() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Bad Avg PP");

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_2_NOT_DOUBLE
                + BAD_AVG_PP.getClass().getName();

        Object[] objectParam = new Object[]{LEVEL_OK, NO_OF_CARS_OK, BAD_AVG_PP,
            AVG_HP_OK, AVG_PRICE_OK};

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a level statistic class with bad average horse power.
     *
     * @throws CarLevelStatisticException should find an error with bad average
     * horse power.
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorBadAvgHP() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Bad Avg HP");

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_3_NOT_DOUBLE
                + BAD_AVG_HP.getClass().getName();

        Object[] objectParam = new Object[]{LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK,
            BAD_AVG_HP, AVG_PRICE_OK};

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a level statistic class with bad average price.
     *
     * @throws CarLevelStatisticException should find an error with bad average
     * price
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorBadAvgPrice() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Bad Avg Price");

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_4_NOT_DOUBLE
                + BAD_AVG_PRICE.getClass().getName();

        Object[] objectParam = new Object[]{LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK,
            AVG_HP_OK, BAD_AVG_PRICE};

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }

    /**
     * Test for creating a level statistic class with the wrong number of
     * elements.
     *
     * @throws CarLevelStatisticException should find an error with the wrong
     * number of elements
     */
    @Test(expectedExceptions = {CarLevelStatisticException.class})
    public void carLevelStatisticTestObjectConstructorWrongSize() throws CarLevelStatisticException {
        logger.info("Object[] Constructor Wrong size");

        Object[] objectParam = new Object[]{LEVEL_OK, NO_OF_CARS_OK, AVG_PP_OK,
            AVG_HP_OK};

        String expectedError = CarLevelStatisticException.CAR_STAT_OBJECT_SIZE_WRONG
                + objectParam.length;

        try {
            CarLevelStatistic carLevelStatistic = new CarLevelStatistic(objectParam);
            logger.info(carLevelStatistic);
        } catch (CarLevelStatisticException cse) {
            assertEquals(cse.getMessage(), expectedError);
            throw cse;
        }
    }
}
