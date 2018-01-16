package backEnd.general.ownerCars;

import backEnd.general.GTSportDataTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the owner car validate.
 *
 * @author jonathan
 */
public class OwnerCarValidateTest extends GTSportDataTesting {

    private static final String BAD_OWNERCAR_KEY = "X!X999999999";
    private static final String BAD_OWNER_KEY = "X!X999999999";
    private static final String BAD_CAR_KEY = "X!X999999999";

    @Autowired
    private OwnerCarValidate ownerCarValidate;

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

        manufacturerRepository.saveAndFlush(MANUFACTURER1);
        manufacturerRepository.saveAndFlush(MANUFACTURER2);
        manufacturerRepository.saveAndFlush(MANUFACTURER3);
        manufacturerRepository.saveAndFlush(MANUFACTURER4);
        manufacturerRepository.saveAndFlush(MANUFACTURER5);
        manufacturerRepository.saveAndFlush(MANUFACTURER6);
        manufacturerRepository.saveAndFlush(MANUFACTURER7);
        manufacturerRepository.saveAndFlush(MANUFACTURER8);
        manufacturerRepository.saveAndFlush(MANUFACTURER9);

        carRepository.saveAndFlush(CAR1);
        carRepository.saveAndFlush(CAR2);
        carRepository.saveAndFlush(CAR3);
        carRepository.saveAndFlush(CAR4);
        carRepository.saveAndFlush(CAR5);
        carRepository.saveAndFlush(CAR6);
        carRepository.saveAndFlush(CAR7);
        carRepository.saveAndFlush(CAR8);
        carRepository.saveAndFlush(CAR9);
        carRepository.saveAndFlush(CAR10);
        carRepository.saveAndFlush(CAR11);
        carRepository.saveAndFlush(CAR12);
        carRepository.saveAndFlush(CAR13);
        carRepository.saveAndFlush(CAR14);
        carRepository.saveAndFlush(CAR15);

        ownerRepository.saveAndFlush(OWNER1);
        ownerRepository.saveAndFlush(OWNER2);
        ownerRepository.saveAndFlush(OWNER3);

