package backEnd.general.manufacturers;

import backEnd.general.GTSportDataTesting;
import backEnd.general.countries.CountriesForTesting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the manufacturer service.
 *
 * @author jonathan
 */
public class ManufacturerServiceTest extends GTSportDataTesting {

    private static final String MANUFACTURER_10_NAME = "MANUFACTURER_10";
    private static final String MANUFACTURER_STRING_10_COUNTRY_KEY = CountriesForTesting.COUNTRY2.getPrimaryKey();
    private static final String NEW_MANUFACTURER_10_NAME = "NEW_MANUFACTURER_X";

    private static final String BAD_MANUFACTURER_KEY = "D!X999999999";
    private static final String BAD_NAME = "BAD_MANUFACTURER_NAME";

    private static final int EXPECTED_NUMBER_OF_ROWS = 9;
    private static final int EXPECTED_NUMBER_OF_ROWS_COUNTRY = 3;

    private static final String EXPECTED_MAX_KEY = "MAN900000010";

    @Autowired
    private ManufacturerService manufacturerService;

    private String manufacturer10Key = "";

    /**
     * Setup records to test against.
     */
    @BeforeClass
    @Rollback(false)
    public void beforeClass() {
        logger.info("Before Class");

        regionRepository.saveAndFlush(REGION1);
        regionRepository.saveAndFlush(REGION2);
        regionRepository.saveAndFlush(REGION3);

        countryRepository.saveAndFlush(COUNTRY1);
        countryRepository.saveAndFlush(COUNTRY2);
        countryRepository.saveAndFlush(COUNTRY3);
        countryRepository.saveAndFlush(COUNTRY4);
        countryRepository.saveAndFlush(COUNTRY5);

        // add the manufacturers to work with.
        manufacturerRepository.saveAndFlush(MANUFACTURER1);
        manufacturerRepository.saveAndFlush(MANUFACTURER2);
        manufacturerRepository.saveAndFlush(MANUFACTURER3);
        manufacturerRepository.saveAndFlush(MANUFACTURER4);
        manufacturerRepository.saveAndFlush(MANUFACTURER5);
        manufacturerRepository.saveAndFlush(MANUFACTURER6);
        manufacturerRepository.saveAndFlush(MANUFACTURER7);
        manufacturerRepository.saveAndFlush(MANUFACTURER8);
        manufacturerRepository.saveAndFlush(MANUFACTURER9);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteManufacturerTestRecord(MANUFACTURER1.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER2.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER3.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER4.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER5.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER6.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER7.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER8.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER9.getPrimaryKey());
        deleteManufacturerTestRecord(manufacturer10Key);

        manufacturerService.resetKeys();

        deleteCountryTestRecord(COUNTRY1.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY2.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY3.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY4.getPrimaryKey());
        deleteCountryTestRecord(COUNTRY5.getPrimaryKey());

        deleteRegionTestRecord(REGION1.getPrimaryKey());
        deleteRegionTestRecord(REGION2.getPrimaryKey());
        deleteRegionTestRecord(REGION3.getPrimaryKey());
    }

    /**
     * Test the get dealer by primary key method.
     *
     * @throws ManufacturerException
     */
    @Test
    public void getManufacturerJsonByKey() throws ManufacturerException {
        logger.info("Get Manufacturer By Key");

        ManufacturerJson manufacturerJson = manufacturerService.getManufacturerJsonByKey(MANUFACTURER2.getPrimaryKey());

        assertEquals(manufacturerJson.getPrimaryKey(), MANUFACTURER2.getPrimaryKey());
    }

    /**
     * Test the get manufacturer by primary key and getting the error because it
     * does not exist.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void getManufacturerJsonByKeyBadKey() throws ManufacturerException {
        logger.info("Get Manufacturer By Key Bad Key: " + BAD_MANUFACTURER_KEY);

        String expectedError = ManufacturerException.MANUFACTURER_KEY_NOT_FOUND + BAD_MANUFACTURER_KEY;

        try {
            manufacturerService.getManufacturerJsonByKey(BAD_MANUFACTURER_KEY);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test get the manufacturer by the manufacturer name.
     *
     * @throws ManufacturerException
     */
    @Test
    public void getManufacturerJsonByName() throws ManufacturerException {
        logger.info("Get Manufacturer By Name");

        ManufacturerJson manufacturerJson = manufacturerService.getManufacturerJsonByName(MANUFACTURER1.getName());

        assertEquals(manufacturerJson.getPrimaryKey(), MANUFACTURER1.getPrimaryKey());
    }

