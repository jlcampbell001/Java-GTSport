package backEnd.general.ownerCars;

import backEnd.general.KeySequenceService;
import backEnd.general.cars.Car;
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
public class OwnerCarService {

    @Autowired
    KeySequenceService keySequenceService;

    @Autowired
    OwnerCarRepository ownerCarRepository;

    @Autowired
    OwnerCarValidate ownerCarValidate;

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

    @Transactional
    public void deleteOwnerCar(String primaryKey) throws OwnerCarException {
        ownerCarValidate.validateOwnerCarDelete(primaryKey);

        ownerCarRepository.delete(primaryKey);
    }

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