        ownerCarRepository.saveAndFlush(OWNERCAR1);
        ownerCarRepository.saveAndFlush(OWNERCAR2);
        ownerCarRepository.saveAndFlush(OWNERCAR3);
    }

    /**
     * Delete the records added for testing.
     */
    @AfterClass
    @Rollback(false)
    public void afterClass() {
        logger.info("After Class");

        // delete the test records.
        deleteOwnerCarTestRecord(OWNERCAR1.getPrimaryKey());
        deleteOwnerCarTestRecord(OWNERCAR2.getPrimaryKey());
        deleteOwnerCarTestRecord(OWNERCAR3.getPrimaryKey());

        deleteOwnerTestRecord(OWNER1.getPrimaryKey());
        deleteOwnerTestRecord(OWNER2.getPrimaryKey());
        deleteOwnerTestRecord(OWNER3.getPrimaryKey());

        deleteCarTestRecord(CAR1.getPrimaryKey());
        deleteCarTestRecord(CAR2.getPrimaryKey());
        deleteCarTestRecord(CAR3.getPrimaryKey());
        deleteCarTestRecord(CAR4.getPrimaryKey());
        deleteCarTestRecord(CAR5.getPrimaryKey());
        deleteCarTestRecord(CAR6.getPrimaryKey());
        deleteCarTestRecord(CAR7.getPrimaryKey());
        deleteCarTestRecord(CAR8.getPrimaryKey());
        deleteCarTestRecord(CAR9.getPrimaryKey());
        deleteCarTestRecord(CAR10.getPrimaryKey());
        deleteCarTestRecord(CAR11.getPrimaryKey());
        deleteCarTestRecord(CAR12.getPrimaryKey());
        deleteCarTestRecord(CAR13.getPrimaryKey());
        deleteCarTestRecord(CAR14.getPrimaryKey());
        deleteCarTestRecord(CAR15.getPrimaryKey());

        deleteManufacturerTestRecord(MANUFACTURER1.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER2.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER3.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER4.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER5.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER6.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER7.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER8.getPrimaryKey());
        deleteManufacturerTestRecord(MANUFACTURER9.getPrimaryKey());

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
     * Test for validating an owner car save.
     *
     * @throws OwnerCarException should find no errors
     */
    @Test(groups = "goodSave")
    public void validateOwnerCarSave() throws OwnerCarException {
        logger.info("Validate Owner Car Save");

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey(OWNERCAR1.getOwnerKey());
        ownerCarJson.setCarKey(OWNERCAR1.getCarKey());
        ownerCarJson.setCarId(OWNERCAR1.getCarId());
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        ownerCarValidate.validateOwnerCarSave(ownerCarJson);
    }

    /**
     * Test for validating the owner car save where the Id was not filled.
     *
     * @throws OwnerCarException should find an error that the Id was not filled
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarSaveMissingId() throws OwnerCarException {
        logger.info("Validate Owner Car Save Missing Id");

        String expectedError = OwnerCarException.ID_NOT_SET;

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey(OWNERCAR1.getOwnerKey());
        ownerCarJson.setCarKey(OWNERCAR1.getCarKey());
        ownerCarJson.setCarId("");
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        try {
            ownerCarValidate.validateOwnerCarSave(ownerCarJson);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test for validating the save of an owner car where the owner key is not
     * filled.
     *
     * @throws OwnerCarException should find an error that the owner key is not
     * filled
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarSaveMissingOwnerKey() throws OwnerCarException {
        logger.info("Validate Owner Car Save Missing Owner Key");

        String expectedError = OwnerCarException.OWNER_KEY_NOT_SET;

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey("");
        ownerCarJson.setCarKey(OWNERCAR1.getCarKey());
        ownerCarJson.setCarId(OWNERCAR1.getCarId());
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        try {
            ownerCarValidate.validateOwnerCarSave(ownerCarJson);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test validating an owner car where the car key is not filled.
     *
     * @throws OwnerCarException should find an error where the car key is not
     * filled
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarSaveMissingCarKey() throws OwnerCarException {
        logger.info("Validate Owner Car Save Missing Car Key");

        String expectedError = OwnerCarException.CAR_KEY_NOT_SET;

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey(OWNERCAR1.getOwnerKey());
        ownerCarJson.setCarKey("");
        ownerCarJson.setCarId(OWNERCAR1.getCarId());
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        try {
            ownerCarValidate.validateOwnerCarSave(ownerCarJson);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test validating an owner car save where the owner key is not found.
     *
     * @throws OwnerCarException should find an error where the owner key can
     * not be found
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarSaveBadOwnerKey() throws OwnerCarException {
        logger.info("Validate Owner Car Save Bad Owner Key: " + BAD_OWNER_KEY);

        String expectedError = OwnerCarException.OWNER_KEY_DOSE_NOT_EXIST + BAD_OWNER_KEY;

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey(BAD_OWNER_KEY);
        ownerCarJson.setCarKey(OWNERCAR1.getCarKey());
        ownerCarJson.setCarId(OWNERCAR1.getCarId());
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        try {
            ownerCarValidate.validateOwnerCarSave(ownerCarJson);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test validating an owner car save where the car key if not found.
     *
     * @throws OwnerCarException should find an error where the car key is not
     * found
     */
    @Test(dependsOnGroups = "goodSave", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarSaveBadCarKey() throws OwnerCarException {
        logger.info("Validate Owner Car Save Bad Car Key: " + BAD_CAR_KEY);

        String expectedError = OwnerCarException.CAR_KEY_DOSE_NOT_EXIST + BAD_CAR_KEY;

        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(OWNERCAR1.getPrimaryKey());
        ownerCarJson.setOwnerKey(OWNERCAR1.getOwnerKey());
        ownerCarJson.setCarKey(BAD_CAR_KEY);
        ownerCarJson.setCarId(OWNERCAR1.getCarId());
        ownerCarJson.setColour(OWNERCAR1.getCarColour());
        ownerCarJson.setMaxPower(OWNERCAR1.getMaxPower());
        ownerCarJson.setPowerLevel(OWNERCAR1.getPowerLevel());
        ownerCarJson.setWeightReductionLevel(OWNERCAR1.getWeightReductionLevel());
        ownerCarJson.setAcquiredDate(OWNERCAR1.getAcquiredDate());

        try {
            ownerCarValidate.validateOwnerCarSave(ownerCarJson);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

    /**
     * Test validating an owner car delete.
     *
     * @throws OwnerCarException should find no errors
     */
    @Test(groups = "goodDelete")
    public void validateOwnerCarDelete() throws OwnerCarException {
        logger.info("Valdiate Owner Car Delete");

        ownerCarValidate.validateOwnerCarDelete(OWNERCAR1.getPrimaryKey());
    }

    /**
     * Test validating an owner car delete where the owner car key can not be
     * found.
     *
     * @throws OwnerCarException should find an error where the owner car key
     * can not be found.
     */
    @Test(dependsOnGroups = "goodDelete", expectedExceptions = OwnerCarException.class)
    public void validateOwnerCarDeleteBadKey() throws OwnerCarException {
        logger.info("Validate Owner Car Delete Bad Key: " + BAD_OWNERCAR_KEY);

        String expectedError = OwnerCarException.OWNERCAR_KEY_CAN_NOT_BE_FOUND_TO_DELETE
                + BAD_OWNERCAR_KEY;

        try {
            ownerCarValidate.validateOwnerCarDelete(BAD_OWNERCAR_KEY);
        } catch (OwnerCarException oce) {
            assertEquals(oce.getMessage(), expectedError);
            throw oce;
        }
    }

}
