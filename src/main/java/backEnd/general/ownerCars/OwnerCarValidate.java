package backEnd.general.ownerCars;

import backEnd.general.cars.CarRepository;
import backEnd.general.owners.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonathan
 */
@Service
public class OwnerCarValidate {

    @Autowired
    private OwnerCarRepository ownerCarRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CarRepository carRepository;

    public void validateOwnerCarSave(OwnerCarJson ownerCarJson) throws OwnerCarException {
        // Make sure the Id is filled.
        if (ownerCarJson.getCarId().trim().isEmpty()) {
            throw new OwnerCarException(OwnerCarException.ID_NOT_SET);
        }

        // Make sure the owner key is filled.
        if (ownerCarJson.getOwnerKey().trim().isEmpty()) {
            throw new OwnerCarException(OwnerCarException.OWNER_KEY_NOT_SET);
        }

        // Make sure the car key is filled.
        if (ownerCarJson.getCarKey().trim().isEmpty()) {
            throw new OwnerCarException(OwnerCarException.CAR_KEY_NOT_SET);
        }

        // Make sure the owner key exists.
        if (!ownerRepository.exists(ownerCarJson.getOwnerKey())) {
            throw new OwnerCarException(OwnerCarException.OWNER_KEY_DOSE_NOT_EXIST
                    + ownerCarJson.getOwnerKey());
        }

        // Make sure the car key exists.
        if (!carRepository.exists(ownerCarJson.getCarKey())) {
            throw new OwnerCarException(OwnerCarException.CAR_KEY_DOSE_NOT_EXIST
                    + ownerCarJson.getCarKey());
        }
    }

    public void validateOwnerCarDelete(String primaryKey) throws OwnerCarException {
        if (!ownerCarRepository.exists(primaryKey)) {
            throw new OwnerCarException(OwnerCarException.OWNERCAR_KEY_CAN_NOT_BE_FOUND_TO_DELETE
                    + primaryKey);
        }
    }
}
