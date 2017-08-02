package backEnd.general.dealers;

import backEnd.general.GTSportConfig;
import backEnd.general.GTSportDataTesting;
import backEnd.general.countries.CountriesForTesting;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
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
 * Tests for the dealer service.
 *
 * @author jonathan
 */
public class DealerServiceTest extends GTSportDataTesting {

    private static final String DEALER_4_NAME = "DEALER_4";
    private static final String DEALER_4_COUNTRY_KEY = CountriesForTesting.country2.getPrimaryKey();
    private static final String NEW_DEALER_4_NAME = "NEW_DEALER_IV";

    private static final String BAD_DEALER_KEY = "D!X999999999";
    private static final String BAD_NAME = "BAD_DEALER_NAME";

    private static final int EXPECTED_NUMBER_OF_ROWS = 3;
    private static final int EXPECTED_NUMBER_OF_ROWS_COUNTRY = 2;

    private static final String EXPECTED_MAX_KEY = "DEA900000004";
    
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

        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        
        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);

        // add the 3 dealers to work with.
        dealerRepository.saveAndFlush(DEALER1);
        dealerRepository.saveAndFlush(DEALER2);
        dealerRepository.saveAndFlush(DEALER3);
    }

    /**
     * Delete the dealer records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());
        deleteDealerTestRecord(DEALER3.getPrimaryKey());
        deleteDealerTestRecord(dealer4Key);
        
        dealerService.resetKeys();

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        
        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    /**
     * Test the get dealer by primary key method.
     *
     * @throws DealerException
     */
    @Test
    public void getDealerJsonByKey() throws DealerException {
        logger.info("Get Dealer By Key");

        DealerJson dealerJson = dealerService.getDealerJsonByKey(DEALER2.getPrimaryKey());

        assertEquals(dealerJson.getPrimaryKey(), DEALER2.getPrimaryKey());
    }

    /**
     * Test the get dealer by primary key and getting the error because it does
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

        DealerJson dealerJson = dealerService.getDealerJsonByName(DEALER1.getName());

        assertEquals(dealerJson.getPrimaryKey(), DEALER1.getPrimaryKey());
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
        assertEquals(dealerJsons.get(0).getPrimaryKey(), DEALER1.getPrimaryKey());
    }

    /**
     * Test getting the dealer list filtered by a country key.
     */
    @Test(dependsOnMethods = {"getDealerList"})
    public void getDealerListByCountryKey() {
        logger.info("Get Dealer List By Dealer Key");

        List<DealerJson> dealerJsons = dealerService.getDealerListByCountryKey(CountriesForTesting.country1.getPrimaryKey());

        assertEquals(dealerJsons.size(), EXPECTED_NUMBER_OF_ROWS_COUNTRY);
        assertEquals(dealerJsons.get(0).getPrimaryKey(), DEALER1.getPrimaryKey());
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
}
