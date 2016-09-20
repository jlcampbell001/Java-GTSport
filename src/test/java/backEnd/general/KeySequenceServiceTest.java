package backEnd.general;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

//import backEnd.general.KeySequenceService;

//@ContextConfiguration(locations = { "classpath:hibernate.cfg.xml"})
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class KeySequenceServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private KeySequenceService keySequenceService;
	
//	KeySequenceService keySequenceService = new KeySequenceService();
	
	@Test
    public void getNextKeyWithNewTable() {		
	    String newKey = keySequenceService.getNextKey("TEESTING","XXX");
	  
	    assertEquals(newKey, "XXX000000001");	  
    }
}
