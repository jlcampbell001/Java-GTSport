package backEnd.general.manufacturers;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for the manufacturer.
 *
 * @author jonathan
 */
@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private KeySequenceService keySequenceService;

    @Autowired
    private ManufacturerValidate manufacturerValidate;

    /**
     * Get a manufacturer Json using the primary key.
     *
     * @param primaryKey - the primary key to look for
     * @return - the manufacturer found
     * @throws ManufacturerException - an error if the manufacturer is not found
     */
    @Transactional
    public ManufacturerJson getManufacturerJsonByKey(String primaryKey) throws ManufacturerException {
        Manufacturer manufacturer = null;

        try {
            manufacturer = manufacturerRepository.findOne(primaryKey);

            if (manufacturer == null) {
                throw new ManufacturerException(ManufacturerException.MANUFACTURER_KEY_NOT_FOUND + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new ManufacturerException(ManufacturerException.MANUFACTURER_KEY_NOT_FOUND + primaryKey);
        }

        ManufacturerJson manufacturerJson = toJson(manufacturer);

        return manufacturerJson;
    }

    /**
     * Get a manufacturer Json by the manufacturer name.
     *
     * @param name - the name to look for
     * @return - the manufacturer found
     * @throws ManufacturerException - an error if the manufacturer is not found
     */
    @Transactional
    public ManufacturerJson getManufacturerJsonByName(String name) throws ManufacturerException {
        Manufacturer manufacturer = null;

        try {
            manufacturer = manufacturerRepository.findByName(name);

            if (manufacturer == null) {
                throw new ManufacturerException(ManufacturerException.MANUFACTURER_NAME_NOT_FOUND + name);
            }
        } catch (NoResultException nre) {
            throw new ManufacturerException(ManufacturerException.MANUFACTURER_NAME_NOT_FOUND + name);
        }

        ManufacturerJson manufacturerJson = toJson(manufacturer);

        return manufacturerJson;
    }

    /**
     * Save the passed manufacturerJson.
     *
     * @param manufacturerJson - the manufacturer info to save
     * @throws ManufacturerException - errors found with the manufacturer info to save
     */
    @Transactional
    public void saveManufacturer(ManufacturerJson manufacturerJson) throws ManufacturerException {
        manufacturerValidate.validateManufacturerSave(manufacturerJson);

        if (manufacturerJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("MANUFACTURERS", "MAN");
            manufacturerJson.setPrimaryKey(primaryKey);
        }

        Manufacturer manufacturer = toManufacturer(manufacturerJson);

        manufacturerRepository.saveAndFlush(manufacturer);
    }

    /**
     * Delete the manufacturer.
     *
     * @param primaryKey - the primary key of the manufacturer to delete
     * @throws ManufacturerException - errors found when trying to delete the manufacturer
     */
    @Transactional
    public void deleteManufacturer(String primaryKey) throws ManufacturerException {
        manufacturerValidate.validateManufacturerDelete(primaryKey);

        manufacturerRepository.delete(primaryKey);
    }

    /**
     * Get a list of manufacturers.
     *
     * @return - the list of manufacturers
     */
    @Transactional
    public List<ManufacturerJson> getManufacturerList() {
        List<ManufacturerJson> manufacturerJsons = new ArrayList<ManufacturerJson>();

        List<Manufacturer> manufacturers = manufacturerRepository.findAll();

        for (Manufacturer manufacturer : manufacturers) {
            ManufacturerJson manufacturerJson = toJson(manufacturer);

            manufacturerJsons.add(manufacturerJson);

        }

        return manufacturerJsons;
    }

    /**
     * Get a list of manufacturers for the passed country key.
     *
     * @param countryKey - the country key to filter by
     * @return - the list of manufacturers found
     */
    @Transactional
    public List<ManufacturerJson> getManufacturerListByCountryKey(String countryKey) {
        List<ManufacturerJson> manufacturerJsons = new ArrayList<ManufacturerJson>();

        List<Manufacturer> manufacturers = manufacturerRepository.findAllByCountryKey(countryKey);

        for (Manufacturer manufacturer : manufacturers) {
            ManufacturerJson manufacturerJson = toJson(manufacturer);

            manufacturerJsons.add(manufacturerJson);

        }

        return manufacturerJsons;
    }

    /**
     * Reset the last key in the key sequence table for the manufacturers table to the
     * highest key.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = manufacturerRepository.getMaxKey();
        int maxKeyValue = 0;

        if (!(maxKey == null) && !(maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("MANUFACTURERS", maxKeyValue);
    }

    private ManufacturerJson toJson(Manufacturer manufacturer) {
        ManufacturerJson manufacturerJson = new ManufacturerJson();
        manufacturerJson.setPrimaryKey(manufacturer.getPrimaryKey());
        manufacturerJson.setName(manufacturer.getName());
        manufacturerJson.setCountryKey(manufacturer.getCountryKey());

        return manufacturerJson;
    }

    private Manufacturer toManufacturer(ManufacturerJson manufacturerJson) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setPrimaryKey(manufacturerJson.getPrimaryKey());
        manufacturer.setName(manufacturerJson.getName());
        manufacturer.setCountryKey(manufacturerJson.getCountryKey());

        return manufacturer;
    }
}
