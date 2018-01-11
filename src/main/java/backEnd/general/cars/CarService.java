package backEnd.general.cars;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for working with the car table.
 *
 * @author jonathan
 */
@Service
public class CarService {

    @Autowired
    private KeySequenceService keySequenceService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarValidate carValidate;

    /**
     * Gets the car Json for a car primary key.
     *
     * @param primaryKey the car key to look for
     * @return The car Json found.
     * @throws CarException if the car can not be found
     */
    @Transactional
    public CarJson getCarJsonByKey(String primaryKey) throws CarException {
        Car car = null;

        try {
            car = carRepository.findOne(primaryKey);

            if (car == null) {
                throw new CarException(CarException.CAR_KEY_NOT_FOUND + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new CarException(CarException.CAR_KEY_NOT_FOUND + primaryKey);
        }

        CarJson carJson = toJson(car);

        return carJson;
    }

    /**
     * Gets the car Json for a car by the car name.
     *
     * @param name the car name to look for
     * @return The car Json found.
     * @throws CarException if the car can not be found
     */
    @Transactional
    public CarJson getCarJsonByName(String name) throws CarException {
        Car car = null;

        try {
            car = carRepository.findByName(name);

            if (car == null) {
                throw new CarException(CarException.CAR_NAME_NOT_FOUND + name);
            }
        } catch (NoResultException nre) {
            throw new CarException(CarException.CAR_NAME_NOT_FOUND + name);
        }

        CarJson carJson = toJson(car);

        return carJson;
    }

    /**
     * Save a car to the database.
     *
     * @param carJson the car to save
     * @throws CarException errors that have be found during validation of the
     * car data
     */
    @Transactional
    public void saveCar(CarJson carJson) throws CarException {
        carValidate.validateCarSave(carJson);

        if (carJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("CARS", "CAR");
            carJson.setPrimaryKey(primaryKey);
        }

        Car car = toCar(carJson);

        carRepository.saveAndFlush(car);
    }

    /**
     * Delete a car from the table based on the primary key.
     *
     * @param primaryKey the primary key of the car to delete
     * @throws CarException errors that have be found during validation of the
     * car data
     */
    @Transactional
    public void deleteCar(String primaryKey) throws CarException {
        carValidate.validateCarDelete(primaryKey);

        carRepository.delete(primaryKey);
    }

    /**
     * Get a list of all the cars.
     *
     * @return a list of cars
     */
    @Transactional
    public List<CarJson> getCarList() {
        List<CarJson> carJsons = new ArrayList<CarJson>();

        List<Car> cars = carRepository.findAll();

        for (Car car : cars) {
            CarJson carJson = toJson(car);

            carJsons.add(carJson);
        }

        return carJsons;
    }

    /**
     * Gets a list of all the car for a manufacturer key.
     *
     * @param manufacturerKey the manufacturer key to filter the cars on
     * @return a list of cars
     */
    @Transactional
    public List<CarJson> getCarListByManufacturerKey(String manufacturerKey) {
        List<CarJson> carJsons = new ArrayList<CarJson>();

        List<Car> cars = carRepository.findAllByManufacturerKey(manufacturerKey);

        for (Car car : cars) {
            CarJson carJson = toJson(car);

            carJsons.add(carJson);
        }

        return carJsons;
    }

    /**
     * Resets the last key value to the highest key in the car table.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = carRepository.getMaxKey();
        int maxKeyValue = 0;

        if (!(maxKey == null) && !(maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("CARS", maxKeyValue);
    }

    private CarJson toJson(Car car) {
        CarJson carJson = new CarJson();

        carJson.setPrimaryKey(car.getPrimaryKey());
        carJson.setName(car.getName());
        carJson.setManufacturerKey(car.getManufacturerKey());
        carJson.setDisplacementCC(car.getDisplacementCC());
        carJson.setPowerRPM(car.getPowerRPM());
        carJson.setTorqueRPM(car.getTorqueRPM());
        carJson.setDriveTrain(car.getDriveTrain());
        carJson.setAspiration(car.getAspiration());

        carJson.setYear(car.getYear());
        carJson.setCategory(car.getCategory());
        carJson.setMaxPower(car.getMaxPower());

        carJson.setPrice(car.getPrice());
        carJson.setTorqueFtLb(car.getTorqueFtLb());
        carJson.setLength(car.getLength());
        carJson.setWidth(car.getWidth());
        carJson.setHeight(car.getHeight());
        carJson.setWeight(car.getWeight());
        carJson.setMaxSpeed(car.getMaxSpeed());
        carJson.setAcceleration(car.getAcceleration());
        carJson.setBraking(car.getBraking());
        carJson.setCornering(car.getCornering());
        carJson.setStability(car.getStability());

        return carJson;
    }

    private Car toCar(CarJson carJson) {
        Car car = new Car();

        car.setPrimaryKey(carJson.getPrimaryKey());
        car.setName(carJson.getName());
        car.setManufacturerKey(carJson.getManufacturerKey());
        car.setDisplacementCC(carJson.getDisplacementCC());
        car.setPowerRPM(carJson.getPowerRPM());
        car.setTorqueRPM(carJson.getTorqueRPM());
        car.setDriveTrain(carJson.getDriveTrain());
        car.setAspiration(carJson.getAspiration());

        car.setYear(carJson.getYear());
        car.setCategory(carJson.getCategory());
        car.setMaxPower(carJson.getMaxPower());

        car.setPrice(carJson.getPrice());
        car.setTorqueFtLb(carJson.getTorqueFtLb());
        car.setLength(carJson.getLength());
        car.setWidth(carJson.getWidth());
        car.setHeight(carJson.getHeight());
        car.setWeight(carJson.getWeight());
        car.setMaxSpeed(carJson.getMaxSpeed());
        car.setAcceleration(carJson.getAcceleration());
        car.setBraking(carJson.getBraking());
        car.setCornering(carJson.getCornering());
        car.setStability(carJson.getStability());

        return car;
    }
}
