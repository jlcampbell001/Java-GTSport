package backEnd.general.manufacturers;

import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.countries.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Validates the manufacturer on a save or delete.
 *
 * @author jonathan
 */
@Service
public class ManufacturerValidate {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CarRepository carRepository;

    /**
     * Validates the manufacturer for saving.
     *
     * @param manufacturerJson - the manufacturer Json to validate
     * @throws ManufacturerException - Errors - missing name or country key. The name
     * not being unique. The country key not existing.
     */
    public void validateManufacturerSave(ManufacturerJson manufacturerJson) throws ManufacturerException {

        // Make sure the manufacturer name is filled.
        if (manufacturerJson.getName().trim().isEmpty()) {
            throw new ManufacturerException(ManufacturerException.NAME_NOT_SET);
        }

        // Make sure the manufacturer country key is filled.
        if (manufacturerJson.getCountryKey().trim().isEmpty()) {
            throw new ManufacturerException(ManufacturerException.COUNTRY_KEY_NOT_SET);
        }

        // Make sure the manufacturer name is unique.
        Manufacturer existingManufacturer = manufacturerRepository.findByName(manufacturerJson.getName());

        if (existingManufacturer != null) {
            if ((manufacturerJson.getPrimaryKey().trim().isEmpty())
                    || (!manufacturerJson.getPrimaryKey().trim().equals(existingManufacturer.getPrimaryKey().trim()))) {
                throw new ManufacturerException(ManufacturerException.NAME_ALREADY_EXISTS);
            }
        }

        // Make sure the country key exists.
        if (!countryRepository.exists(manufacturerJson.getCountryKey())) {
            throw new ManufacturerException(ManufacturerException.COUNTRY_KEY_DOES_NOT_EXIST + manufacturerJson.getCountryKey());
        }
    }

    /**
     * Validates the manufacturer to delete.
     *
     * @param primaryKey - the primary key of the manufacturer to delete
     * @throws ManufacturerException Errors if the primary key is not found or is used
     * in a car.
     */
    public void validateManufacturerDelete(String primaryKey) throws ManufacturerException {
        if (!manufacturerRepository.exists(primaryKey)) {
            throw new ManufacturerException(ManufacturerException.MANUFACTURER_KEY_DOES_NOT_EXIST_DELETE + primaryKey);
        }

        // Make sure the manufacturer is not being used by a car.
        List<Car> cars = carRepository.findAllByManufacturerKey(primaryKey);

        if (!cars.isEmpty()) {
            throw new ManufacturerException(ManufacturerException.MANUFACTURER_IS_IN_USE);
        }
    }
}
