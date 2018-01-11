package backEnd.general.cars;

import backEnd.general.ownerCars.OwnerCar;
import backEnd.general.ownerCars.OwnerCarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backEnd.general.manufacturers.ManufacturerRepository;

/**
 * The save and delete validates for a car.
 *
 * @author jonathan
 */
@Service
public class CarValidate {

    @Autowired
    private ManufacturerRepository dealerRepository;

    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private OwnerCarRepository ownerCarRepository;

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
        if (carJson.getManufacturerKey().trim().isEmpty()) {
            throw new CarException(CarException.MANUFACTURER_KEY_NOT_SET);
        }

        // Make sure the drivetrain is set.
        if (carJson.getDriveTrain() == null) {
            throw new CarException(CarException.DRIVETRAIN_NOT_VALID);
        }

        // Make sure the aspiration is (NA, T, SC, EV) if not empty.
        if (carJson.getAspiration() == null) {
            throw new CarException(CarException.ASPIRATION_NOT_VALID);
        }
        
        // Make sure the category is valid if not empty.
        if (carJson.getCategory() == null) {
            throw new CarException(CarException.CATEGORY_NOT_VALID);
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
        if (!dealerRepository.exists(carJson.getManufacturerKey())) {
            throw new CarException(CarException.MANUFACTURER_KEY_DOES_NOT_EXIST + carJson.getManufacturerKey());
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
        List<OwnerCar> ownerCars = ownerCarRepository.findAllByCarKey(primaryKey);
        
        if (!ownerCars.isEmpty()) {
            throw new CarException(CarException.CAR_IS_IN_USE);
        }
    }
}