    /**
     * Test the get the manufacturer by name where the name does not exist.
     *
     * @throws ManufacturerException
     */
    @Test(expectedExceptions = ManufacturerException.class)
    public void getManufacturerJsonByNameBadName() throws ManufacturerException {
        logger.info("Get Manufacturer By Name Bad Name: " + BAD_NAME);

        String expectedError = ManufacturerException.MANUFACTURER_NAME_NOT_FOUND + BAD_NAME;

        try {
            manufacturerService.getManufacturerJsonByName(BAD_NAME);
        } catch (ManufacturerException de) {
            assertEquals(de.getMessage(), expectedError);
            throw de;
        }
    }

    /**
     * Test saving a new manufacturer.
     *
     * @throws ManufacturerException
     */
    @Test
    public void saveNewManufacturer() throws ManufacturerException {
        logger.info("Save New Manufacturer");

        ManufacturerJson manufacturerJson = new ManufacturerJson();
        manufacturerJson.setPrimaryKey("");
        manufacturerJson.setName(MANUFACTURER_10_NAME);
        manufacturerJson.setCountryKey(MANUFACTURER_STRING_10_COUNTRY_KEY);

        manufacturerService.saveManufacturer(manufacturerJson);

        manufacturer10Key = manufacturerJson.getPrimaryKey();
    }

    /**
     * Test updating a manufacturer.
     *
     * @throws ManufacturerException
     */
    @Test(dependsOnMethods = {"saveNewManufacturer"})
    public void updateManufacturer() throws ManufacturerException {
        logger.info("Update Manufacturer");

        ManufacturerJson manufacturerJson = new ManufacturerJson();
        manufacturerJson.setPrimaryKey(manufacturer10Key);
        manufacturerJson.setName(NEW_MANUFACTURER_10_NAME);
        manufacturerJson.setCountryKey(MANUFACTURER_STRING_10_COUNTRY_KEY);

        manufacturerService.saveManufacturer(manufacturerJson);
    }

    /**
     * Test deleting a manufacturer.
     *
     * @throws ManufacturerException
     */
    @Test(dependsOnMethods = {"updateManufacturer"})
    public void deleteManufacturer() throws ManufacturerException {
        logger.info("Delete Manufacturer: " + manufacturer10Key);

        manufacturerService.deleteManufacturer(manufacturer10Key);
    }

    /**
     * Test getting the manufacturer list.
     */
    @Test(dependsOnMethods = {"deleteManufacturer"})
    public void getManufacturerList() {
        logger.info("Get Manufacturer List");

        List<ManufacturerJson> manufacturerJsons = manufacturerService.getManufacturerList();

        assertEquals(manufacturerJsons.size(), EXPECTED_NUMBER_OF_ROWS);
        assertEquals(manufacturerJsons.get(0).getPrimaryKey(), MANUFACTURER1.getPrimaryKey());
    }

    /**
     * Test getting the manufacturer list filtered by a country key.
     */
    @Test(dependsOnMethods = {"getManufacturerList"})
    public void getManufacturerListByCountryKey() {
        logger.info("Get Manufacturer List By Manufacturer Key");

        List<ManufacturerJson> manufacturerJsons = manufacturerService.getManufacturerListByCountryKey(CountriesForTesting.COUNTRY2.getPrimaryKey());

        assertEquals(manufacturerJsons.size(), EXPECTED_NUMBER_OF_ROWS_COUNTRY);
        assertEquals(manufacturerJsons.get(0).getPrimaryKey(), MANUFACTURER2.getPrimaryKey());
    }

    /**
     * Test reset the primary key value.
     *
     * @throws ManufacturerException
     */
    @Test(dependsOnMethods = {"getManufacturerListByCountryKey"})
    public void resetKeys() throws ManufacturerException {
        logger.info("Reset Keys");

        manufacturerService.resetKeys();

        ManufacturerJson manufacturerJson = new ManufacturerJson();
        manufacturerJson.setPrimaryKey("");
        manufacturerJson.setName(MANUFACTURER_10_NAME);
        manufacturerJson.setCountryKey(MANUFACTURER_STRING_10_COUNTRY_KEY);

        manufacturerService.saveManufacturer(manufacturerJson);

        manufacturer10Key = manufacturerJson.getPrimaryKey();

        assertEquals(EXPECTED_MAX_KEY, manufacturer10Key);
    }
}
