package backEnd.general.cars;

import backEnd.general.countries.CountryException;
import backEnd.general.dealers.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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
     * name set
     * name unique
     * dealer set
     * dealer valid
     * Drivetrain must be 4WD, FF, FR, MR, RR
     * Aspiration  must be NA, T, SC, EV
     * @param carJson
     * @throws CarException 
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
        
        // Make sure the drivetrain is (4WD, FF, FR, MR, RR) if not empty.
        if (!carJson.getDriveTrain().trim().isEmpty()) {
            if (DriveTrain.toDriveTrain(carJson.getDriveTrain()) == null) {
                throw new CarException(CarException.DRIVETRAIN_NOT_VALID);
            }
        }
        
        // Make sure the aspiration is (NA, T, SC, EV) if not empty.
        if (!carJson.getAspiration().trim().isEmpty()) {
            if (Aspiration.toAspiration(carJson.getAspiration()) == null) {
                throw new CarException(CarException.ASPIRATION_NOT_VALID);
            }
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
