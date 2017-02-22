package backEnd.general.dealers;

import backEnd.general.GTSportConfig;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
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
 * Tests for the dealer service.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class DealerServiceTest extends AbstractTestNGSpringContextTests {

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

    private static final String DEALER_4_NAME = "DEALER_4";
    private static final String DEALER_4_COUNTRY_KEY = COUNTRY_2_KEY;
    private static final String NEW_DEALER_4_NAME = "NEW_DEALER_IV";

    private static final String BAD_DEALER_KEY = "D!X999999999";
    private static final String BAD_NAME = "BAD_DEALER_NAME";

    private static final int EXPECTED_NUMBER_OF_ROWS = 3;
    private static final int EXPECTED_NUMBER_OF_ROWS_COUNTRY = 2;

    private static final String EXPECTED_MAX_KEY = "DEA900000004";
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private DealerService dealerService;

    private String dealer4Key = "";

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
        deleteDealerTestRecord(dealer4Key);

        deleteCountryTestRecord(COUNTRY_1_KEY);
        deleteCountryTestRecord(COUNTRY_2_KEY);
    }

    /**
     * Test the get dealer by primary key method.
     *
     * @throws DealerException
     */
    @Test
    public void getDealerJsonByKey() throws DealerException {
        logger.info("Get Dealer By Key");

        DealerJson dealerJson = dealerService.getDealerJsonByKey(DEALER_2_KEY);

        assertEquals(dealerJson.getPrimaryKey(), DEALER_2_KEY);
    }

    /**
     * Test the get dealer by primary key and getting the error because it dose
     * not exist.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void getDealerJsonByKeyBadKey() throws DealerException {
        logger.info("Get Dealer By Key Bad Key: " + BAD_DEALER_KEY);

        String expectedError = DealerException.DEALER_KEY_NOT_FOUND + BAD_DEALER_KEY;

        try {
            dealerService.getDealerJsonByKey(BAD_DEALER_KEY);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test get the dealer by the dealer name.
     *
     * @throws DealerException
     */
    @Test
    public void getDealerJsonByName() throws DealerException {
        logger.info("Get Dealer By Name");

        DealerJson dealerJson = dealerService.getDealerJsonByName(DEALER_1_NAME);

        assertEquals(dealerJson.getPrimaryKey(), DEALER_1_KEY);
    }

    /**
     * Test the get the dealer by name where the name does not exist.
     *
     * @throws DealerException
     */
    @Test(expectedExceptions = DealerException.class)
    public void getDealerJsonByNameBadName() throws DealerException {
        logger.info("Get Dealer By Name Bad Name: " + BAD_NAME);

        String expectedError = DealerException.DEALER_NAME_NOT_FOUND + BAD_NAME;

        try {
            dealerService.getDealerJsonByName(BAD_NAME);
        } catch (DealerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test saving a new dealer.
     *
     * @throws DealerException
     */
    @Test
    public void saveNewDealer() throws DealerException {
        logger.info("Save New Dealer");

        DealerJson dealerJson = new DealerJson();
        dealerJson.setPrimaryKey("");
        dealerJson.setName(DEALER_4_NAME);
        dealerJson.setCountryKey(DEALER_4_COUNTRY_KEY);

        dealerService.saveDealer(dealerJson);

        dealer4Key = dealerJson.getPrimaryKey();
    }

    /**
     * Test updating a dealer.
     *
     * @throws DealerException
     */
    @Test(dependsOnMethods = {"saveNewDealer"})
    public void updateDealer() throws DealerException {
        logger.info("Update Dealer");

        DealerJson dealerJson = new DealerJson();
        dealerJson.setPrimaryKey(dealer4Key);
        dealerJson.setName(NEW_DEALER_4_NAME);
        dealerJson.setCountryKey(DEALER_4_COUNTRY_KEY);

        dealerService.saveDealer(dealerJson);
    }

    /**
     * Test deleting a dealer.
     *
     * @throws DealerException
     */
    @Test(dependsOnMethods = {"updateDealer"})
    public void deleteDealer() throws DealerException {
        logger.info("Delete Dealer: " + dealer4Key);

        dealerService.deleteDealer(dealer4Key);
    }

    /**
     * Test getting the dealer list.
     */
    @Test(dependsOnMethods = {"deleteDealer"})
    public void getDealerList() {
        logger.info("Get Dealer List");

        List<DealerJson> dealerJsons = dealerService.getDealerList();

        assertEquals(dealerJsons.size(), EXPECTED_NUMBER_OF_ROWS);
        assertEquals(dealerJsons.get(0).getPrimaryKey(), DEALER_1_KEY);
    }

    /**
     * Test getting the dealer list filtered by a country key.
     */
    @Test(dependsOnMethods = {"getDealerList"})
    public void getDealerListByCountryKey() {
        logger.info("Get Dealer List By Dealer Key");

        List<DealerJson> dealerJsons = dealerService.getDealerListByCountryKey(COUNTRY_1_KEY);

        assertEquals(dealerJsons.size(), EXPECTED_NUMBER_OF_ROWS_COUNTRY);
        assertEquals(dealerJsons.get(0).getPrimaryKey(), DEALER_1_KEY);
    }

    /**
     * Test reset the primary key value.
     *
     * @throws DealerException
     */
    @Test(dependsOnMethods = {"getDealerListByCountryKey"})
    public void resetKeys() throws DealerException {
        logger.info("Reset Keys");

        dealerService.resetKeys();

        DealerJson dealerJson = new DealerJson();
        dealerJson.setPrimaryKey("");
        dealerJson.setName(DEALER_4_NAME);
        dealerJson.setCountryKey(DEALER_4_COUNTRY_KEY);

        dealerService.saveDealer(dealerJson);

        dealer4Key = dealerJson.getPrimaryKey();

        assertEquals(EXPECTED_MAX_KEY, dealer4Key);
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
}
