package backEnd.general.dealers;

import backEnd.general.GTSportConfig;
import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the dealer validate.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class DealerValidateTest extends AbstractTestNGSpringContextTests {

    private static final String COUNTRY_1_KEY = "COU900000001";
    private static final String COUNTRY_1_DESCRIPTION = "COUNTRY 1";
    private static final String COUNTRY_1_REGION_KEY = "REG900000001";

    private static final String COUNTRY_2_KEY = "COU900000002";
    private static final String COUNTRY_2_DESCRIPTION = "COUNTRY 2";
    private static final String COUNTRY_2_REGION_KEY = "REG900000002";

    private static final String DEALER_1_KEY = "XXX900000001";
    private static final String DEALER_1_NAME = "DEALER_1";
    private static final String DEALER_1_COUNTRY_KEY = COUNTRY_1_KEY;

    private static final String DEALER_2_KEY = "XXX900000002";
    private static final String DEALER_2_NAME = "DEALER_2";
    private static final String DEALER_2_COUNTRY_KEY = COUNTRY_2_KEY;

    private static final String DEALER_3_KEY = "XXX900000003";
    private static final String DEALER_3_NAME = "DEALER_3";
    private static final String DEALER_3_COUNTRY_KEY = COUNTRY_1_KEY;
    
    private static final String CAR_KEY = "CAR900000001";
    private static final String CAR_NAME = "TEST_CAR";
    private static final String CAR_DEALER_KEY = DEALER_2_KEY;

    private static final String BAD_COUNTRY_KEY = "C!X999999999";
    private static final String BAD_DEALER_KEY = "D!X999999999";

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DealerRepository dealerRepository;
    
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DealerValidate dealerValidate;

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        // add the 3 dealers to work with.
        Dealer dealer1 = new Dealer(DEALER_1_KEY, DEALER_1_NAME, DEALER_1_COUNTRY_KEY);
        dealerRepository.saveAndFlush(dealer1);

        Dealer dealer2 = new Dealer(DEALER_2_KEY, DEALER_2_NAME, DEALER_2_COUNTRY_KEY);
        dealerRepository.saveAndFlush(dealer2);

        Dealer dealer3 = new Dealer(DEALER_3_KEY, DEALER_3_NAME, DEALER_3_COUNTRY_KEY);
        dealerRepository.saveAndFlush(dealer3);

        // add country records.
        Country country1 = new Country(COUNTRY_1_KEY, COUNTRY_1_DESCRIPTION, COUNTRY_1_REGION_KEY);
        countryRepository.saveAndFlush(country1);

        Country country2 = new Country(COUNTRY_2_KEY, COUNTRY_2_DESCRIPTION, COUNTRY_2_REGION_KEY);
        countryRepository.saveAndFlush(country2);
        
        // add car record.
        Car car = new Car();
        car.setPrimaryKey(CAR_KEY);
        car.setName(CAR_NAME);
        car.setDealerKey(CAR_DEALER_KEY);
        carRepository.saveAndFlush(car);
    }

    /**
     * Delete the dealer records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteDealerTestRecord(DEALER_1_KEY);
        deleteDealerTestRecord(DEALER_2_KEY);
        deleteDealerTestRecord(DEALER_3_KEY);

        deleteCountryTestRecord(COUNTRY_1_KEY);
        deleteCountryTestRecord(COUNTRY_2_KEY);
        
        deleteCarTestRecord(CAR_KEY);
    }

    /**
     * Test validating a dealer save that is good to save.
     *
     * @throws DealerException
     */
    @Test
    public void validateDealerSave() throws DealerException {
        logger.info("Validate Dealer Save");

        DealerJson dealerJson = new DealerJson();

        dealerJson.setPrimaryKey(DEALER_1_KEY);
        dealerJson.setName(DEALER_1_NAME);
        dealerJson.setCountryKey(DEALER_1_COUNTRY_KEY);

        dealerValidate.validateDealerSave(dealerJson);
    }

    /**
     * Test the validating the dealer on a save with the dealer name not filled.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerSaveNameNotSet() throws DealerException {
        logger.info("Validate Dealer Save Name Not Set");

        String expectedError = DealerException.NAME_NOT_SET;

        try {
            DealerJson dealerJson = new DealerJson();
            dealerJson.setCountryKey(COUNTRY_1_KEY);

            dealerValidate.validateDealerSave(dealerJson);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test validating the dealer save where the country key is not filled.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerSaveCountryKeyNotSet() throws DealerException {
        logger.info("Validate Dealer Save Country Key Not Set");

        String expectedError = DealerException.COUNTRY_KEY_NOT_SET;

        try {
            DealerJson dealerJson = new DealerJson();
            dealerJson.setPrimaryKey(DEALER_1_KEY);
            dealerJson.setName(DEALER_1_NAME);

            dealerValidate.validateDealerSave(dealerJson);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test validating the dealer save where the name already exists on another
     * dealer.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerSaveNameAlreadyExists() throws DealerException {
        logger.info("Validate Dealer Save Name Already Exists: " + DEALER_1_NAME);

        String expectedError = DealerException.NAME_ALREADY_EXISTS;

        try {
            DealerJson dealerJson = new DealerJson();
            dealerJson.setPrimaryKey("");
            dealerJson.setName(DEALER_1_NAME);
            dealerJson.setCountryKey(DEALER_1_COUNTRY_KEY);

            dealerValidate.validateDealerSave(dealerJson);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test the validate dealer save where the country key does not exist.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerSaveBadCountryKey() throws DealerException {
        logger.info("Validate Dealer Save Bad Country Key: " + BAD_COUNTRY_KEY);

        String expectedError = DealerException.COUNTRY_KEY_DOES_NOT_EXIST + BAD_COUNTRY_KEY;

        try {
            DealerJson dealerJson = new DealerJson();
            dealerJson.setPrimaryKey(DEALER_1_KEY);
            dealerJson.setName(DEALER_1_NAME);
            dealerJson.setCountryKey(BAD_COUNTRY_KEY);

            dealerValidate.validateDealerSave(dealerJson);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test the validate deleting the dealer successfully.
     *
     * @throws DealerException
     */
    @Test
    public void validateDealerDelete() throws DealerException {
        logger.info("Validate Dealer Delete");

        dealerValidate.validateDealerDelete(DEALER_3_KEY);
    }

    /**
     * Test the validate deleting the dealer and passing in a non-existing
     * primary key.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerDeleteBadDealerKey() throws DealerException {
        logger.info("Validate Dealer Delete Bad Dealer Key");

        String expectedException = DealerException.DEALER_KEY_DOES_NOT_EXIST_DELETE + BAD_DEALER_KEY;

        try {
            dealerValidate.validateDealerDelete(BAD_DEALER_KEY);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedException);
            throw de;
        }
    }
    
    @Test(expectedExceptions = DealerException.class)
    public void validateDealerDeleteInUse() throws DealerException {
        logger.info("Validate Dealer Delete In Use");
        
        String expectedException = DealerException.DEALER_IS_IN_USE;
        
        try {
            dealerValidate.validateDealerDelete(DEALER_2_KEY);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedException);
            throw de;
        }
        
    }

    private void deleteDealerTestRecord(String deleteKey) {
        Dealer dealer = dealerRepository.findOne(deleteKey);

        if (dealer != null) {
            dealerRepository.delete(dealer);
        }
    }

    private void deleteCountryTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }

    private void deleteCarTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }
}
