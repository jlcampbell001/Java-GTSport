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
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class KeySequenceServiceTest extends AbstractTestNGSpringContextTests {
	
	private static final String TESTING_TABLE_NAME = "TESTING";
	private static final String KEY_PREFIX = "TST";
	
	private static final String EXPECTED_NEW_VALUE = KEY_PREFIX + "000000001";	
	private static final String EXPECTED_EXISTING_VALUE = KEY_PREFIX + "000000002";
	
	@Autowired
	private KeySequenceService keySequenceService;
	
	@Autowired
	private KeySequenceRepository keySequenceRepository;
		
	@BeforeClass
	@Rollback(false)
	public void beforeClass() {
		deleteTestRecord();
	}
	
	@AfterClass
	@Rollback(false)
	public void afterClass() {
		deleteTestRecord();
	}
	
	@Test(groups = "first")
    public void getNextKeyWithNewTable() {		
	    String newKey = keySequenceService.getNextKey(TESTING_TABLE_NAME, KEY_PREFIX);
	  
	    assertEquals(newKey, EXPECTED_NEW_VALUE);	  
    }

	@Test(dependsOnGroups="first")
    public void getNextKeyWithExistingTable() {		
	    String newKey = keySequenceService.getNextKey(TESTING_TABLE_NAME, KEY_PREFIX);
	  
	    assertEquals(newKey, EXPECTED_EXISTING_VALUE);	  
    }

	private void deleteTestRecord() {
		KeySequence testKeySequence = keySequenceRepository.findOne(TESTING_TABLE_NAME);
		
		if (testKeySequence != null) {
			keySequenceRepository.delete(testKeySequence);
		}
	}
}
