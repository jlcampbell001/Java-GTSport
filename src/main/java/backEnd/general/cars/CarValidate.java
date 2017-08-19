package backEnd.general.cars;

import backEnd.general.dealers.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The save and delete validates for a car.
 *
 * @author jonathan
 */
@Service
public class CarValidate {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private CarRepository carRepository;

    /**
     * Validates the data to save a car.<br>
     * - Car name must be filled.<br>
     * - Dealer key must be filled.<br>
     * - Drivetrain can not be null<br>
     * - Aspiration can not be null<br>
     * - The car name must be unique.<br>
     * - The dealer key must exist in the dealer table.
     *
     * @param carJson the car data to check
     * @throws CarException if there is an issue with the car data
     */
    public void validateCarSave(CarJson carJson) throws CarException {
        // Make sure the name is filled.
        if (carJson.getName().trim().isEmpty()) {
            throw new CarException(CarException.NAME_NOT_SET);
        }

        // Make sure the dealer is set.
        if (carJson.getDealerKey().trim().isEmpty()) {
            throw new CarException(CarException.DEALER_KEY_NOT_SET);
        }

        // Make sure the drivetrain is set.
        if (carJson.getDriveTrain() == null) {
            throw new CarException(CarException.DRIVETRAIN_NOT_VALID);
        }

        // Make sure the aspiration is (NA, T, SC, EV) if not empty.
        if (carJson.getAspiration() == null) {
            throw new CarException(CarException.ASPIRATION_NOT_VALID);
        }

        // Make sure the name is unique.
        Car existingCar = carRepository.findByName(carJson.getName());

        if (existingCar != null) {
            if ((carJson.getPrimaryKey().trim().isEmpty())
                    || (!carJson.getPrimaryKey().trim().equals(existingCar.getPrimaryKey().trim()))) {
                throw new CarException(CarException.NAME_ALREADY_EXISTS);
            }
        }

        // Make sure the dealer key exists.
        if (!dealerRepository.exists(carJson.getDealerKey())) {
            throw new CarException(CarException.DEALER_KEY_DOES_NOT_EXIST + carJson.getDealerKey());
        }
    }

    /**
     * Validates the car to be deleted. <br>
     * - Car key must exist to delete.<br>
     * - The car can not be used as a owned car.
     *
     * @param primaryKey the primary key of the car to delete
     * @throws CarException if there is an issue with being able to delete the
     * car
     */
    public void validateCarDelete(String primaryKey) throws CarException {
        if (!carRepository.exists(primaryKey)) {
            throw new CarException(CarException.CAR_KEY_NOT_FOUND_TO_DELETE + primaryKey);
        }

        // Make sure the car is not used in a owned car.
        /*    List<Dealer> dealers = dealerRepository.findAllByCountryKey(primaryKey);
        
        if (!dealers.isEmpty()) {
            throw new CountryException(CountryException.COUNTRY_IS_IN_USE);
        }*/
    }
}
