package backEnd.general.ownerCars;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for the owner cars.
 *
 * @author jonathan
 */
@Service
public class OwnerCarService {

    @Autowired
    KeySequenceService keySequenceService;

    @Autowired
    OwnerCarRepository ownerCarRepository;

    @Autowired
    OwnerCarValidate ownerCarValidate;

    /**
     * Get an owner car by the primary key.
     *
     * @param primaryKey the primary key to look for
     * @return The owner car found.
     * @throws OwnerCarException if the owner car can not be found
     */
    @Transactional
    public OwnerCarJson getOwnerCarByKey(String primaryKey) throws OwnerCarException {
        OwnerCar ownerCar = null;

        try {
            ownerCar = ownerCarRepository.findOne(primaryKey);

            if (ownerCar == null) {
                throw new OwnerCarException(OwnerCarException.OWNERCAR_KEY_NOT_FOUND
                        + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new OwnerCarException(OwnerCarException.OWNERCAR_KEY_NOT_FOUND
                    + primaryKey);
        }

        OwnerCarJson ownerCarJson = toJson(ownerCar);

        return ownerCarJson;
    }

    /**
     * Save the passed owner cars.
     *
     * @param ownerCarJson the owner car data to be saved
     * @throws OwnerCarException if the validate for issues with the data
     */
    @Transactional
    public void saveOwnerCar(OwnerCarJson ownerCarJson) throws OwnerCarException {
        ownerCarValidate.validateOwnerCarSave(ownerCarJson);

        if (ownerCarJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("OWNERCARS", "OWC");
            ownerCarJson.setPrimaryKey(primaryKey);
        }

        OwnerCar ownerCar = toOwnerCar(ownerCarJson);

        ownerCarRepository.saveAndFlush(ownerCar);
    }

    /**
     * Delete the owner car for the passed primary key.
     *
     * @param primaryKey the primary key to delete
     * @throws OwnerCarException if the validate finds issues with deleting the
     * owner car
     */
    @Transactional
    public void deleteOwnerCar(String primaryKey) throws OwnerCarException {
        ownerCarValidate.validateOwnerCarDelete(primaryKey);

        ownerCarRepository.delete(primaryKey);
    }

    /**
     * Get a list of all the owner cars.
     *
     * @return A list of owner cars.
     */
    @Transactional
    public List<OwnerCarJson> getOwnerCarList() {
        List<OwnerCarJson> ownerCarJsons = new ArrayList<OwnerCarJson>();

        List<OwnerCar> ownerCars = ownerCarRepository.findAll();

        for (OwnerCar ownerCar : ownerCars) {
            OwnerCarJson ownerCarJson = toJson(ownerCar);

            ownerCarJsons.add(ownerCarJson);
        }

        return ownerCarJsons;
    }

    /**
     * Get a list of owner cars for the passed owner key.
     *
     * @param ownerKey the owner key to filter by
     * @return A list of owner cars.
     */
    @Transactional
    public List<OwnerCarJson> getOwnerCarListByOwnerKey(String ownerKey) {
        List<OwnerCarJson> ownerCarJsons = new ArrayList<OwnerCarJson>();

        List<OwnerCar> ownerCars = ownerCarRepository.findAllByOwnerKey(ownerKey);

        for (OwnerCar ownerCar : ownerCars) {
            OwnerCarJson ownerCarJson = toJson(ownerCar);

            ownerCarJsons.add(ownerCarJson);
        }

        return ownerCarJsons;
    }

    /**
     * Reset the last primary key used.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = ownerCarRepository.getMaxKey();
        int maxKeyValue = 0;

        if (!(maxKey == null) && !(maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("OWNERCARS", maxKeyValue);
    }

    private OwnerCarJson toJson(OwnerCar ownerCar) {
        OwnerCarJson ownerCarJson = new OwnerCarJson();

        ownerCarJson.setPrimaryKey(ownerCar.getPrimaryKey());
        ownerCarJson.setOwnerKey(ownerCar.getOwnerKey());
        ownerCarJson.setCarKey(ownerCar.getCarKey());
        ownerCarJson.setCarId(ownerCar.getCarId());
        ownerCarJson.setColour(ownerCar.getCarColour());
        ownerCarJson.setPowerPoints(ownerCar.getPowerPoints());
        ownerCarJson.setAcquiredDate(ownerCar.getAcquiredDate());

        return ownerCarJson;
    }

    private OwnerCar toOwnerCar(OwnerCarJson ownerCarJson) {
        OwnerCar ownerCar = new OwnerCar();

        ownerCar.setPrimaryKey(ownerCarJson.getPrimaryKey());
        ownerCar.setOwnerKey(ownerCarJson.getOwnerKey());
        ownerCar.setCarKey(ownerCarJson.getCarKey());
        ownerCar.setCarId(ownerCarJson.getCarId());
        ownerCar.setCarColour(ownerCarJson.getColour());
        ownerCar.setPowerPoints(ownerCarJson.getPowerPoints());
        ownerCar.setAcquiredDate(ownerCarJson.getAcquiredDate());

        return ownerCar;
    }
}
