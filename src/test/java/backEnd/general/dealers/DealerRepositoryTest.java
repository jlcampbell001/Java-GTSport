package backEnd.general.dealers;

import backEnd.general.GTSportDataTesting;
import java.util.List;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test the dealer repository.
 *
 * @author jonathan
 */
public class DealerRepositoryTest extends GTSportDataTesting {

    private static final String EXPECTED_MAX_KEY = DEALER3.getPrimaryKey();

    private static final int EXPECT_NUMBER_OF_DEALERS = 2;

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
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteDealerTestRecord(DEALER1.getPrimaryKey());
        deleteDealerTestRecord(DEALER2.getPrimaryKey());
        deleteDealerTestRecord(DEALER3.getPrimaryKey());

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
    }

    /**
     * Test for the find by name method.
     */
    @Test
    public void findByName() {
        logger.info("Find By Name");

        Dealer dealer = dealerRepository.findByName(DEALER2.getName());

        assertEquals(dealer.getPrimaryKey(), DEALER2.getPrimaryKey());
    }

    /**
     * Test for the get max key method.
     */
    @Test
    public void getMaxKey() {
        logger.info("Get Max Key");

        String maxKey = dealerRepository.getMaxKey();

        assertEquals(maxKey, EXPECTED_MAX_KEY);
    }

    /**
     * Test for the find all by country key method.
     */
    @Test
    public void findAllByCountryKey() {
        logger.info("Find All By Country Key");

        List<Dealer> dealers = dealerRepository.findAllByCountryKey(DEALER1.getCountryKey());

        assertEquals(dealers.size(), EXPECT_NUMBER_OF_DEALERS);
        assertEquals(dealers.get(0).getPrimaryKey(), DEALER1.getPrimaryKey());
    }
}
