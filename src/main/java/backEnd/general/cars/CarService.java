/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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
    
    @Transactional
    public void deleteCar(String primaryKey) throws CarException {
        carValidate.validateCarDelete(primaryKey);
        
        carRepository.delete(primaryKey);
    }
    
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

    @Transactional
    public List<CarJson> getCarListByDealerKey(String dealerKey) {
        List<CarJson> carJsons = new ArrayList<CarJson>();
        
        List<Car> cars = carRepository.findAllByDealerKey(dealerKey);
        
        for (Car car : cars) {
            CarJson carJson = toJson(car);
            
            carJsons.add(carJson);
        }
        
        return carJsons;
    }
    
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
        carJson.setDealerKey(car.getDealerKey());
        carJson.setDisplacementCC(car.getDisplacementCC());
        carJson.setPowerRPM(car.getPowerRPM());
        carJson.setTorqueRPM(car.getTorqueRPM());
        carJson.setDriveTrain(car.getDriveTrain());
        carJson.setAspiration(car.getAspiration());
        
        carJson.setYear(car.getYear());
        carJson.setLevel(car.getLevel());
        carJson.setPowerPoints(car.getPowerPoints());
        carJson.setHorsePower(car.getHorsePower());
        
        carJson.setPrice(car.getPrice());
        carJson.setTorqueFtLb(car.getTorqueFtLb());
        carJson.setLength(car.getLength());
        carJson.setWidth(car.getWidth());
        carJson.setHeight(car.getHeight());
        carJson.setWeight(car.getWeight());
        
        return carJson;
    }
    
    private Car toCar(CarJson carJson) {
        Car car = new Car();
        
        car.setPrimaryKey(carJson.getPrimaryKey());
        car.setName(carJson.getName());
        car.setDealerKey(carJson.getDealerKey());
        car.setDisplacementCC(carJson.getDisplacementCC());
        car.setPowerRPM(carJson.getPowerRPM());
        car.setTorqueRPM(carJson.getTorqueRPM());
        car.setDriveTrain(carJson.getDriveTrain());
        car.setAspiration(carJson.getAspiration());
        
        car.setYear(carJson.getYear());
        car.setLevel(carJson.getLevel());
        car.setPowerPoints(carJson.getPowerPoints());
        car.setHorsePower(carJson.getHorsePower());
        
        car.setPrice(carJson.getPrice());
        car.setTorqueFtLb(carJson.getTorqueFtLb());
        car.setLength(carJson.getLength());
        car.setWidth(carJson.getWidth());
        car.setHeight(carJson.getHeight());
        car.setWeight(carJson.getWeight());
        
        return car;
    }
}
