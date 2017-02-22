package backEnd.general.dealers;

import backEnd.general.GTSportConfig;
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
 * Test the dealer repository.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class DealerRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String COUNTRY_KEY_1 = "COU9000000001";
    private static final String EXPECTED_MAX_KEY = "XXX900000003";

    private static final String DEALER_1_KEY = "XXX900000001";
    private static final String DEALER_1_NAME = "DEALER_1";
    private static final String DEALER_1_COUNTRY_KEY = COUNTRY_KEY_1;

    private static final String DEALER_2_KEY = "XXX900000002";
    private static final String DEALER_2_NAME = "DEALER_2";
    private static final String DEALER_2_COUNTRY_KEY = "COU900000002";

    private static final String DEALER_3_KEY = EXPECTED_MAX_KEY;
    private static final String DEALER_3_NAME = "DEALER_3";
    private static final String DEALER_3_COUNTRY_KEY = COUNTRY_KEY_1;

    private static final int EXPECT_NUMBER_OF_DEALERS = 2;

    @Autowired
    private DealerRepository dealerRepository;

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
    }

    /**
     * Delete the dealer records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteTestRecord(DEALER_1_KEY);
        deleteTestRecord(DEALER_2_KEY);
        deleteTestRecord(DEALER_3_KEY);
    }

    /**
     * Test for the find by name method.
     */
    @Test
    public void findByName() {
        logger.info("Find By Name");

        Dealer dealer = dealerRepository.findByName(DEALER_2_NAME);

        assertEquals(dealer.getPrimaryKey(), DEALER_2_KEY);
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

        List<Dealer> dealers = dealerRepository.findAllByCountryKey(COUNTRY_KEY_1);

        assertEquals(dealers.size(), EXPECT_NUMBER_OF_DEALERS);
        assertEquals(dealers.get(0).getPrimaryKey(), DEALER_1_KEY);
    }

    private void deleteTestRecord(String deleteKey) {
        Dealer dealer = dealerRepository.findOne(deleteKey);

        if (dealer != null) {
            dealerRepository.delete(dealer);
        }
    }
}
