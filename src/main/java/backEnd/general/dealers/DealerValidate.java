package backEnd.general.dealers;

import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.countries.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Validates the dealer on a save or delete.
 *
 * @author jonathan
 */
@Service
public class DealerValidate {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CarRepository carRepository;

    /**
     * Validates the dealer for saving.
     *
     * @param dealerJson - the dealer Json to validate
     * @throws DealerException - Errors - missing name or country key. The name
     * not being unique. The country key not existing.
     */
    public void validateDealerSave(DealerJson dealerJson) throws DealerException {

        // Make sure the dealer name is filled.
        if (dealerJson.getName().trim().isEmpty()) {
            throw new DealerException(DealerException.NAME_NOT_SET);
        }

        // Make sure the dealer country key is filled.
        if (dealerJson.getCountryKey().trim().isEmpty()) {
            throw new DealerException(DealerException.COUNTRY_KEY_NOT_SET);
        }

        // Make sure the dealer name is unique.
        Dealer existingDealer = dealerRepository.findByName(dealerJson.getName());

        if (existingDealer != null) {
            if ((dealerJson.getPrimaryKey().trim().isEmpty())
                    || (!dealerJson.getPrimaryKey().trim().equals(existingDealer.getPrimaryKey().trim()))) {
                throw new DealerException(DealerException.NAME_ALREADY_EXISTS);
            }
        }

        // Make sure the country key exists.
        if (!countryRepository.exists(dealerJson.getCountryKey())) {
            throw new DealerException(DealerException.COUNTRY_KEY_DOES_NOT_EXIST + dealerJson.getCountryKey());
        }
    }

    /**
     * Validates the dealer to delete.
     *
     * @param primaryKey - the primary key of the dealer to delete
     * @throws DealerException Errors if the primary key is not found or is used
     * in a car.
     */
    public void validateDealerDelete(String primaryKey) throws DealerException {
        if (!dealerRepository.exists(primaryKey)) {
            throw new DealerException(DealerException.DEALER_KEY_DOES_NOT_EXIST_DELETE + primaryKey);
        }

        // Make sure the dealer is not being used by a car.
        List<Car> cars = carRepository.findAllByDealerKey(primaryKey);

        if (!cars.isEmpty()) {
            throw new DealerException(DealerException.DEALER_IS_IN_USE);
        }
    }
}
