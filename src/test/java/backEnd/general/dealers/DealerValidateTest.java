package backEnd.general.dealers;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.cars.CarsForTesting;
import backEnd.general.countries.CountriesForTesting;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import backEnd.general.regions.Region;
import backEnd.general.regions.RegionRepository;
import backEnd.general.regions.RegionsForTesting;
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
public class DealerValidateTest extends GTSportDataTesting {

    private static final String BAD_COUNTRY_KEY = "C!X999999999";
    private static final String BAD_DEALER_KEY = "D!X999999999";

    @Autowired
    private DealerValidate dealerValidate;

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

        // add the 3 dealers to work with.
        dealerRepository.saveAndFlush(DEALER1);
        dealerRepository.saveAndFlush(DEALER2);
        dealerRepository.saveAndFlush(DEALER3);
        
        // add car record.
        carRepository.saveAndFlush(CAR2);
    }

    /**
     * Delete the dealer records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteCarTestRecord(CAR2.getPrimaryKey());

        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());
        deleteDealerTestRecord(DEALER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        
        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
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

        dealerJson.setPrimaryKey(DEALER1.getPrimaryKey());
        dealerJson.setName(DEALER1.getName());
        dealerJson.setCountryKey(DEALER1.getCountryKey());

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
            dealerJson.setCountryKey(COUNTRY1.getPrimaryKey());

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
            dealerJson.setPrimaryKey(DEALER1.getPrimaryKey());
            dealerJson.setName(DEALER1.getName());

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
        logger.info("Validate Dealer Save Name Already Exists: " + DEALER1.getName());

        String expectedError = DealerException.NAME_ALREADY_EXISTS;

        try {
            DealerJson dealerJson = new DealerJson();
            dealerJson.setPrimaryKey("");
            dealerJson.setName(DEALER1.getName());
            dealerJson.setCountryKey(DEALER1.getCountryKey());

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
            dealerJson.setPrimaryKey(DEALER1.getPrimaryKey());
            dealerJson.setName(DEALER1.getName());
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

        dealerValidate.validateDealerDelete(DEALER3.getPrimaryKey());
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
            dealerValidate.validateDealerDelete(DEALER2.getPrimaryKey());
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedException);
            throw de;
        }
        
    }
}
