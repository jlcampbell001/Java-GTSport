package backEnd.owners;

import static org.testng.Assert.assertEquals;
import backEnd.general.GTSportConfig;
import backEnd.general.owners.Owner;
import backEnd.general.owners.OwnerRepository;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;

@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class OwnerRepositoryTest extends AbstractTestNGSpringContextTests {

	private static final String MAX_OWN_KEY = "XXX900000003"; 

	private static final String OWNER_1_KEY = "XXX900000001";
	private static final String OWNER_1_NAME = "XXX_Test_Owner_1_XXX";
	private static final Boolean OWNER_1_CURRENT = false;

	private static final String OWNER_2_KEY = "XXX900000002";
	private static final String OWNER_2_NAME = "XXX_Test_Owner_2_XXX";
	private static final Boolean OWNER_2_CURRENT = true;

	private static final String OWNER_3_KEY = MAX_OWN_KEY;
	private static final String OWNER_3_NAME = "XXX_Test_Owner_3_XXX";
	private static final Boolean OWNER_3_CURRENT = false;

	private static final String BAD_OWNER_NAME = "XXX_";
	
	@Autowired
	private OwnerRepository ownerRepository;

	@BeforeClass
	@Rollback(false)
	public void beforeClass() {
		// add the 3 owners to work with.
		Owner owner1 = new Owner(OWNER_1_KEY, OWNER_1_NAME, OWNER_1_CURRENT);
		ownerRepository.saveAndFlush(owner1);

		Owner owner2 = new Owner(OWNER_2_KEY, OWNER_2_NAME, OWNER_2_CURRENT);
		ownerRepository.saveAndFlush(owner2);

		Owner owner3 = new Owner(OWNER_3_KEY, OWNER_3_NAME, OWNER_3_CURRENT);
		ownerRepository.saveAndFlush(owner3);
	}

	@AfterClass
	@Rollback(false)
	public void afterClass() {
		// delete the test records.
		deleteTestRecord(OWNER_1_KEY);
		deleteTestRecord(OWNER_2_KEY);
		deleteTestRecord(OWNER_3_KEY);
	}

	@Test
	public void findOwnerByName() {
		Owner foundOwner = ownerRepository.findByName(OWNER_3_NAME);

		assertEquals(foundOwner.getPrimaryKey(), OWNER_3_KEY);
	}

	@Test
	public void findOwnerByNameBadOwer() {
		Owner foundOwner = ownerRepository.findByName(BAD_OWNER_NAME);

		assertEquals(foundOwner, null);
	}
	
	@Test
	public void findCurrentOwner() {
		Owner foundOwner = ownerRepository.findCurrentOwner();
		
		assertEquals(foundOwner.getPrimaryKey(), OWNER_2_KEY);
	}

	private void deleteTestRecord(String deleteKey) {
		Owner owner = ownerRepository.findOne(deleteKey);

		if (owner != null) {
			ownerRepository.delete(owner);
		}
	}
	
	@Test
	public void getMaxKey() {
		String maxKey = ownerRepository.getMaxKey();
		
		assertEquals(maxKey, MAX_OWN_KEY);
	}

}
