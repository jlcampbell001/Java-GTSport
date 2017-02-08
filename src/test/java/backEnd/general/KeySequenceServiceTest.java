package backEnd.general;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@ContextConfiguration(locations = { "classpath:hibernate.cfg.xml"})
/**
 * Testing the key sequence service.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class KeySequenceServiceTest extends AbstractTestNGSpringContextTests {

    private static final String TESTING_TABLE_NAME = "TESTING";
    private static final String KEY_PREFIX = "TST";

    private static final String EXPECTED_NEW_VALUE = KEY_PREFIX + "000000001";
    private static final String EXPECTED_EXISTING_VALUE = KEY_PREFIX + "000000002";

    private static final Integer NEW_KEY_VALUE = 999;

    private static final String EXPECTED_EXISTING_VALUE_AFTER_RESET = KEY_PREFIX + "000001000";

    @Autowired
    private KeySequenceService keySequenceService;

    @Autowired
    private KeySequenceRepository keySequenceRepository;

    /**
     * Clear any test records that may have been left.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        deleteTestRecord();
    }

    /**
     * Delete the test records.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        deleteTestRecord();
    }

    /**
     * Test getting a key for a new record with a new table name.
     */
    @Test()
    public void getNextKeyWithNewTable() {
        logger.info("Get Next Key With New Table: " + TESTING_TABLE_NAME);

        String newKey = keySequenceService.getNextKey(TESTING_TABLE_NAME, KEY_PREFIX);

        assertEquals(newKey, EXPECTED_NEW_VALUE);
    }

    /**
     * Test getting a key for an existing record with a table name.
     */
    @Test(dependsOnMethods = {"getNextKeyWithNewTable"})
    public void getNextKeyWithExistingTable() {
        logger.info("Get Next Key With Existing table: " + TESTING_TABLE_NAME);

        String newKey = keySequenceService.getNextKey(TESTING_TABLE_NAME, KEY_PREFIX);

        assertEquals(newKey, EXPECTED_EXISTING_VALUE);
    }

    /**
     * Reset an existing record with a new key value.
     */
    @Test(dependsOnMethods = {"getNextKeyWithExistingTable"})
    public void resetKeyValue() {
        logger.info("Reset Key Value: " + TESTING_TABLE_NAME);

        keySequenceService.resetKeyValue(TESTING_TABLE_NAME, NEW_KEY_VALUE);

        String newKey = keySequenceService.getNextKey(TESTING_TABLE_NAME, KEY_PREFIX);

        assertEquals(newKey, EXPECTED_EXISTING_VALUE_AFTER_RESET);
    }

    /**
     * Delete the testing record from the key sequence table.
     */
    private void deleteTestRecord() {
        KeySequence testKeySequence = keySequenceRepository.findOne(TESTING_TABLE_NAME);

        if (testKeySequence != null) {
            keySequenceRepository.delete(testKeySequence);
        }
    }
}
